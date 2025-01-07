package com.moss.cloud.stream.idoc.strategy.spilt;

import java.util.List;

/**
 * @author david
 * @create 2025-01-07 16:55
 * @Description:
 **/
public interface SplitStrategy {

  public List<SpiltData> splitData(int totalQuantity, int maxQuantity, List<SpiltData> spiltDataList);
}
