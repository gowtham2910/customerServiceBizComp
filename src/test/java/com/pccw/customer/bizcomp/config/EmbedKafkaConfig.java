package com.pccw.customer.bizcomp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ltts.client.MessageBrokerClient;

@Configuration
public class EmbedKafkaConfig {

	@Bean
	public MessageBrokerClient getMessageBrokerClient() {
		class MockClient implements MessageBrokerClient {

			@Override
			public void consume(Object message) {
				// TODO Auto-generated method stub
			}

			@Override
			public <T> void produce(String topic, T message) {
				// TODO Auto-generated method stub
			}
		}
		return new MockClient();
	}

}
