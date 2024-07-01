package com.moss.springboot.stream.idoc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version V0.1
 * @项目名称：
 * @类名称：IdocBean
 * @类描述：
 * @author：david
 * @创建时间：2024-07-01 10:40
 */
public class IdocBean {

  @JsonProperty("EDI_DC40")
  private EdiDc40Bean edi_dc40;
  @JsonProperty("BEGIN")
  private String begin;

  public EdiDc40Bean getEdi_dc40() {
    return edi_dc40;
  }

  public void setEdi_dc40(EdiDc40Bean edi_dc40) {
    this.edi_dc40 = edi_dc40;
  }

  public String getBegin() {
    return begin;
  }

  public void setBegin(String begin) {
    this.begin = begin;
  }

  @Override
  public String toString() {
    return "IdocBean{" +
        "edi_dc40=" + edi_dc40 +
        ", begin='" + begin + '\'' +
        '}';
  }
}
