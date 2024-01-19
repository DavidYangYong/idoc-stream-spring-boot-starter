package com.fl.idoc.stream.springbootstarter.listener;

import com.fl.idoc.stream.springbootstarter.factory.IdocExecFactory;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseTaskService;
import com.fl.idoc.stream.springbootstarter.service.base.IdocMessageConverter;
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

	private IBaseTaskService baseTaskService;

	private final RuleProperties ruleProperties;

	public void setBaseTaskService(IBaseTaskService baseTaskService) {
		this.baseTaskService = baseTaskService;
	}

	public IdocListenerSupport(IdocExecFactory idocExecFactory, RuleProperties ruleProperties) {
		this.ruleProperties = ruleProperties;
		this.idocExecFactory = idocExecFactory;
		Assert.notNull(ruleProperties, "ruleProperties must be not null");
		Assert.notNull(idocExecFactory, "idocExecFactory must be not null");
	}

	private boolean validationExecMesType(String mesTyp) {
		boolean b = false;
		List<String> idocRules = ruleProperties.getIdocRules();
		if (CollectionUtils.isNotEmpty(idocRules)) {
			Collection<String> selectList = CollectionUtils.select(idocRules, new EqualPredicate<>(mesTyp, DefaultEquator.defaultEquator()));
			b = CollectionUtils.isNotEmpty(selectList);
		}
		return b;
	}

	private IdocMessageConverter idocMessageConverter;

	public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter) {
		this.idocMessageConverter = idocMessageConverter;
	}

	public String process(String idocContent, String mesType) {
		log.info("idoc Listener process start------- ");
		boolean b = validationExecMesType(mesType);
		String sendMessage = null;
		if (b) {
			IBaseExecService baseExecService = idocExecFactory.createBaseExec(mesType);
			try {
				if (baseExecService != null) {
					if (ruleProperties.getIdocContentNotConvert()) {
						return baseExecService.idocContentConvert(idocContent);
					}
					baseExecService.setIdocMessageConverter(idocMessageConverter);
					Object t = baseExecService.execTemplate(idocContent);
					Object temp = baseExecService.exec(t);
					baseExecService.cacheObject(temp);
					boolean supportSendMessage = baseExecService.supportSendMessage();
					if (supportSendMessage) {
						sendMessage = baseExecService.sendMessage(temp);
						if (StringUtils.isNotEmpty(sendMessage) && baseTaskService != null) {
							baseTaskService.sendMessage(sendMessage);
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
