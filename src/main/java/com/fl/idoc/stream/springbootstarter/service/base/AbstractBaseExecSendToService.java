package com.fl.idoc.stream.springbootstarter.service.base;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author david
 * @create 2022-11-11 11:50
 * @Description:
 **/
public abstract class AbstractBaseExecSendToService extends AbstractBaseExecService {

	public AbstractBaseExecSendToService(Class clazz) {
		super(clazz);
	}

	@Override
	public boolean supportSendMessage() {
		return true;
	}

	@SendTo(Source.OUTPUT)
	@Override
	public String sendMessage(Object o) {
		return objectConvertJson(o);
	}
}
