package com.fl.idoc.stream.springbootstarter.factory;


import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerContext;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerProcessor;

/**
 * @author david
 * @date 2019-10-19 08:34
 **/
public class IdocExecFactory {

	public IdocExecFactory(HandlerProcessor handlerProcessor) {
		this.handlerContext = handlerProcessor.createHandlerContext();
	}

	private HandlerContext handlerContext;

	public HandlerContext getHandlerContext() {
		return handlerContext;
	}

	public void setHandlerContext(HandlerContext handlerContext) {
		this.handlerContext = handlerContext;
	}

	public IBaseExecService createBaseExec(String mestyp) {
		if (handlerContext == null) {
			return null;
		}
		return handlerContext.getInstance(mestyp);
	}
}
