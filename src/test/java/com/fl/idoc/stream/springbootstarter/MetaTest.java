package com.fl.idoc.stream.springbootstarter;

import com.fl.idoc.stream.springbootstarter.annotation.HandlerType;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseExecService;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
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
public class MetaTest {

	@Test
	public void test1() {
		StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(OrdntfBaseServiceImpl.class, true);
		Map<String, IBaseExecService> handlerMap = new HashMap<>();
		OrdntfBaseServiceImpl ordntfBaseService = new OrdntfBaseServiceImpl();
		handlerMap.put("ordntfBaseServiceImpl", ordntfBaseService);
		String mesType = handlerMap.get("ordntfBaseServiceImpl").getMesType();
		System.out.println(mesType);
		Optional<IBaseExecService> optionalIBaseExecService = handlerMap.entrySet().stream()
				.filter(new Predicate<Entry<String, IBaseExecService>>() {
					@Override
					public boolean test(Entry<String, IBaseExecService> stringIBaseExecServiceEntry) {
						return org.apache.commons.lang3.StringUtils.equals(stringIBaseExecServiceEntry.getValue().getMesType(), "IDOC:ORDNTF"
								+ ":ORDINT01");
					}
				}).map(Map.Entry::getValue).findFirst();
		System.out.println(optionalIBaseExecService.get());
		System.out.println("==============ClassMetadata==============");
		ClassMetadata classMetadata = metadata;
		System.out.println(classMetadata.getClassName());
		System.out.println(classMetadata.getEnclosingClassName());
		System.out.println(StringUtils.arrayToCommaDelimitedString(classMetadata.getMemberClassNames()));
		System.out.println(StringUtils.arrayToCommaDelimitedString(classMetadata.getInterfaceNames()));
		System.out.println(classMetadata.hasSuperClass());
		System.out.println(classMetadata.getSuperClassName());

		System.out.println(classMetadata.isAnnotation());
		System.out.println(classMetadata.isFinal());
		System.out.println(classMetadata.isIndependent());

		System.out.println("==============AnnotatedTypeMetadata==============");
		AnnotatedTypeMetadata annotatedTypeMetadata = metadata;
		System.out.println(annotatedTypeMetadata.isAnnotated(HandlerType.class.getName()));
		System.out.println(annotatedTypeMetadata.isAnnotated(Component.class.getName()));

		System.out.println(annotatedTypeMetadata.getAnnotationAttributes(HandlerType.class.getName()));
		System.out.println(annotatedTypeMetadata.getAnnotationAttributes(Component.class.getName()));
		System.out.println(annotatedTypeMetadata.getAnnotationAttributes(EnableAsync.class.getName()));

		System.out.println(annotatedTypeMetadata.getAllAnnotationAttributes(HandlerType.class.getName()));
		System.out.println(annotatedTypeMetadata.getAllAnnotationAttributes(Component.class.getName()));
		System.out.println(annotatedTypeMetadata.getAllAnnotationAttributes(EnableAsync.class.getName()));

		System.out.println("==============AnnotationMetadata==============");
		AnnotationMetadata annotationMetadata = metadata;
		System.out.println(annotationMetadata.getAnnotationTypes());
		System.out.println(annotationMetadata.getMetaAnnotationTypes(HandlerType.class.getName()));
		System.out.println(annotationMetadata.getMetaAnnotationTypes(Component.class.getName()));

		System.out.println(annotationMetadata.hasAnnotation(HandlerType.class.getName()));
		System.out.println(annotationMetadata.hasAnnotation(Component.class.getName()));

		System.out.println(annotationMetadata.hasMetaAnnotation(HandlerType.class.getName()));
		System.out.println(annotationMetadata.hasMetaAnnotation(Component.class.getName()));

		System.out.println(annotationMetadata.hasAnnotatedMethods(Autowired.class.getName()));
		annotationMetadata.getAnnotatedMethods(Autowired.class.getName()).forEach(methodMetadata -> {
			System.out.println(methodMetadata.getClass());
			System.out.println(methodMetadata.getMethodName());
			System.out.println(methodMetadata.getReturnTypeName());
		});
	}
}
