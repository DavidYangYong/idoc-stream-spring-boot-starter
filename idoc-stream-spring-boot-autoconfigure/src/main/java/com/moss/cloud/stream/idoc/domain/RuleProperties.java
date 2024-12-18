package com.moss.cloud.stream.idoc.domain;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author david
 * @date 2021-01-08 10:10
 **/
@ConfigurationProperties(prefix = RuleProperties.STREAMIDOC_PREFIX)
public class RuleProperties {

	public static final String STREAMIDOC_PREFIX = "com.moss.cloud.stream.idoc";
	private boolean enabled;
	private boolean idocContentNotConvert;
	private List<String> idocRules;

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getIdocContentNotConvert() {
		return idocContentNotConvert;
	}

	public void setIdocContentNotConvert(boolean idocContentNotConvert) {
		this.idocContentNotConvert = idocContentNotConvert;
	}

	public List<String> getIdocRules() {
		return idocRules;
	}

	public void setIdocRules(List<String> idocRules) {
		this.idocRules = idocRules;
	}
}
