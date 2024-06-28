package com.moss.springboot.stream.idoc.listener;

import com.moss.springboot.stream.idoc.domain.RuleProperties;
import com.moss.springboot.stream.idoc.factory.IdocExecFactory;
import com.moss.springboot.stream.idoc.service.base.IBaseExecService;
import com.moss.springboot.stream.idoc.service.base.IBaseTaskService;
import com.moss.springboot.stream.idoc.service.base.IdocMessageConverter;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @author david
 * @date 2019-10-19 08:20
 **/
@Slf4j
public class IdocListenerSupport {

  private final IdocExecFactory idocExecFactory;
  private final RuleProperties ruleProperties;
  private IdocMessageConverter idocMessageConverter;

  public IdocListenerSupport(IdocExecFactory idocExecFactory, RuleProperties ruleProperties) {
    this.ruleProperties = ruleProperties;
    this.idocExecFactory = idocExecFactory;
    Assert.notNull(ruleProperties, "ruleProperties must be not null");
    Assert.notNull(idocExecFactory, "idocExecFactory must be not null");
  }


  public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter) {
    this.idocMessageConverter = idocMessageConverter;
  }

  private boolean validationExecMesType(String mesTyp) {
    boolean b = false;
    List<String> idocRules = ruleProperties.getIdocRules();
    if (CollectionUtils.isNotEmpty(idocRules)) {
      Collection<String> selectList = CollectionUtils.select(idocRules, new EqualPredicate<>(mesTyp,
          DefaultEquator.defaultEquator()));
      b = CollectionUtils.isNotEmpty(selectList);
    }
    return b;
  }

  public String process(String idocContent, String mesType) {
    log.info("idoc Listener process start------- ");
    boolean b = validationExecMesType(mesType);
    String sendMessage = null;
    if (b) {
      IBaseExecService baseExecService = idocExecFactory.createBaseExec(mesType);
      try {
        if (baseExecService != null) {
          if (ruleProperties.getIdocContentNotConvert()
              && baseExecService.idocContentNotConvert()) {
            return baseExecService.idocContentConvert(idocContent);
          }
          baseExecService.setIdocMessageConverter(idocMessageConverter);
          Object t = baseExecService.execTemplate(idocContent);
          Object temp = baseExecService.exec(t);
          baseExecService.cacheObject(temp);
          boolean supportSendMessage = baseExecService.supportSendMessage();
          if (supportSendMessage) {
            sendMessage = baseExecService.sendMessage(temp);
            if (StringUtils.isNotEmpty(sendMessage)) {
              IBaseTaskService baseTaskService = idocExecFactory.createBaseTask(mesType);
              if (baseTaskService != null) {
                baseTaskService.sendMessage(sendMessage);
              }
            } else {
              log.warn("baseTaskService is null or sendMessage is empty!");
            }
          }
        } else {
          log.warn("mesTyp is not find process class ");
        }
      } catch (RuntimeException e) {
        log.error("IdocListenerSupport process fail:", e);
        throw new RuntimeException("IdocListenerSupport process fail:", e);
      }
    } else {
      log.warn("mesTyp {} is not find in idoc rules ", mesType);
    }
    log.info("idoc Listener process end------- ");
    return sendMessage;
  }
}
