#Running Kafka on Windows:

The Java Environment variables should be set.
Got to the Apache Kafka downloads page and download the Scala 2.12 kafka_2.12-0.10.2.1.tgz
Unzip it.

#Start zookeeper-

\kafka_2.12-0.10.2.1>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

#Apache Kafka-

\kafka_2.12-0.10.2.1>.\bin\windows\kafka-server-start.bat .\config\server.properties

#Create a topic with name xxx-topic, that has only one partition & one replica.

\kafka_2.12-0.10.2.1>.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic javainuse-topic

#Create a producer to send message to the above created xxx-topic and send a message - Hello

\kafka_2.12-0.10.2.1>.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic javainuse-topic

Hello

#Start the consumer which listens to the topic xxx-topic we just created above. We will get the message we had sent using the producer

\kafka_2.12-0.10.2.1>.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic xxx-topic --from-beginning

or from any broker
\kafka_2.12-0.10.2.1>.\bin/windows\kafka-console-consumer.bat --zookeeper localhost:2181 --topic xxx-topic --from-beginning