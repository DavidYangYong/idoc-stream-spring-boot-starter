package com.moss.idoc.stream.springbootstarter.service.base;

import com.moss.idoc.stream.springbootstarter.annotation.HandlerType;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;

/**
 * @author david
 * @date 2019-10-19 09:14
 **/
@Slf4j
public abstract class AbstractBaseExecService<T> implements IBaseExecService<T> {

	private final Class<T> clazz;


	public AbstractBaseExecService(Class<T> clazz) {
		this.clazz = clazz;
	}

	private IdocMessageConverter idocMessageConverter;

	public IdocMessageConverter getIdocMessageConverter() {
		return idocMessageConverter;
	}

	public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter) {
		this.idocMessageConverter = idocMessageConverter;
	}

	@Override
	public T execTemplate(String idocContent) {
		return (T) getIdocMessageConverter().fromMessage(idocContent, clazz);
	}

	/**
	 * idoc处理核心方法
	 *
	 * @param t idoc 对应 java 的转换类
	 * @return T idoc 对应 java 的转换类
	 */
	@Override
	public abstract T exec(T t);

	@Override
	public Serializable cacheObject(T t) {
		return null;
	}

	@Override
	public boolean supportSendMessage() {
		return false;
	}

	@Override
	public String sendMessage(T t) {
		return null;
	}

	@Override
	public String idocContentConvert(String idocContent) {
		return idocContent;
	}


	@Override
	public String getMesType() {
		HandlerType[] handlerTypes = this.getClass().getAnnotationsByType(HandlerType.class);
		if (handlerTypes != null && handlerTypes.length > 0) {
			return handlerTypes[0].value();
		}
		return "";
	}
}
