package com.moss.springboot.stream.idoc;

import com.moss.springboot.stream.idoc.annotation.HandlerType;
import com.moss.springboot.stream.idoc.service.base.IBaseExecService;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
				.filter(new Predicate<>() {
					@Override
					public boolean test(Entry<String, IBaseExecService> stringIBaseExecServiceEntry) {
						return org.apache.commons.lang3.StringUtils.equals(stringIBaseExecServiceEntry.getValue().getMesType(), "IDOC"
								+ ":ORDNTF"
								+ ":ORDINT01");
					}
				}).map(Map.Entry::getValue).findFirst();
		log.info("{}", optionalIBaseExecService.get());
		log.info("==============ClassMetadata==============");
		log.info(((ClassMetadata) metadata).getClassName());
		log.info(((ClassMetadata) metadata).getEnclosingClassName());
		log.info(StringUtils.arrayToCommaDelimitedString(((ClassMetadata) metadata).getMemberClassNames()));
		log.info(StringUtils.arrayToCommaDelimitedString(((ClassMetadata) metadata).getInterfaceNames()));
		log.info("{}", metadata.hasSuperClass());
		log.info(((ClassMetadata) metadata).getSuperClassName());

		log.info("{}", ((ClassMetadata) metadata).isAnnotation());
		log.info("{}", ((ClassMetadata) metadata).isFinal());
		log.info("{}", ((ClassMetadata) metadata).isIndependent());

		log.info("==============AnnotatedTypeMetadata==============");
		log.info("{}", metadata.isAnnotated(HandlerType.class.getName()));
		log.info("{}", metadata.isAnnotated(Component.class.getName()));

		log.info("{}", metadata.getAnnotationAttributes(HandlerType.class.getName()));
		log.info("{}", metadata.getAnnotationAttributes(Component.class.getName()));
		log.info("{}", metadata.getAnnotationAttributes(EnableAsync.class.getName()));

		log.info("{}", metadata.getAllAnnotationAttributes(HandlerType.class.getName()));
		log.info("{}", metadata.getAllAnnotationAttributes(Component.class.getName()));
		log.info("{}", metadata.getAllAnnotationAttributes(EnableAsync.class.getName()));

		log.info("==============AnnotationMetadata==============");
		log.info("{}", ((AnnotationMetadata) metadata).getAnnotationTypes());
		log.info("{}", metadata.getMetaAnnotationTypes(HandlerType.class.getName()));
		log.info("{}", metadata.getMetaAnnotationTypes(Component.class.getName()));

		log.info("{}", metadata.hasAnnotation(HandlerType.class.getName()));
		log.info("{}", metadata.hasAnnotation(Component.class.getName()));

		log.info("{}", metadata.hasMetaAnnotation(HandlerType.class.getName()));
		log.info("{}", metadata.hasMetaAnnotation(Component.class.getName()));

		log.info("{}", ((AnnotationMetadata) metadata).hasAnnotatedMethods(Autowired.class.getName()));
		((AnnotationMetadata) metadata).getAnnotatedMethods(Autowired.class.getName()).forEach(methodMetadata -> {
			log.info("{}", methodMetadata.getClass());
			log.info(methodMetadata.getMethodName());
			log.info(methodMetadata.getReturnTypeName());
		});
	}
}
