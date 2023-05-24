package com.fl.idoc.stream.springbootstarter.listener;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author david
 * @date 2021-01-08 10:10
 **/
@ConfigurationProperties(prefix = "com.fl.cloud.idoc.stream")
public class RuleProperties {

	private Boolean idocContentNotConvert;

	public Boolean getIdocContentNotConvert() {
		return idocContentNotConvert;
	}

	public void setIdocContentNotConvert(Boolean idocContentNotConvert) {
		this.idocContentNotConvert = idocContentNotConvert;
	}

	private List<String> idocRules;

	public List<String> getIdocRules() {
		return idocRules;
	}

	public void setIdocRules(List<String> idocRules) {
		this.idocRules = idocRules;
	}
}
