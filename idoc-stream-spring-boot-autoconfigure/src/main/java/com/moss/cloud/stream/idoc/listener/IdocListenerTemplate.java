package com.moss.cloud.stream.idoc.listener;

import com.moss.cloud.stream.idoc.domain.RuleProperties;
import com.moss.cloud.stream.idoc.service.base.IBaseExecService;
import com.moss.cloud.stream.idoc.service.base.IBaseTaskService;
import com.moss.cloud.stream.idoc.service.base.IdocMessageConverter;
import com.moss.cloud.stream.idoc.strategy.IdocExecFactory;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;

/**
 * @author david
 * @date 2019-10-19 08:20
 **/
@Slf4j
public class IdocListenerTemplate {

  private final IdocExecFactory idocExecFactory;
  private final RuleProperties ruleProperties;
  private IBaseTaskService baseTaskService;
  private IdocMessageConverter idocMessageConverter;

  public IdocListenerTemplate(IdocExecFactory idocExecFactory, RuleProperties ruleProperties) {
    Assert.notNull(ruleProperties, "ruleProperties must be not null");
    Assert.notNull(idocExecFactory, "idocExecFactory must be not null");
    this.ruleProperties = ruleProperties;
    this.idocExecFactory = idocExecFactory;
  }

  public void setBaseTaskService(IBaseTaskService baseTaskService) {
    this.baseTaskService = baseTaskService;
  }

  public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter) {
    this.idocMessageConverter = idocMessageConverter;
  }

  private boolean validationExecMesType(String mesTyp) {
    boolean b = false;
    List<String> idocRules = this.ruleProperties.getIdocRules();
    if (CollectionUtils.isNotEmpty(idocRules)) {
      Collection<String> selectList = idocRules.stream().filter(
              s -> StringUtils.equals(s, mesTyp))
          .collect(Collectors.toList());
      b = CollectionUtils.isNotEmpty(selectList);
    }
    return b;
  }


  String process(MessageHeaders messageHeaders, String idocContent, String mesType) {
    log.info("idoc Listener process start------- ");
    String sendMessage = null;
    IBaseExecService baseExecService = this.getiBaseExecService(
        mesType);
    if (baseExecService == null) {
      return null;
    }
    try {
      if (this.idocConvertVal(baseExecService)) {
        return baseExecService.idocContentConvert(idocContent);
      } else {
        Object t = baseExecService.execTemplate(idocContent);
        Object temp = baseExecService.exec(t);
        baseExecService.cacheObject(temp);
        boolean supportSendMessage = baseExecService.supportSendMessage();
        if (supportSendMessage) {
          baseExecService.setMessageHeaders(messageHeaders);
          sendMessage = baseExecService.sendMessage(temp);
          if (StringUtils.isNotEmpty(sendMessage) && this.baseTaskService != null) {
            this.baseTaskService.sendMessage(mesType, sendMessage);
          }
        }
      }
    } catch (RuntimeException e) {
      log.error("IdocListenerTemplate process fail:", e);
      throw new RuntimeException("IdocListenerTemplate process fail:", e);
    }
    log.info("idoc Listener process end------- ");
    return sendMessage;
  }

  private IBaseExecService getiBaseExecService(String mesType) {
    boolean validationExecMesType = this.validationExecMesType(mesType);

    if (!validationExecMesType) {
      log.warn("mesTyp {} is not find in idoc rules ", mesType);
      return null;
    }
    IBaseExecService baseExecService = this.idocExecFactory.createBaseExec(mesType);
    if (baseExecService == null) {
      log.warn("mesTyp is not find process class ");
      return null;
    }
    baseExecService.setIdocMessageConverter(this.idocMessageConverter);
    return baseExecService;
  }

  private boolean idocConvertVal(IBaseExecService<?> baseExecService) {
    return this.ruleProperties.getIdocContentNotConvert()
        && baseExecService.idocContentNotConvert();
  }
}
