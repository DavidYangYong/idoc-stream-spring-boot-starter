package com.moss.cloud.stream.idoc.strategy.spilt;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author david
 * @create 2025-01-07 16:58
 * @Description:
 **/
public class SpiltData {

  private String spiltKey;
  private int maxQuantity;
  private BigDecimal maxAmount;

  private boolean isSplitQuantity;
  private boolean isSplitAmount;

  public SpiltData() {
  }

  public boolean isSplitQuantity() {
    return this.isSplitQuantity;
  }

  public void setSplitQuantity(boolean splitQuantity) {
    this.isSplitQuantity = splitQuantity;
  }

  public boolean isSplitAmount() {
    return this.isSplitAmount;
  }

  public void setSplitAmount(boolean splitAmount) {
    this.isSplitAmount = splitAmount;
  }

  public BigDecimal getMaxAmount() {
    return this.maxAmount;
  }

  public void setMaxAmount(BigDecimal maxAmount) {
    this.maxAmount = maxAmount;
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
