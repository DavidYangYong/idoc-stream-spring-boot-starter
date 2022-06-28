package com.fl.idoc.stream.springbootstarter.strategy;

import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.utils.SpringUtil;
import java.util.Map;

/**
 * @author david
 * @create 2019-10-31 10:39
 **/
public class HandlerContext {

	private Map<String, Class> handlerMap;

	public HandlerContext(Map<String, Class> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public IBaseExecService getInstance(String mestyp) {

		Class clazz = handlerMap.get(mestyp);
		if (clazz == null) {
			throw new IllegalArgumentException("not found handler for type:" + mestyp);
		}
		return (IBaseExecService) SpringUtil.getBean(clazz);
	}
}
