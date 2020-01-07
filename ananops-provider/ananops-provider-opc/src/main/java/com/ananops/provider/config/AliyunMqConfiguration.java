/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AliyunMqConfiguration.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.config;

import com.ananops.PublicUtil;
import com.ananops.base.constant.AliyunMqTopicConstants;
import com.ananops.base.constant.GlobalConstant;
import com.ananops.config.properties.AnanopsProperties;
import com.ananops.provider.consumer.listener.OptPushMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

/**
 * The class Aliyun mq configuration.
 *
 * @author ananops.com@gmail.com
 */
@Slf4j
@Configuration
public class AliyunMqConfiguration {

	@Resource
	private AnanopsProperties ananOpsProperties;

	@Resource
	private OptPushMessageListener optPushConsumer;

	@Resource
	private TaskExecutor taskExecutor;

	/**
	 * Default mq push consumer default mq push consumer.
	 *
	 * @return the default mq push consumer
	 *
	 * @throws MQClientException the mq client exception
	 */
	@Bean
	public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(ananOpsProperties.getAliyun().getRocketMq().getConsumerGroup());
		consumer.setNamesrvAddr(ananOpsProperties.getAliyun().getRocketMq().getNamesrvAddr());
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

		String[] strArray = AliyunMqTopicConstants.ConsumerTopics.OPT.split(GlobalConstant.Symbol.COMMA);
		for (String aStrArray : strArray) {
			String[] topicArray = aStrArray.split(GlobalConstant.Symbol.AT);
			String topic = topicArray[0];
			String tags = topicArray[1];
			if (PublicUtil.isEmpty(tags)) {
				tags = "*";
			}
			consumer.subscribe(topic, tags);
			log.info("RocketMq OpcPushConsumer topic = {}, tags={}", topic, tags);
		}

		consumer.registerMessageListener(optPushConsumer);
		consumer.setConsumeThreadMax(2);
		consumer.setConsumeThreadMin(2);

		taskExecutor.execute(() -> {
			try {
				Thread.sleep(5000);
				consumer.start();
				log.info("RocketMq OpcPushConsumer OK.");
			} catch (InterruptedException | MQClientException e) {
				log.error("RocketMq OpcPushConsumer, 出现异常={}", e.getMessage(), e);
			}
		});
		return consumer;
	}

}
