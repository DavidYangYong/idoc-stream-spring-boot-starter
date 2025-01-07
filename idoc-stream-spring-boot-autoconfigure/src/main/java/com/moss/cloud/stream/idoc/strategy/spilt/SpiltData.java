package com.moss.cloud.stream.idoc.strategy.spilt;

import java.util.Objects;

/**
 * @author david
 * @create 2025-01-07 16:58
 * @Description:
 **/
public class SpiltData {

  private String spiltKey;
  private int maxQuantity;

  public SpiltData(String spiltKey) {
    this.spiltKey = spiltKey;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SpiltData spiltData)) {
      return false;
    }
    return this.maxQuantity == spiltData.maxQuantity && Objects.equals(this.spiltKey, spiltData.spiltKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.spiltKey, this.maxQuantity);
  }

  public int getMaxQuantity() {
    return this.maxQuantity;
  }

  public void setMaxQuantity(int maxQuantity) {
    this.maxQuantity = maxQuantity;
  }

  public String getSpiltKey() {
    return this.spiltKey;
  }

  public void setSpiltKey(String spiltKey) {
    this.spiltKey = spiltKey;
  }
}
