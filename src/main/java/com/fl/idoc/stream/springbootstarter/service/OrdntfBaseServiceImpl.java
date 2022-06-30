package com.fl.idoc.stream.springbootstarter.service;

import com.fl.idoc.stream.springbootstarter.service.base.AbstractBaseExecService;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author david
 * @create 2021-01-07 10:07
 **/
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
@HandlerType(value = "IDOC:ORDNTF:ORDINT01")
public class OrdntfBaseServiceImpl extends AbstractBaseExecService<Object> implements IBaseExecService<Object> {


	public OrdntfBaseServiceImpl() {
		super(null);
	}

	@Override
	public Object exec(Object o) {
		return null;
	}
}
