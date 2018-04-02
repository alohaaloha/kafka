package kafapp.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class KConsumer {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092"; //"localhost:9092,localhost:9093,localhost:9094";
    private final static String GROUP_ID = "sample-group";

    public static Consumer<Long, String> createConsumer(String server, String group_identification){
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group_identification);
        //props.put("enable.auto.commit", "true");
        //props.put("auto.commit.interval.ms", "1000");
        //props.put("session.timeout.ms", "30000");
        return new KafkaConsumer<Long, String>(props);
    }

    public static void main(String[] args) throws Exception {

        // sample 1
        Consumer<Long, String> consumer = createConsumer(BOOTSTRAP_SERVERS, GROUP_ID);
        consumer.subscribe(Arrays.asList("test-topic")); // list of topics here.
        while (true) {
            ConsumerRecords<Long, String> records = consumer.poll(100);
            for (ConsumerRecord<Long, String> record : records) {
                System.out.println("--------------------------------------------------");
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                System.out.println("--------------------------------------------------");
            }
        }

        // sample 2

    }


}
