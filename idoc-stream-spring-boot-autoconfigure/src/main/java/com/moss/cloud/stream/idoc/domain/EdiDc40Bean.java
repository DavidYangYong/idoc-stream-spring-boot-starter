package com.moss.cloud.stream.idoc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version V0.1
 * @项目名称：
 * @类名称：EdiDc40Bean
 * @类描述：
 * @author：david
 * @创建时间：2024-07-01 10:40
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EdiDc40Bean {

  @JsonProperty("SERIAL")
  private String serial;
  @JsonProperty("RCVPRN")
  private String rcvprn;
  @JsonProperty("SEGMENT")
  private String segment;
  @JsonProperty("DOCREL")
  private String docrel;
  @JsonProperty("SNDPRT")
  private String sndprt;
  @JsonProperty("SNDPOR")
  private String sndpor;
  @JsonProperty("MESTYP")
  private String mestyp;
  @JsonProperty("RCVPRT")
  private String rcvprt;
  @JsonProperty("RCVPOR")
  private String rcvpor;
  @JsonProperty("DOCNUM")
  private String docnum;
  @JsonProperty("RCVPFC")
  private String rcvpfc;
  @JsonProperty("SNDPRN")
  private String sndprn;
  @JsonProperty("TABNAM")
  private String tabnam;
  @JsonProperty("OUTMOD")
  private String outmod;
  @JsonProperty("MANDT")
  private String mandt;
  @JsonProperty("STATUS")
  private String status;
  @JsonProperty("IDOCTYP")
  private String idoctyp;
  @JsonProperty("CREDAT")
  private String credat;
  @JsonProperty("CRETIM")
  private String cretim;
  @JsonProperty("DIRECT")
  private String direct;
  @JsonProperty("CIMTYP")
  private String cimtyp;

  public String getCimtyp() {
    return cimtyp;
  }

  public void setCimtyp(String cimtyp) {
    this.cimtyp = cimtyp;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getRcvprn() {
    return rcvprn;
  }

  public void setRcvprn(String rcvprn) {
    this.rcvprn = rcvprn;
  }

  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public String getDocrel() {
    return docrel;
  }

  public void setDocrel(String docrel) {
    this.docrel = docrel;
  }

  public String getSndprt() {
    return sndprt;
  }

  public void setSndprt(String sndprt) {
    this.sndprt = sndprt;
  }

  public String getSndpor() {
    return sndpor;
  }

  public void setSndpor(String sndpor) {
    this.sndpor = sndpor;
  }

  public String getMestyp() {
    return mestyp;
  }

  public void setMestyp(String mestyp) {
    this.mestyp = mestyp;
  }

  public String getRcvprt() {
    return rcvprt;
  }

  public void setRcvprt(String rcvprt) {
    this.rcvprt = rcvprt;
  }

  public String getRcvpor() {
    return rcvpor;
  }

  public void setRcvpor(String rcvpor) {
    this.rcvpor = rcvpor;
  }

  public String getDocnum() {
    return docnum;
  }

  public void setDocnum(String docnum) {
    this.docnum = docnum;
  }

  public String getRcvpfc() {
    return rcvpfc;
  }

  public void setRcvpfc(String rcvpfc) {
    this.rcvpfc = rcvpfc;
  }

  public String getSndprn() {
    return sndprn;
  }

  public void setSndprn(String sndprn) {
    this.sndprn = sndprn;
  }

  public String getTabnam() {
    return tabnam;
  }

  public void setTabnam(String tabnam) {
    this.tabnam = tabnam;
  }

  public String getOutmod() {
    return outmod;
  }

  public void setOutmod(String outmod) {
    this.outmod = outmod;
  }

  public String getMandt() {
    return mandt;
  }

  public void setMandt(String mandt) {
    this.mandt = mandt;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getIdoctyp() {
    return idoctyp;
  }

  public void setIdoctyp(String idoctyp) {
    this.idoctyp = idoctyp;
  }

  public String getCredat() {
    return credat;
  }

  public void setCredat(String credat) {
    this.credat = credat;
  }

  public String getCretim() {
    return cretim;
  }

  public void setCretim(String cretim) {
    this.cretim = cretim;
  }

  public String getDirect() {
    return direct;
  }

  public void setDirect(String direct) {
    this.direct = direct;
  }

  @Override
  public String toString() {
    return "EdiDc40Bean{" +
        "serial='" + serial + '\'' +
        ", rcvprn='" + rcvprn + '\'' +
        ", segment='" + segment + '\'' +
        ", docrel='" + docrel + '\'' +
        ", sndprt='" + sndprt + '\'' +
        ", sndpor='" + sndpor + '\'' +
        ", mestyp='" + mestyp + '\'' +
        ", rcvprt='" + rcvprt + '\'' +
        ", rcvpor='" + rcvpor + '\'' +
        ", docnum='" + docnum + '\'' +
        ", rcvpfc='" + rcvpfc + '\'' +
        ", sndprn='" + sndprn + '\'' +
        ", tabnam='" + tabnam + '\'' +
        ", outmod='" + outmod + '\'' +
        ", mandt='" + mandt + '\'' +
        ", status='" + status + '\'' +
        ", idoctyp='" + idoctyp + '\'' +
        ", credat='" + credat + '\'' +
        ", cretim='" + cretim + '\'' +
        ", direct='" + direct + '\'' +
        '}';
  }
}
