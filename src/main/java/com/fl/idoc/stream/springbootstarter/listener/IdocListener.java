package com.fl.idoc.stream.springbootstarter.listener;

/**
 * @author david
 * @date 2019-10-15 16:45
 **/

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 消费者
 *
 * @author david
 */
@EnableBinding(Sink.class)
@Slf4j
public class IdocListener {

	@Autowired
	private IdocListenerSupport idocListenerSupport;
	@Autowired
	private ObjectMapper objectMapper;

	@StreamListener(Sink.INPUT)
	public void process(String content) {
		log.info("Receiver-queue:si.idoc.queue--> : {}", content);
		try {
			JsonNode jsonNode = objectMapper.readTree(content);

			List mestyp = getValueByFind(jsonNode, "MESTYP");
			if (CollectionUtils.isNotEmpty(mestyp)) {
				log.info("MESTYP---> {}", mestyp.get(0));
				String type = queryProcessMsgType(jsonNode);
				if (StringUtils.isNotEmpty(type)) {
					idocListenerSupport.process(content, type);
				} else {
					log.error("MESTYP is Empty in json content");
				}
			} else {
				log.error("MESTYP is not found in json content");
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			log.error("IdocListener process fail:", illegalArgumentException);
		} catch (Exception e) {
			log.error("idoc exec fail:", e);
		}
	}

	private String queryProcessMsgType(JsonNode jsonNode) throws Exception {
		String type = "";
		List mesTyps = getValueByFind(jsonNode, "MESTYP");
		String msgTypeTemp = "";
		if (CollectionUtils.isNotEmpty(mesTyps)) {
			msgTypeTemp = mesTyps.get(0).toString();
		}
		String idocTypeTemp = "";
		List idocTypes = getValueByFind(jsonNode, "IDOCTYP");
		if (CollectionUtils.isNotEmpty(idocTypes)) {
			idocTypeTemp = idocTypes.get(0).toString();
			type = String.format("IDOC:%s:%s", msgTypeTemp, idocTypeTemp);
		}
		List cimTypes = getValueByFind(jsonNode, "$..CIMTYP");
		if (CollectionUtils.isNotEmpty(cimTypes)) {
			String temp = cimTypes.get(0).toString();
			if (StringUtils.isNotEmpty(temp)) {
				type = String.format("IDOC:%s:%s:%s", msgTypeTemp, idocTypeTemp, temp);
			}
		}
		return type;
	}

	/**
	 * 使用find的方法从实体中取出所有匹配的值
	 **/
	public List<String> getValueByFind(JsonNode node, String path) throws Exception {
		List<String> values = new ArrayList<String>();
		node.findValuesAsText(path, values);
		return values;
	}
}