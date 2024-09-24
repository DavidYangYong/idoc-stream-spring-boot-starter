package com.moss.cloud.stream.idoc.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;

/**
 * 消费者
 *
 * @author david
 */

@Slf4j
public class IdocListener implements InitializingBean {

  private IdocListenerSupport idocListenerSupport;

  public void setIdocListenerSupport(IdocListenerSupport idocListenerSupport) {
    this.idocListenerSupport = idocListenerSupport;
  }

  private static final String mesTypeKey="message_type";
  private static final String idocTypeKey="idoc_type";
  private static final String cimTypeKey="cim_type";

  public String process(MessageHeaders messageHeaders, String content) {

    String sendMessage = null;
    log.info("Receiver-queue:si.idoc.queue--> : {}", content);

    try {
      String mesType = getValueByFind(messageHeaders,mesTypeKey);

      if (StringUtils.isNotEmpty(mesType)) {
        log.info("MESTYP---> {}", mesType);
        String type = queryProcessMsgType(messageHeaders);
        if (StringUtils.isNotEmpty(type)) {
          log.info("queryProcessMsgType---> {}", type);
          sendMessage = idocListenerSupport.process(messageHeaders, content, type);
        } else {
          log.warn("MESTYP is Empty in json content");
        }
      }
    } catch (Exception e) {
      log.error("idoc exec fail:", e);
      throw new RuntimeException(e);
    }
    if (StringUtils.isNotEmpty(sendMessage)) {
      log.info("idoc Listener send message String length------- {}", sendMessage.length());
    } else {
      log.info("idoc Listener send message String length------- is zero");
    }

    return sendMessage;
  }

  private String queryProcessMsgType(MessageHeaders messageHeaders) {
    String type = "";
    String mestyp = getValueByFind(messageHeaders, mesTypeKey);
    String msgTypeTemp = "";
    if (StringUtils.isNotEmpty(mestyp)) {
      msgTypeTemp = mestyp;
    }
    String idocTypeTemp = "";
    String idocType = getValueByFind(messageHeaders, idocTypeKey);
    if (StringUtils.isNotEmpty(idocType)) {
      type = String.format("IDOC:%s:%s", msgTypeTemp, idocType);
    }
    String cimType = getValueByFind(messageHeaders, cimTypeKey);
    if (StringUtils.isNotEmpty(cimType)) {
      if (StringUtils.isNotEmpty(cimType)) {
        type = String.format("IDOC:%s:%s:%s", msgTypeTemp, idocTypeTemp, cimType);
      }
    }
    return type;
  }

  public String getValueByFind(MessageHeaders messageHeaders, String path) {
    return messageHeaders.get(path, String.class);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(idocListenerSupport, "idocListenerSupport must be not null");
  }
}
