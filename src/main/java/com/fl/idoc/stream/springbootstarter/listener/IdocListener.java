package com.fl.idoc.stream.springbootstarter.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 消费者
 *
 * @author david
 */

@Slf4j
public class IdocListener implements InitializingBean {

	private IdocListenerSupport idocListenerSupport;

	public void setIdocListenerSupport(IdocListenerSupport idocListenerSupport) {
		this.idocListenerSupport = idocListenerSupport;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	private ObjectMapper objectMapper;

	public String process(String content) {

		String sendMessage = null;
		log.info("Receiver-queue:si.idoc.queue--> : {}", content);

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
					log.warn("MESTYP is Empty in json content");
				}
			} else {
				log.warn("MESTYP is not found in json content");
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

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(idocListenerSupport, "idocListenerSupport must be not null");
		Assert.notNull(objectMapper, "objectMapper must be not null");
	}
}
