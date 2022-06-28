package com.fl.idoc.stream.springbootstarter.factory;


import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author david
 * @date 2019-10-19 08:34
 **/
@Component
public class IdocExecFactory {

	@Autowired
	private HandlerContext handlerContext;

	public IBaseExecService createBaseExec(String mestyp) {
		return handlerContext.getInstance(mestyp);
	}

}
