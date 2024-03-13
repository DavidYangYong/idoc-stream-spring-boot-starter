package com.moss.springboot.stream.idoc.service.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author david
 * @create 2024-01-19 14:38
 * @Description:
 **/
@Slf4j
public class DefaultIdocMessageConverter implements IdocMessageConverter {

	private ObjectMapper objectMapper;

	public DefaultIdocMessageConverter(ObjectMapper objectMapper) {

		this.objectMapper = objectMapper;
	}

	@Override
	public <T> T fromMessage(String idocMessage, Class<T> clazz) throws RuntimeException {
		T tBase = null;
		try {
			if (objectMapper != null) {
				tBase = objectMapper.readValue(idocMessage, clazz);
			}
			log.debug("jsonConvert success");
		} catch (Exception e) {
			throw new RuntimeException("jsonConvert fail", e);
		}
		return tBase;
	}


	public <T> String objectConvertJson(T t) {
		String json = null;
		try {
			if (objectMapper != null) {
				json = objectMapper.writeValueAsString(t);
			}
			log.info("构建后的对象（json格式）=={}", json);
		} catch (JsonProcessingException e) {
			log.error("构建后的对象（json格式) error ", e);
		}
		return json;
	}
}
