package com.org.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
//@Component
public class KafkaConsumer {

    @KafkaListener(topicPartitions = @TopicPartition(topic = "test2", partitions = {"0"}))
    public void onMassage0(ConsumerRecord<Integer, String> record) {

        System.out.println("消费者test2,分区0-收到的消息"
                + "\t主题名称：" + record.topic()
                + "\t分区：" + record.partition()
                + "\t偏移量：" + record.offset()
                + "\t键：" + record.key()
                + "\t值：" + record.value());
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "test2", partitions = {"1"}))
    public void onMassage1(ConsumerRecord<Integer, String> record) {
        System.out.println("消费者test2,分区1-收到的消息"
                + "\t主题名称：" + record.topic()
                + "\t分区：" + record.partition()
                + "\t偏移量：" + record.offset()
                + "\t键：" + record.key()
                + "\t值：" + record.value());
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "test2", partitions = {"2"}))
    public void onMassage2(ConsumerRecord<Integer, String> record) {
        System.out.println("消费者test2,分区2-收到的消息"
                + "\t主题名称：" + record.topic()
                + "\t分区：" + record.partition()
                + "\t偏移量：" + record.offset()
                + "\t键：" + record.key()
                + "\t值：" + record.value());
    }
}
