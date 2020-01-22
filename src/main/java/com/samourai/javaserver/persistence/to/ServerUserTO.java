package com.samourai.javaserver.persistence.to;

import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

@MappedSuperclass
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class ServerUserTO {
  private static final String PRIVILEGES_SEPARATOR = ";";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String login;

  private String passwordHash;

  private String privileges;

  public ServerUserTO() {}

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public List<String> getPrivilegesList() {
    return Arrays.asList(privileges.split(PRIVILEGES_SEPARATOR));
  }
}
