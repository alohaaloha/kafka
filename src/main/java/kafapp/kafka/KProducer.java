package kafapp.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html
 * https://dzone.com/articles/kafka-producer-in-java
 *
 * */
public class KProducer {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String CLIENT_ID  = "I'm-producer-01";

    private static Producer<Long, String> createProducer(String server, String identification) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, identification);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    static void sendMessage(Producer<Long, String> producer, String topic, String message, Long partition) throws Exception {
        try {
            final ProducerRecord<Long, String> record = new ProducerRecord<>(topic, partition, message);
            RecordMetadata metadata = producer.send(record).get();
            System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d)\n", record.key(), record.value(), metadata.partition(), metadata.offset());
        } finally {
            producer.flush();
            producer.close();
        }
    }

    public static void main(String[] args) throws Exception {
        //how many instances of producer?
        Producer<Long, String > producer = createProducer(BOOTSTRAP_SERVERS, CLIENT_ID);
        sendMessage(producer, "test-topic", "hello from the other side", 0l);
    }
}
