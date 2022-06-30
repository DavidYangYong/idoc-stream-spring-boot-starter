package com.fl.idoc.stream.springbootstarter.strategy;

import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import java.util.Map;

/**
 * @author david
 * @date 2019-10-31 10:39
 **/
public class HandlerContext {

	private Map<String, IBaseExecService> handlerMap;

	public HandlerContext(Map<String, IBaseExecService> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public IBaseExecService getInstance(String mestyp) {

		IBaseExecService baseExecService = handlerMap.get(mestyp);
		if (baseExecService == null) {
			throw new IllegalArgumentException("not found handler for type:" + mestyp);
		}
		return baseExecService;
	}
}
