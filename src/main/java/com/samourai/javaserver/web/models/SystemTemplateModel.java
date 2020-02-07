package com.samourai.javaserver.web.models;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.ui.Model;

public class SystemTemplateModel extends DashboardTemplateModel {
  public Collection<Thread> threads;
  private long memUsed;
  private long memTotal;

  public SystemTemplateModel(String pageTitle, String logoTitle) {
    super(pageTitle, logoTitle);

    ThreadGroup tg = Thread.currentThread().getThreadGroup();
    this.threads =
        Thread.getAllStackTraces()
            .keySet()
            .stream()
            .filter(t -> t.getThreadGroup() == tg)
            .sorted(Comparator.comparing(o -> o.getName().toLowerCase()))
            .collect(Collectors.toList());
    if (false) {
      Thread t = threads.iterator().next();
      t.getName();
      t.getState();
    }

    // memory
    Runtime rt = Runtime.getRuntime();
    long total = rt.totalMemory();
    long free = rt.freeMemory();
    memUsed = bytesToMB(total - free);
    memTotal = bytesToMB(total);
  }

  public void apply(Model model) {
    super.apply(model);
    model.addAttribute("threads", threads);
    model.addAttribute("memUsed", memUsed);
    model.addAttribute("memTotal", memTotal);
  }

  private static long bytesToMB(long bytes) {
    return Math.round(bytes / (1024L * 1024L));
  }
}
