package com.moss.idoc.stream.springbootstarter.domain;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author david
 * @date 2021-01-08 10:10
 **/
@ConfigurationProperties(prefix = "com.moss.cloud.idoc.stream")
public class RuleProperties {

	private boolean enabled;

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
