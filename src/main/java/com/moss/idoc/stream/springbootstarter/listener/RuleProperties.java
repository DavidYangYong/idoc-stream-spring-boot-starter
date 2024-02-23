package com.moss.idoc.stream.springbootstarter.listener;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author david
 * @date 2021-01-08 10:10
 **/
@ConfigurationProperties(prefix = "com.fl.cloud.idoc.stream")
public class RuleProperties {

	private boolean idocContentNotConvert;

	public boolean getIdocContentNotConvert() {
		return idocContentNotConvert;
	}

	public void setIdocContentNotConvert(boolean idocContentNotConvert) {
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
