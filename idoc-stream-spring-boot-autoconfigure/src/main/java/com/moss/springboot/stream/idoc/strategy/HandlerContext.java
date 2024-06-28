package com.moss.springboot.stream.idoc.strategy;

import com.moss.springboot.stream.idoc.service.base.IBaseExecService;
import com.moss.springboot.stream.idoc.service.base.IBaseTaskService;
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
	private List<IBaseTaskService> baseTaskServiceList;

	public HandlerContext(List<IBaseExecService> baseExecServiceList,
			List<IBaseTaskService> baseTaskServiceList) {
		this.baseExecServiceList = baseExecServiceList;
		this.baseTaskServiceList = baseTaskServiceList;
	}

	public IBaseExecService getInstanceExecService(String mesTyp) {
		if (CollectionUtils.isEmpty(baseExecServiceList)) {
			return null;
		}
		Optional<IBaseExecService> baseExecServiceOptional = baseExecServiceList.stream()
				.filter(iBaseExecService -> StringUtils.equals(iBaseExecService.getMesType(), mesTyp)).findFirst();

		return baseExecServiceOptional.orElse(null);
	}

	public IBaseTaskService getInstanceTaskService(String mesTyp) {
		if (CollectionUtils.isEmpty(baseTaskServiceList)) {
			return null;
		}
		Optional<IBaseTaskService> baseExecServiceOptional = baseTaskServiceList.stream()
				.filter(iBaseTaskService -> StringUtils.equals(iBaseTaskService.getMesType(), mesTyp))
				.findFirst();

		return baseExecServiceOptional.orElse(null);
	}
}
