package com.moss.springboot.stream.idoc.core.support;

import org.springframework.core.ResolvableType;

/**
 * @author david
 * @create 2024-08-30 15:16
 * @Description:
 **/
public interface BaseExecServiceMetadata {
  default Class getDomainType() {
    return getResolvableType().resolve();
  }
  ResolvableType getResolvableType();
}
