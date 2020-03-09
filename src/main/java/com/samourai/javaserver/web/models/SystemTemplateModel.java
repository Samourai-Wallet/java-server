package com.samourai.javaserver.web.models;

import com.samourai.javaserver.utils.ServerUtils;
import java.util.Collection;
import java.util.Map;
import org.springframework.ui.Model;

public class SystemTemplateModel extends DashboardTemplateModel {
  public Collection<Thread> threads;
  public long memUsed;
  public long memTotal;
  public long startupTime;
  public Map<String, String> metrics;

  public SystemTemplateModel(String pageTitle, String logoTitle, Map<String, String> metrics) {
    super(pageTitle, logoTitle);

    this.threads = ServerUtils.getInstance().getThreads();
    if (false) { // template usage
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

    this.metrics = metrics;
  }

  public void setStartupTime(long startupTime) {
    this.startupTime = startupTime;
  }

  public void apply(Model model) {
    super.apply(model);
    model.addAttribute("systemModel", this);
  }

  private static long bytesToMB(long bytes) {
    return Math.round(bytes / (1024L * 1024L));
  }
}
