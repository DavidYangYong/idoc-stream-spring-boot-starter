package com.moss.idoc.stream.springbootstarter;

import com.moss.idoc.stream.springbootstarter.annotation.HandlerType;
import com.moss.idoc.stream.springbootstarter.service.base.IBaseExecService;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

/**
 * @author david
 * @create 2022-07-01 09:30
 * @Description:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MetaTest {

	@Test
	public void test1() {
		StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(OrdntfBaseServiceImpl.class, true);
		Map<String, IBaseExecService> handlerMap = new HashMap<>();
		OrdntfBaseServiceImpl ordntfBaseService = new OrdntfBaseServiceImpl();
		handlerMap.put("ordntfBaseServiceImpl", ordntfBaseService);
		String mesType = handlerMap.get("ordntfBaseServiceImpl").getMesType();
		log.info(mesType);
		Optional<IBaseExecService> optionalIBaseExecService = handlerMap.entrySet().stream()
				.filter(new Predicate<Entry<String, IBaseExecService>>() {
					@Override
					public boolean test(Entry<String, IBaseExecService> stringIBaseExecServiceEntry) {
						return org.apache.commons.lang3.StringUtils.equals(stringIBaseExecServiceEntry.getValue().getMesType(), "IDOC:ORDNTF"
								+ ":ORDINT01");
					}
				}).map(Map.Entry::getValue).findFirst();
		log.info("{}", optionalIBaseExecService.get());
		log.info("==============ClassMetadata==============");
		ClassMetadata classMetadata = metadata;
		log.info(classMetadata.getClassName());
		log.info(classMetadata.getEnclosingClassName());
		log.info(StringUtils.arrayToCommaDelimitedString(classMetadata.getMemberClassNames()));
		log.info(StringUtils.arrayToCommaDelimitedString(classMetadata.getInterfaceNames()));
		log.info("{}", classMetadata.hasSuperClass());
		log.info(classMetadata.getSuperClassName());

		log.info("{}", classMetadata.isAnnotation());
		log.info("{}", classMetadata.isFinal());
		log.info("{}", classMetadata.isIndependent());

		log.info("==============AnnotatedTypeMetadata==============");
		AnnotatedTypeMetadata annotatedTypeMetadata = metadata;
		log.info("{}", annotatedTypeMetadata.isAnnotated(HandlerType.class.getName()));
		log.info("{}", annotatedTypeMetadata.isAnnotated(Component.class.getName()));

		log.info("{}", annotatedTypeMetadata.getAnnotationAttributes(HandlerType.class.getName()));
		log.info("{}", annotatedTypeMetadata.getAnnotationAttributes(Component.class.getName()));
		log.info("{}", annotatedTypeMetadata.getAnnotationAttributes(EnableAsync.class.getName()));

		log.info("{}", annotatedTypeMetadata.getAllAnnotationAttributes(HandlerType.class.getName()));
		log.info("{}", annotatedTypeMetadata.getAllAnnotationAttributes(Component.class.getName()));
		log.info("{}", annotatedTypeMetadata.getAllAnnotationAttributes(EnableAsync.class.getName()));

		log.info("==============AnnotationMetadata==============");
		AnnotationMetadata annotationMetadata = metadata;
		log.info("{}", annotationMetadata.getAnnotationTypes());
		log.info("{}", annotationMetadata.getMetaAnnotationTypes(HandlerType.class.getName()));
		log.info("{}", annotationMetadata.getMetaAnnotationTypes(Component.class.getName()));

		log.info("{}", annotationMetadata.hasAnnotation(HandlerType.class.getName()));
		log.info("{}", annotationMetadata.hasAnnotation(Component.class.getName()));

		log.info("{}", annotationMetadata.hasMetaAnnotation(HandlerType.class.getName()));
		log.info("{}", annotationMetadata.hasMetaAnnotation(Component.class.getName()));

		log.info("{}", annotationMetadata.hasAnnotatedMethods(Autowired.class.getName()));
		annotationMetadata.getAnnotatedMethods(Autowired.class.getName()).forEach(methodMetadata -> {
			log.info("{}", methodMetadata.getClass());
			log.info(methodMetadata.getMethodName());
			log.info(methodMetadata.getReturnTypeName());
		});
	}
}
