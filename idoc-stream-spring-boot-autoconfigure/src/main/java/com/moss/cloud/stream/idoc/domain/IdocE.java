package com.moss.cloud.stream.idoc.domain;

import java.io.Serializable;

/**
 * @author david
 * @create 2024-12-31 08:55
 * @Description:
 **/
public class IdocE<T, R> {

  private boolean supportSendMessage;
  private boolean idocContentNotConvert;

  public IdocE(boolean idocContentNotConvert, boolean supportSendMessage) {
    this.idocContentNotConvert = idocContentNotConvert;
    this.supportSendMessage = supportSendMessage;
  }

  public Serializable cacheObject(T t) {
    return null;
  }

  public boolean supportSendMessage() {
    return false;
  }

  public String sendMessage(T t) {
    return null;
  }

  public boolean idocContentNotConvert() {
    return this.idocContentNotConvert;
  }
}
