package com.moss.cloud.stream.idoc.domain.principal;

import java.io.Serial;
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

  @Serial
  private static final long serialVersionUID = 1798884586633958825L;
  private  final  static String USER_NAME="IDOC";
  private final String username;

  public IdocUserPrincipal(String username) {
    Assert.notNull(username, "User name");
    this.username = username;
  }
  public IdocUserPrincipal() {
    this.username = USER_NAME;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdocUserPrincipal that)) {
      return false;
    }
    return Objects.equals(this.username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.username);
  }

  @Override
  public String getName() {
    return this.username;
  }

  @Override
  public String toString() {
    return "IdocUserPrincipal{" +
        "username='" + this.username + '\'' +
        '}';
  }
}
