package com.moss.springboot.stream.idoc.core.support;

import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

/**
 * @author david
 * @create 2024-08-30 15:15
 * @Description:
 **/
public class DefaultBaseExecServiceMetadata implements  BaseExecServiceMetadata{

  private  Class<?> type;
  public DefaultBaseExecServiceMetadata(Class<?> type) {
    Assert.notNull(type, "type must be not null");
    this.type = type;
  }

  @Override
  public ResolvableType getResolvableType() {
    ResolvableType resolvableType = ResolvableType.forClass(type);
    ResolvableType anInterface = resolvableType.getInterfaces()[0];
    return anInterface.getGeneric(0);
  }
}
