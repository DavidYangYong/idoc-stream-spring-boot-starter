package com.fl.idoc.stream.springbootstarter.service.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.idoc.stream.springbootstarter.annotation.HandlerType;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author david
 * @date 2019-10-19 09:14
 **/
@Slf4j
public abstract class AbstractBaseExecService<T> implements IBaseExecService<T> {

	private Class<T> clazz;

	public AbstractBaseExecService(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T execTemplate(String idocContent) {
		return jsonConvert(idocContent);
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
		return "";
	}

	@Override
	public String idocContentConvert(String idocContent) {
		return idocContent;
	}

	@Autowired
	private ObjectMapper objectMapper;

	public T jsonConvert(String idocContent) {
		T tBase = null;
		try {
			tBase = objectMapper.readValue(idocContent, clazz);
			log.info("jsonConvert success");
		} catch (Exception e) {
			throw new RuntimeException("jsonConvert fail", e);
		}
		return tBase;
	}

	public String objectConvertJson(T t) {
		String json = "";
		try {
			json = objectMapper.writeValueAsString(t);
			log.info("构建后的对象（json格式）=={}", json);
		} catch (JsonProcessingException e) {
			log.error("构建后的对象（json格式) error ", e);
		}
		return json;
	}

	@Override
	public String getMesType() {
		HandlerType[] handlerTypes = this.getClass().getAnnotationsByType(HandlerType.class);
		if (handlerTypes.length > 0) {
			return handlerTypes[0].value();
		}
		return "";
	}
}
