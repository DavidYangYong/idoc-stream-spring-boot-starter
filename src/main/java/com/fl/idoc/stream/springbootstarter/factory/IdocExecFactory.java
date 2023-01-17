package com.fl.idoc.stream.springbootstarter.factory;


import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerContext;

/**
 * @author david
 * @date 2019-10-19 08:34
 **/
public class IdocExecFactory {

	public IdocExecFactory(HandlerContext handlerContext) {
		this.handlerContext = handlerContext;
	}

	private HandlerContext handlerContext;

	public HandlerContext getHandlerContext() {
		return handlerContext;
	}

	public void setHandlerContext(HandlerContext handlerContext) {
		this.handlerContext = handlerContext;
	}

	public IBaseExecService createBaseExec(String mesTyp) {
		if (handlerContext == null) {
			return null;
		}
		return handlerContext.getInstance(mesTyp);
	}
}
