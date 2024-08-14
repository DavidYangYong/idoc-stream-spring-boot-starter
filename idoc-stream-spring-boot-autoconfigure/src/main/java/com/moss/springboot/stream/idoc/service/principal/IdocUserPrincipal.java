package com.moss.springboot.stream.idoc.service.principal;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;
import org.springframework.util.Assert;

/**
 * @author david
 * @create 2024-08-14 09:58
 * @Description:
 **/
public class IdocUserPrincipal implements Principal, Serializable {

  private final String username;

  public IdocUserPrincipal(String username) {
    Assert.notNull(username, "User name");
    this.username = username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdocUserPrincipal that)) {
      return false;
    }
    return Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(username);
  }

  @Override
  public String getName() {
    return username;
  }

  @Override
  public String toString() {
    return "IdocUserPrincipal{" +
        "username='" + username + '\'' +
        '}';
  }
}
