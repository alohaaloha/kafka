# kafka-demo

# ZooKeeper

## Extract tar file
tar -zxf zookeeper-3.4.6.tar.gz

$ cd zookeeper-3.4.6

$ mkdir data

## Create Configuration File
$ vi conf/zoo.cfg

tickTime=2000

dataDir=/path/to/zookeeper/data

clientPort=2181

initLimit=5

syncLimit=2

## Start ZooKeeper Server
$ bin/zkServer.sh start

## Start CLI
$ bin/zkCli.sh

(stop: $ bin/zkServer.sh stop)

# Kafka

## Extract the tar file
tar -zxf kafka_2.11.0.9.0.0 tar.gz

## Start Server
You can start the server by giving the following command −

$ bin/kafka-server-start.sh config/server.properties

(stop: $ bin/kafka-server-stop.sh config/server.properties)