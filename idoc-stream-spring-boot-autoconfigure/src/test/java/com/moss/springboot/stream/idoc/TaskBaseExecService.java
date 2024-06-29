package com.moss.springboot.stream.idoc;

import com.moss.springboot.stream.idoc.service.base.IBaseTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author david
 * @create 2020-07-22 16:30
 **/
@Slf4j
@Service
public class TaskBaseExecService implements IBaseTaskService {


	@Override
	public void sendMessage(String messageType, String sendMessage) {
		log.info("外发Message to MQ：{}", sendMessage);
		if (StringUtils.isNotEmpty(sendMessage)) {//判断移动类型是否在枚举范围内
			//source.output().send(MessageBuilder.withPayload(sendMessage).build());
		} else {
			log.info("外发Message {}不在外发范围内，不做任何操作！", sendMessage);
		}
	}
}
