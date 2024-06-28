package com.moss.springboot.stream.idoc.factory;


import com.moss.springboot.stream.idoc.service.base.IBaseExecService;
import com.moss.springboot.stream.idoc.service.base.IBaseTaskService;
import com.moss.springboot.stream.idoc.strategy.HandlerContext;

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
		return handlerContext.getInstanceExecService(mesTyp);
	}

	public IBaseTaskService createBaseTask(String mesTyp) {
		if (handlerContext == null) {
			return null;
		}
		return handlerContext.getInstanceTaskService(mesTyp);
	}
}
