package com.moss.cloud.stream.idoc.strategy.spilt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author david
 * @create 2025-01-07 17:03
 * @Description:
 **/
public class QuantitySplitStrategy<T> implements SplitStrategy {

  private final int maxQuantity;
  private int totalQuantity;

  public QuantitySplitStrategy(int totalQuantity, int maxQuantity) {
    this.totalQuantity = totalQuantity;
    this.maxQuantity = maxQuantity;
  }

  @Override
  public List<SpiltData> splitData() {
    List<SpiltData> spiltDataListTemp = new ArrayList<>();
    int index = 0;
    while (totalQuantity > 0) {

      SpiltData spiltData = new SpiltData();

      spiltData.setSpiltKey(String.valueOf(index));
      spiltData.setMaxQuantity(Math.min(maxQuantity, totalQuantity));
      spiltData.setSplitQuantity(this.isSplitQuantity());
      spiltDataListTemp.add(spiltData);

      totalQuantity -= maxQuantity;

      index++;

    }
    return spiltDataListTemp;
  }

  @Override
  public boolean isSplitQuantity() {
    return true;
  }
}
