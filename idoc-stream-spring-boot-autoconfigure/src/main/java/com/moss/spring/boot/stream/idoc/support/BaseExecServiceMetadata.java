package com.moss.spring.boot.stream.idoc.support;

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
