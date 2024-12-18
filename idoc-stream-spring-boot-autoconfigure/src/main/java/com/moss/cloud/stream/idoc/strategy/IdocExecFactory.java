package com.moss.cloud.stream.idoc.strategy;


import com.moss.cloud.stream.idoc.service.base.IBaseExecService;

/**
 * @author david
 * @date 2019-10-19 08:34
 **/
public class IdocExecFactory {

	private HandlerContext handlerContext;

	public IdocExecFactory(HandlerContext handlerContext) {
		this.handlerContext = handlerContext;
	}

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
