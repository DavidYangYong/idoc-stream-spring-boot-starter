package com.fl.idoc.stream.springbootstarter.listener;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author david
 * @date 2021-01-08 10:10
 **/
@ConfigurationProperties(prefix = "fl.cloud.idoc.stream")
public class RuleProperties {

	private List<String> idocRules;
	public List<String> getIdocRules() {
		return idocRules;
	}

	public void setIdocRules(List<String> idocRules) {
		this.idocRules = idocRules;
	}
}
