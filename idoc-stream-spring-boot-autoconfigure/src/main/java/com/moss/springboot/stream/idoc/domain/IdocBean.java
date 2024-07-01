package com.moss.springboot.stream.idoc.domain;

/**
 * @version V0.1
 * @项目名称：jnc-codecenter
 * @类名称：IdocBean
 * @类描述：
 * @author：justin
 * @创建时间：2019-10-19 10:40
 */
public class IdocBean {

  /**
   * e1mbxyh :
   * {"e1mbxyi":[{"bwart":"602","werks":"6190","gsber":"0001","grund":"0000","waers":"cny",
   * "aplzl":"00000000","smblp":"0001","exvkw":"0","vfdat":"00000000","ablad":"厦门市和通路115\u2014119号中间简易仓库",
   * "ps_psp_pnr":"00000000","bwlvs":"000","matnr":"000000001016000047","wempf":"0010011996",
   * "paobjnr":"0000000000","smbln":"4900172730","charg":"13068","kunnr":"0010011996","sjahr":"2019","erfmg":"30",
   * "segment":"1","erfme":"car","lgort":"6190","ebelp":"00000","lfpos":"0000","aufps":"0000","rsnum":"0000000000",
   * "kzbew":"l","e1mbxyj":{"mjahr":"2019","segment":"1","bstmg":"0","urzei":"0001","mblnr":"4900172731",
   * "menge":"180","pargb":"0001","mblpo":"0001","meins":"bot"},"shkzg":"s","lfbja":"0000","kdein":"0000",
   * "dabrz":"00000000","bstmg":"0","dmbtr":"0","aufpl":"0000000000","rspos":"0000","kdpos":"000000","bpmng":"0",
   * "exbwr":"0"},{"bwart":"602","werks":"6190","gsber":"0001","grund":"0000","waers":"cny","aplzl":"00000000",
   * "smblp":"0002","exvkw":"0","vfdat":"00000000","ablad":"厦门市和通路115\u2014119号中间简易仓库","ps_psp_pnr":"00000000",
   * "bwlvs":"000","matnr":"000000001016000049","wempf":"0010011996","paobjnr":"0000000000","smbln":"4900172730",
   * "charg":"14261","kunnr":"0010011996","sjahr":"2019","erfmg":"74","segment":"1","erfme":"car","lgort":"6190",
   * "ebelp":"00000","lfpos":"0000","aufps":"0000","rsnum":"0000000000","kzbew":"l","e1mbxyj":{"mjahr":"2019",
   * "segment":"1","bstmg":"0","urzei":"0002","mblnr":"4900172731","menge":"444","pargb":"0001","mblpo":"0002",
   * "meins":"bot"},"shkzg":"s","lfbja":"0000","kdein":"0000","dabrz":"00000000","bstmg":"0","dmbtr":"0",
   * "aufpl":"0000000000","rspos":"0000","kdpos":"000000","bpmng":"0","exbwr":"0"},{"bwart":"602","werks":"6190",
   * "gsber":"0001","grund":"0000","waers":"cny","aplzl":"00000000","smblp":"0003","exvkw":"0","vfdat":"00000000",
   * "ablad":"厦门市和通路115\u2014119号中间简易仓库","ps_psp_pnr":"00000000","bwlvs":"000","matnr":"000000001016000065",
   * "wempf":"0010011996","paobjnr":"0000000000","smbln":"4900172730","charg":"14246","kunnr":"0010011996",
   * "sjahr":"2019","erfmg":"50","segment":"1","erfme":"car","lgort":"6190","ebelp":"00000","lfpos":"0000",
   * "aufps":"0000","rsnum":"0000000000","kzbew":"l","e1mbxyj":{"mjahr":"2019","segment":"1","bstmg":"0",
   * "urzei":"0003","mblnr":"4900172731","menge":"600","pargb":"0001","mblpo":"0003","meins":"bot"},"shkzg":"s",
   * "lfbja":"0000","kdein":"0000","dabrz":"00000000","bstmg":"0","dmbtr":"0","aufpl":"0000000000","rspos":"0000",
   * "kdpos":"000000","bpmng":"0","exbwr":"0"}],"xblnr":"0080127114","segment":"1","bldat":"20151130",
   * "usnam":"zhangxian","budat":"20190330"} edi_dc40 :
   * {"serial":"20190419103217","rcvprn":"wlm","segment":"1","docrel":"700","sndprt":"ls",
   * "sndpor":"sapdev","mestyp":"wmmbxy","rcvprt":"ls","rcvpor":"tmsport01","docnum":"0000000000769744",
   * "rcvpfc":"ls","sndprn":"devclnt200","tabnam":"edi_dc40","outmod":"2","mandt":"200","status":"30",
   * "idoctyp":"wmmbid02","credat":"20190419","cretim":"103217","direct":"1"} begin : 1
   */

  private EdiDc40Bean edi_dc40;
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
}
