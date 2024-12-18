package com.moss.cloud.stream.idoc.service.base;

/**
 * @author david
 * @create 2024-01-19 14:37
 * @Description:
 **/
public interface IdocMessageConverter {

	<T> T fromMessage(String idocMessage, Class<T> clazz) throws RuntimeException;

	public <T> String objectConvertJson(T t);
}
