package com.fl.idoc.stream.springbootstarter.service.base;

/**
 * @author david
 * @create 2022-11-11 11:50
 * @Description:
 **/
public abstract class AbstractBaseExecSendToService<T> extends AbstractBaseExecService<T> {

	public AbstractBaseExecSendToService(Class clazz) {
		super(clazz);
	}

	@Override
	public T exec(T t) {
		return null;
	}

	@Override
	public boolean supportSendMessage() {
		return true;
	}

	public String sendMessage(T t) {
		return objectConvertJson(t);
	}
}
