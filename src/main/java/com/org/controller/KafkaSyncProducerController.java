package com.org.controller;

import com.org.util.SpringUtils;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class KafkaSyncProducerController {


    private KafkaTemplate<Integer, String> template;

    public KafkaTemplate GetUserMapper() {
        if (template == null) {
            template = (KafkaTemplate) SpringUtils.getBean("kafkaTemplate");
        }
        return template;
    }

    @GetMapping("send/sync/{massage}/{partition}")
    public String send(@PathVariable String massage, @PathVariable Integer partition) {
        final ListenableFuture<SendResult<Integer, String>> future = GetUserMapper().send("test2", partition, 0, massage);
        try {
            final SendResult<Integer, String> sendResult = future.get();
            final RecordMetadata metadata = sendResult.getRecordMetadata();
            System.out.println("生产者发起" + metadata.topic() + "\t" + metadata.partition() + "\t" + metadata.offset());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
