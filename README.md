# TKS
- Technologie Komponentów Sieciowych (Network Component Technologies)
- 2021
# Authors:
- Arkadiusz Remplewicz
- Marcin Mucha

# Running with IntelliJ IDEA & payara-micro-plugin

![obraz](https://user-images.githubusercontent.com/58391537/110693971-57c73180-81e8-11eb-9bed-183acb1bf61f.png)

- 1 package to generate war 
- 2 deploy using payara-micro
- 3 undeploy and stop payara-micro


## Kafka Configuration

- Download kafka 

https://www.apache.org/dyn/closer.cgi?path=/kafka/2.8.0/kafka_2.13-2.8.0.tgz

- Start Environment

`bin/zookeeper-server-start.sh config/zookeeper.properties`
`bin/kafka-server-start.sh config/server.properties`

* (CONFLUENT) start schema registyry

`bin/schema-registry-start ./etc/schema-registry/schema-registry.properties`


### Useful commands

- Create topic

`bin/kafka-topics.sh --create --topic TOPIC_NAME --bootstrap-server localhost:9092`

- List topics

`/bin/kafka-topics --list --bootstrap-server localhost:9092`

- Console Consumer (with keys)

`bin/kafka-console-consumer.sh --topic UserTopic --bootstrap-server localhost:9092 --property print.key=true`
