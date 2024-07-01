package com.moss.springboot.stream.idoc.domain;

/**
 * @version V0.1
 * @项目名称：jnc-codecenter
 * @类名称：EdiDc40Bean
 * @类描述：
 * @author：justin
 * @创建时间：2019-10-19 10:40
 */
public class EdiDc40Bean {

  /**
   * serial : 20190419103217 rcvprn : wlm segment : 1 docrel : 700 sndprt : ls sndpor : sapdev
   * mestyp : wmmbxy rcvprt : ls rcvpor : tmsport01 docnum : 0000000000769744 rcvpfc : ls sndprn :
   * devclnt200 tabnam : edi_dc40 outmod : 2 mandt : 200 status : 30 idoctyp : wmmbid02 credat :
   * 20190419 cretim : 103217 direct : 1
   */

  private String serial;
  private String rcvprn;
  private String segment;
  private String docrel;
  private String sndprt;
  private String sndpor;
  private String mestyp;
  private String rcvprt;
  private String rcvpor;
  private String docnum;
  private String rcvpfc;
  private String sndprn;
  private String tabnam;
  private String outmod;
  private String mandt;
  private String status;
  private String idoctyp;
  private String credat;
  private String cretim;
  private String direct;

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
}
