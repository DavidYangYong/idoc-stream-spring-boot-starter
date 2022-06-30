package com.fl.idoc.stream.springbootstarter.strategy;

import com.fl.idoc.stream.springbootstarter.listener.RuleProperties;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author david
 * @date 2019-10-31 09:53
 **/
@Slf4j
public class HandlerProcessor implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Resource
	private RuleProperties ruleProperties;

	public HandlerContext createHandlerContext() {

		Map map = new HashMap<>(16);

		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false); // 不使用默认的TypeFilter
		provider.addIncludeFilter(new AnnotationTypeFilter(HandlerType.class));
		String handlePackage = ruleProperties.getHandlePackage();
		if (StringUtils.isNotEmpty(handlePackage)) {
			Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents(handlePackage);

			for (BeanDefinition beanDefinition : beanDefinitionSet) {
				try {
					String type = Class.forName(beanDefinition.getBeanClassName()).getAnnotation(HandlerType.class)
							.value();
					map.put(type, Class.forName(beanDefinition.getBeanClassName()));
				} catch (ClassNotFoundException e) {
					log.error("HandlerProcessor ClassNotFoundException", e);
				}
			}
		}
		HandlerContext handlerContext = new HandlerContext(applicationContext, map);
		return handlerContext;
	}
}
