package com.moss.idoc.stream.springbootstarter.service.base;

/**
 * @author david
 * @create 2024-01-19 14:37
 * @Description:
 **/
public interface IdocMessageConverter {

	Object fromMessage(String idocMessage, Class clazz) throws RuntimeException;

	public String objectConvertJson(Object t);
}
