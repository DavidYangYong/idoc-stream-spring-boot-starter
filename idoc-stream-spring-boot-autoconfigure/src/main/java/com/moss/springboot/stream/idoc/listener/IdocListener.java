package com.moss.springboot.stream.idoc.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;

/**
 * 消费者
 *
 * @author david
 */

@Slf4j
public class IdocListener implements InitializingBean {

	private IdocListenerSupport idocListenerSupport;
	private ObjectMapper objectMapper;

	public void setIdocListenerSupport(IdocListenerSupport idocListenerSupport) {
		this.idocListenerSupport = idocListenerSupport;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public String process(MessageHeaders messageHeaders, String content) {

		String sendMessage = null;
		log.info("Receiver-queue:si.idoc.queue--> : {}", content);

		try {
			String mesType = messageHeaders.get("message_type", String.class);

			if (StringUtils.isNotEmpty(mesType)) {
				log.info("MESTYP---> {}", mesType);
				String type = queryProcessMsgType(messageHeaders);
				if (StringUtils.isNotEmpty(type)) {
					log.info("queryProcessMsgType---> {}", type);
					sendMessage = idocListenerSupport.process(content, type);
				} else {
					log.warn("MESTYP is Empty in json content");
				}
			}
		} catch (Exception e) {
			log.error("idoc exec fail:", e);
			throw new RuntimeException(e);
		}
		if (StringUtils.isNotEmpty(sendMessage)) {
			log.info("idoc Listener send message String length------- {}", sendMessage.length());
		} else {
			log.info("idoc Listener send message String length------- is zero");
		}

		return sendMessage;
	}

	private String queryProcessMsgType(MessageHeaders messageHeaders) {
		String type = "";
		String mestyp = getValueByFind(messageHeaders, "message_type");
		String msgTypeTemp = "";
		if (StringUtils.isNotEmpty(mestyp)) {
			msgTypeTemp = mestyp;
		}
		String idocTypeTemp = "";
		String idocType = getValueByFind(messageHeaders, "idoc_type");
		if (StringUtils.isNotEmpty(idocType)) {
			idocTypeTemp = idocType;
			type = String.format("IDOC:%s:%s", msgTypeTemp, idocTypeTemp);
		}
		String cimType = getValueByFind(messageHeaders, "cim_type");
		if (StringUtils.isNotEmpty(cimType)) {
			String cimTypeTemp = cimType;
			if (StringUtils.isNotEmpty(cimTypeTemp)) {
				type = String.format("IDOC:%s:%s:%s", msgTypeTemp, idocTypeTemp, cimTypeTemp);
			}
		}
		return type;
	}

	@Deprecated
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
			String cimTypeTemp = cimTypes.get(0);
			if (StringUtils.isNotEmpty(cimTypeTemp)) {
				type = String.format("IDOC:%s:%s:%s", msgTypeTemp, idocTypeTemp, cimTypeTemp);
			}
		}
		return type;
	}

	@Deprecated
	/**
	 * 使用find的方法从实体中取出所有匹配的值
	 **/
	public List<String> getValueByFind(JsonNode node, String path) {
		List<String> values = new ArrayList<>();
		List<JsonNode> jsonNodeList = new ArrayList<>();
		node.findValues(path, jsonNodeList);
		Optional<JsonNode> optionalJsonNode = jsonNodeList.stream().findFirst();
		optionalJsonNode.ifPresent(jsonNode -> values.add(jsonNode.asText()));
		return values;
	}

	public String getValueByFind(MessageHeaders messageHeaders, String path) {
		return messageHeaders.get(path, String.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(idocListenerSupport, "idocListenerSupport must be not null");
		Assert.notNull(objectMapper, "objectMapper must be not null");
	}
}
