/// *
// * Copyright 2015-2017 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.moss.springboot.stream.idoc;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.ArgumentMatchers.isNull;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.binder.Binder;
//import org.springframework.cloud.stream.binder.BinderFactory;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
///**
// * @author Marius Bogoevici
// * @author Janne Valkealahti
// */
//@ExtendWith(SpringExtension.class)
//// @checkstyle:off
//@SpringBootTest(classes = SinkBindingWithDefaultsTests.TestSink.class)
//// @checkstyle:on
//@ActiveProfiles("test")
//public class SinkBindingWithDefaultsTests {
//
//  @Autowired
//  private BinderFactory binderFactory;
//
//  @Autowired
//  private Sink testSink;
//
//  @SuppressWarnings({"rawtypes", "unchecked"})
//  @Test
//  public void testSourceOutputChannelBound() {
//    Binder binder = this.binderFactory.getBinder(null, MessageChannel.class);
//    verify(binder).bindConsumer(eq("input"), isNull(), eq(this.testSink.input()),
//        Mockito.any());
//    verifyNoMoreInteractions(binder);
//  }
//
//  @EnableBinding(Sink.class)
//  @EnableAutoConfiguration
//  public static class TestSink {
//
//  }
//
//}
