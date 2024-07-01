package com.moss.springboot.stream.idoc.service.base;

/**
 * @author david
 * @create 2022-11-11 11:50
 * @Description:
 **/
public abstract class AbstractBaseExecSendToService<T, R> extends AbstractBaseExecService<T, R> {

	public AbstractBaseExecSendToService(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public R exec(T t) {
		return null;
	}

	@Override
	public boolean supportSendMessage() {
		return true;
	}

	public String sendMessage(T t) {
		return getIdocMessageConverter().objectConvertJson(t);
	}
}
