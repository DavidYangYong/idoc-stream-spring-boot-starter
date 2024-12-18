package com.moss.cloud.stream.idoc.service.base;

import com.moss.cloud.stream.idoc.annotation.HandlerType;
import com.moss.cloud.stream.idoc.support.DefaultBaseExecServiceMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.messaging.MessageHeaders;

/**
 * @author david
 * @date 2019-10-19 09:14
 **/
@Slf4j
public abstract class AbstractBaseExecService<T> implements IBaseExecService<T> {

  private final Class<T> clazz;
  private IdocMessageConverter idocMessageConverter;

  private MessageHeaders messageHeaders;
  private  DefaultBaseExecServiceMetadata defaultBaseExecServiceMetadata = new DefaultBaseExecServiceMetadata(
      getClass());
  public AbstractBaseExecService() {
    clazz = defaultBaseExecServiceMetadata.getDomainType();
  }

  public AbstractBaseExecService(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public MessageHeaders getMessageHeaders() {
    return messageHeaders;
  }

  @Override
  public void setMessageHeaders(MessageHeaders messageHeaders) {
    this.messageHeaders = messageHeaders;
  }

  public IdocMessageConverter getIdocMessageConverter() {
    return idocMessageConverter;
  }

  @Override
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
  public abstract <R> R exec(T t);


  private HandlerType getHandlerType() {
    return AnnotationUtils.findAnnotation(getClass(), HandlerType.class);
  }

  @Override
  public String getMesType() {
    HandlerType handlerType = getHandlerType();
    return handlerType != null ? handlerType.value() : StringUtils.EMPTY;
  }

  @Override
  public boolean idocContentNotConvert() {
    HandlerType handlerType = getHandlerType();
    return handlerType == null || handlerType.idocContentNotConvert();
  }

  @Override
  public T execTemplate(String idocContent) {
    return getIdocMessageConverter().fromMessage(idocContent, clazz);
  }
}
