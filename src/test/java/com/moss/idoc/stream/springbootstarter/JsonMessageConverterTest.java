package com.moss.idoc.stream.springbootstarter;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author david
 * @create 2024-01-19 08:27
 * @Description:
 **/
public class JsonMessageConverterTest {

	public static void main(String[] args) {
		MessageConverter messageConverter = new Jackson2JsonMessageConverter();
		Message message = null;
		messageConverter.fromMessage(message);
	}
}
