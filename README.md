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

# Kafka

