package com.moss.idoc.stream.springbootstarter.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author david
 * @create 2022-07-01 08:47
 * @Description:
 **/
public class IdocStreamScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

	private final static String PACKAGE_NAME_KEY = "basePackages";

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
		AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(
				importingClassMetadata.getAnnotationAttributes(IdocStreamScan.class.getName()));
		if (mapperScanAttrs == null || mapperScanAttrs.isEmpty()) {
			return;
		}
		String[] basePackages = (String[]) mapperScanAttrs.get(PACKAGE_NAME_KEY);
		// 2. 找到指定包路径下所有添加了HandlerType注解的类，并且把这些类添加到IOC容器里面去
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry, false);
		scanner.setResourceLoader(resourceLoader);
		scanner.addIncludeFilter(new AnnotationTypeFilter(HandlerType.class));
		scanner.scan(basePackages);
	}
}
