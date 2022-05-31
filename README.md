# kafka-streams 

Kafka Streams application that does the following transformations:

1. Words to upper case letters
2. Multiplies positive numbers by a number given on command line
3. Adds “negative number: “ prefix to negative numbers

## dependencies 

scalaVersion := "2.13.8" 

Kafka Steams

## run

kafka zookeeper

kafka server

### name of a topic suggest type of input/output (String or Int)

kafka producer --topic `inputW` (for example)

kafka producer --topic `outputW` (for example)

kafka consumer --topic `inputINT` (for example)

kafka consumer --topic `outputINT` (for example)

### application arguments 

1. multiplication: number to multiple 
2. topicInputW: input topic for words
3. topicInputNum: output topic for words
4. topicOutW: input topic for numbers
5. topicOutNum: output topic for numbers

for example `sbt run 5 inputW outputW inputINT outputINT`