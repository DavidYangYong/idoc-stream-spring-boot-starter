package com.fl.idoc.stream.springbootstarter.strategy;

import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 * @author david
 * @date 2019-10-31 10:39
 **/
public class HandlerContext {

	private Map<String, Class> handlerMap;
	private ApplicationContext applicationContext;

	public HandlerContext(ApplicationContext applicationContext, Map<String, Class> handlerMap) {
		this.handlerMap = handlerMap;
		this.applicationContext = applicationContext;
	}


	public IBaseExecService getInstance(String mestyp) {
		Object object = applicationContext.getBean(handlerMap.get(mestyp));
		if (object instanceof IBaseExecService) {
			IBaseExecService baseExecService = (IBaseExecService) object;
			if (baseExecService == null) {
				throw new IllegalArgumentException("not found handler for type:" + mestyp);
			}
			return baseExecService;
		}
		return null;
	}
}
