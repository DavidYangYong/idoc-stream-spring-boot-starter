package com.fl.idoc.stream.springbootstarter.strategy;

import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author david
 * @date 2019-10-31 10:39
 **/
public class HandlerContext {

	@Autowired(required = false)
	private Map<String, IBaseExecService> handlerMap;

	public IBaseExecService getInstance(String mesTyp) {
		if (handlerMap == null) {
			return null;
		}
		Optional<IBaseExecService> baseExecServiceOptional = handlerMap.values().stream()
				.filter(iBaseExecService -> StringUtils.equals(iBaseExecService.getMesType(), mesTyp)).findFirst();

		return baseExecServiceOptional.orElse(null);
	}
}
