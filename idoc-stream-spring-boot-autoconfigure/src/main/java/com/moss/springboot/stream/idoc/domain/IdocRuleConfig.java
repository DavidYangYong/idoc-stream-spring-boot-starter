package com.moss.springboot.stream.idoc.domain;

/**
 * @author david
 * @create 2024-06-29 10:33
 * @Description:
 **/
public class IdocRuleConfig {

  private String idocRule;
  private boolean idocContentNotConvert;

  public String getIdocRule() {
    return idocRule;
  }

  public void setIdocRule(String idocRule) {
    this.idocRule = idocRule;
  }

  public boolean isIdocContentNotConvert() {
    return idocContentNotConvert;
  }

  public void setIdocContentNotConvert(boolean idocContentNotConvert) {
    this.idocContentNotConvert = idocContentNotConvert;
  }
}
