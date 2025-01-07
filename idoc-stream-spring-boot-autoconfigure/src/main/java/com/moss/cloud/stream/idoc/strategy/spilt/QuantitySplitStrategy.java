package com.moss.cloud.stream.idoc.strategy.spilt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author david
 * @create 2025-01-07 17:03
 * @Description:
 **/
public class QuantitySplitStrategy<T> implements SplitStrategy {

  @Override
  public List<SpiltData> splitData(int totalQuantity, int maxQuantity, List<SpiltData> spiltDataList) {
    List<SpiltData> spiltDataListTemp = new ArrayList<>();
    if (spiltDataList != null || spiltDataList.size() != 0) {
      if (totalQuantity > 0 && maxQuantity > 0 && totalQuantity >= maxQuantity) {
        for (SpiltData spiltData : spiltDataList) {
          spiltData.setMaxQuantity(Math.min(maxQuantity, totalQuantity));
          totalQuantity -= maxQuantity;
          spiltDataListTemp.add(spiltData);
        }
      }
    }
    return spiltDataListTemp;
  }
}
