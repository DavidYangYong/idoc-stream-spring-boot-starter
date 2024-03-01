package com.moss.springboot.stream.idoc.strategy;

import com.moss.springboot.stream.idoc.service.base.IBaseExecService;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author david
 * @date 2019-10-31 10:39
 **/
public class HandlerContext {

	private List<IBaseExecService> baseExecServiceList;

	public HandlerContext(List<IBaseExecService> baseExecServiceList) {
		this.baseExecServiceList = baseExecServiceList;
	}

	public IBaseExecService getInstance(String mesTyp) {
		if (CollectionUtils.isEmpty(baseExecServiceList)) {
			return null;
		}
		Optional<IBaseExecService> baseExecServiceOptional = baseExecServiceList.stream()
				.filter(iBaseExecService -> StringUtils.equals(iBaseExecService.getMesType(), mesTyp)).findFirst();

		return baseExecServiceOptional.orElse(null);
	}
}
