package com.moss.springboot.stream.idoc.service.base;

import com.moss.springboot.stream.idoc.annotation.HandlerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author david
 * @date 2019-10-19 09:14
 **/
@Slf4j
public abstract class AbstractBaseExecService<T, R> implements IBaseExecService<T, R> {

	private final Class<T> clazz;
	private IdocMessageConverter idocMessageConverter;

	public AbstractBaseExecService(Class<T> clazz) {
		this.clazz = clazz;
	}

	public IdocMessageConverter getIdocMessageConverter() {
		return idocMessageConverter;
	}

	public void setIdocMessageConverter(IdocMessageConverter idocMessageConverter) {
		this.idocMessageConverter = idocMessageConverter;
	}

	@Override
	public String idocContentConvert(String idocContent) {
		return idocContent;
	}

	/**
	 * idoc处理核心方法
	 *
	 * @param t idoc 对应 java 的转换类
	 * @return T idoc 对应 java 的转换类
	 */
	@Override
	public abstract R exec(T t);

	@Override
	public String getMesType() {
		HandlerType handlerType = AnnotationUtils.findAnnotation(this.getClass(), HandlerType.class);
		if (handlerType != null) {
			return handlerType.value();
		}
		return "";
	}
	@Override
	public T execTemplate(String idocContent) {
		return getIdocMessageConverter().fromMessage(idocContent, clazz);
	}
}
