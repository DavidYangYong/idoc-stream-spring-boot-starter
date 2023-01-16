package com.fl.idoc.stream.springbootstarter.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消费者
 *
 * @author david
 */

@Slf4j
public class IdocListener {

	@Autowired
	private IdocListenerSupport idocListenerSupport;

	public void setIdocListenerSupport(IdocListenerSupport idocListenerSupport) {
		this.idocListenerSupport = idocListenerSupport;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	private ObjectMapper objectMapper;

	public String process(String content) {
		log.info("Receiver-queue:si.idoc.queue--> : {}", content);
		String sendMessage = null;
		try {
			JsonNode jsonNode = objectMapper.readTree(content);

			List<String> mesTypes = getValueByFind(jsonNode, "MESTYP");
			if (CollectionUtils.isNotEmpty(mesTypes)) {
				log.info("MESTYP---> {}", mesTypes.get(0));
				String type = queryProcessMsgType(jsonNode);
				if (StringUtils.isNotEmpty(type)) {
					log.info("queryProcessMsgType---> {}", type);
					sendMessage = idocListenerSupport.process(content, type);
				} else {
					log.error("MESTYP is Empty in json content");
				}
			} else {
				log.error("MESTYP is not found in json content");
			}
		} catch (Exception e) {
			log.error("idoc exec fail:", e);
			throw new RuntimeException(e);
		}
		if (StringUtils.isNotEmpty(sendMessage)) {
			log.info("idoc Listener send message length------- {}", sendMessage.length());
		} else {
			log.info("idoc Listener send message length------- is zero");
		}

		return sendMessage;
	}

	private String queryProcessMsgType(JsonNode jsonNode) {
		String type = "";
		List<String> mesTypes = getValueByFind(jsonNode, "MESTYP");
		String msgTypeTemp = "";
		if (CollectionUtils.isNotEmpty(mesTypes)) {
			msgTypeTemp = mesTypes.get(0);
		}
		String idocTypeTemp = "";
		List<String> idocTypes = getValueByFind(jsonNode, "IDOCTYP");
		if (CollectionUtils.isNotEmpty(idocTypes)) {
			idocTypeTemp = idocTypes.get(0);
			type = String.format("IDOC:%s:%s", msgTypeTemp, idocTypeTemp);
		}
		List<String> cimTypes = getValueByFind(jsonNode, "CIMTYP");
		if (CollectionUtils.isNotEmpty(cimTypes)) {
			String temp = cimTypes.get(0);
			if (StringUtils.isNotEmpty(temp)) {
				type = String.format("IDOC:%s:%s:%s", msgTypeTemp, idocTypeTemp, temp);
			}
		}
		return type;
	}

	/**
	 * 使用find的方法从实体中取出所有匹配的值
	 **/
	public List<String> getValueByFind(JsonNode node, String path) {
		List<String> values = new ArrayList<>();
		node.findValuesAsText(path, values);
		return values;
	}
}
