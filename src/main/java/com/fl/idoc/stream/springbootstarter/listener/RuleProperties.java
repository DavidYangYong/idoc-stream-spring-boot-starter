package com.fl.idoc.stream.springbootstarter.listener;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author david
 * @date 2021-01-08 10:10
 **/
@Component
@ConfigurationProperties(prefix = "fl.cloud.idoc.stream")
public class RuleProperties {

	private List<String> idocRules;

	private  String handlePackage;

	public String getHandlePackage() {
		return handlePackage;
	}

	public void setHandlePackage(String handlePackage) {
		this.handlePackage = handlePackage;
	}

	public List<String> getIdocRules() {
		return idocRules;
	}

	public void setIdocRules(List<String> idocRules) {
		this.idocRules = idocRules;
	}
}
