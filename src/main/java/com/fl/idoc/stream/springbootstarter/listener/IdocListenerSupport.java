package com.fl.idoc.stream.springbootstarter.listener;

import com.fl.idoc.stream.springbootstarter.factory.IdocExecFactory;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseTaskService;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @author david
 * @date 2019-10-19 08:20
 **/
@Slf4j
public class IdocListenerSupport {

	private IdocExecFactory idocExecFactory;

	private IBaseTaskService baseTaskService;

	private RuleProperties ruleProperties;

	public IBaseTaskService getBaseTaskService() {
		return baseTaskService;
	}

	public void setBaseTaskService(IBaseTaskService baseTaskService) {
		this.baseTaskService = baseTaskService;
	}

	public IdocListenerSupport(IdocExecFactory idocExecFactory, RuleProperties ruleProperties) {
		this.ruleProperties = ruleProperties;
		this.idocExecFactory = idocExecFactory;
		Assert.notNull(ruleProperties, "ruleProperties must be not null");
	}

	private boolean valiationExecMesType(String mestyp) {
		boolean b = false;
		List idocRules = ruleProperties.getIdocRules();
		if (CollectionUtils.isNotEmpty(idocRules)) {
			Collection selectList = CollectionUtils.select(idocRules, new Predicate<String>() {
				@Override
				public boolean evaluate(String s) {
					return StringUtils.equals(mestyp, s);
				}
			});
			b = CollectionUtils.isNotEmpty(selectList);
		}
		return b;
	}

	public String process(String idocContent, String mestyp) {
		log.info("idoc Listener process start------- ");
		boolean b = valiationExecMesType(mestyp);
		String sendMessage = null;
		if (b) {
			IBaseExecService baseExecService = idocExecFactory.createBaseExec(mestyp);
			try {
				if (baseExecService != null) {
					if (ruleProperties.getIdocContentNotConvert()) {
						String tempIdocContent = baseExecService.idocContentConvert(idocContent);
						return tempIdocContent;
					}
					Object t = baseExecService.execTemplate(idocContent);
					Object temp = baseExecService.exec(t);
					baseExecService.cacheObject(temp);
					boolean isSend = baseExecService.supportSendMessage();
					if (isSend) {
						sendMessage = baseExecService.sendMessage(temp);
						if (StringUtils.isNotEmpty(sendMessage) && baseTaskService != null) {
							baseTaskService.sendMessage(sendMessage);
						} else {
							log.warn("baseTaskService is null or sendMessage is empty!");
						}
					}
				} else {
					log.error("mestyp is not find process class ");
				}
			} catch (RuntimeException e) {
				log.error("IdocListenerSupport process fail:", e);
				throw new RuntimeException("IdocListenerSupport process fail:", e);
			}
		} else {
			log.warn("mestyp {} is not find in idoc rules ", mestyp);
		}
		log.info("idoc Listener process end------- ");
		return sendMessage;
	}
}
