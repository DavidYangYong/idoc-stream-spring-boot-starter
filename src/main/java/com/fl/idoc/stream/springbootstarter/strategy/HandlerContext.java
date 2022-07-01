package com.fl.idoc.stream.springbootstarter.strategy;

import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author david
 * @date 2019-10-31 10:39
 **/
public class HandlerContext {

	@Autowired
	private Map<String, IBaseExecService> handlerMap;

	public IBaseExecService getInstance(String mestyp) {
		Optional<IBaseExecService> optionalIBaseExecService = handlerMap.entrySet().stream()
				.filter(new Predicate<Entry<String, IBaseExecService>>() {
					@Override
					public boolean test(Entry<String, IBaseExecService> stringIBaseExecServiceEntry) {
						if (StringUtils.equals(stringIBaseExecServiceEntry.getValue().getMesType(), mestyp)) {
							return true;
						}
						return false;
					}
				}).map(Map.Entry::getValue).findFirst();

		if (optionalIBaseExecService == null) {
			throw new IllegalArgumentException("not found handler for type:" + mestyp);
		}
		return optionalIBaseExecService.get();
	}
}
