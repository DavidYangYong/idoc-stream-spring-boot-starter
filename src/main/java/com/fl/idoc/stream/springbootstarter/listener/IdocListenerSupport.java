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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author david
 * @create 2019-10-19 08:20
 **/
@Slf4j
public class IdocListenerSupport {

	@Autowired
	private IdocExecFactory idocExecFactory;

	@Autowired(required = false)
	private IBaseTaskService baseTaskService;

	private RuleProperties ruleProperties;

	public IdocListenerSupport(RuleProperties ruleProperties) {
		this.ruleProperties = ruleProperties;
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

	public void process(String idocContent, String mestyp) {
		log.info("idoc Listener process start------- ");
		boolean b = valiationExecMesType(mestyp);
		if (b) {
			IBaseExecService baseExecService = idocExecFactory.createBaseExec(mestyp);
			try {
				if (baseExecService != null) {
					Object t = baseExecService.execTemplate(idocContent);
					baseExecService.cacheObject(baseExecService.exec(t));
					boolean isSend = baseExecService.supportSendMessage();
					if (isSend) {
						String sendMessage = baseExecService.sendMessage(t);
						if (StringUtils.isNotEmpty(sendMessage) && baseTaskService != null) {
							baseTaskService.sendMessage(sendMessage);
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
	}
}
