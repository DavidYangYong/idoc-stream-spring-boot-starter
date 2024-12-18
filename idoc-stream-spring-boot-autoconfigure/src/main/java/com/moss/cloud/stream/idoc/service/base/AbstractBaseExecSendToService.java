package com.moss.cloud.stream.idoc.service.base;

/**
 * @author david
 * @create 2022-11-11 11:50
 * @Description:
 **/
public abstract class AbstractBaseExecSendToService<T> extends AbstractBaseExecService<T> {

	public AbstractBaseExecSendToService(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public <R> R exec(T t) {
		return null;
	}

	@Override
	public boolean supportSendMessage() {
		return true;
	}

	@Override
	public String sendMessage(T t) {
		return getIdocMessageConverter().objectConvertJson(t);
	}
}
