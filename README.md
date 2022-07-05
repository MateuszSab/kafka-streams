# kafka-streams 

Kafka Streams application that does the following transformations:

1. Words to upper case letters
2. Multiplies positive numbers by a number given on command line
3. Adds “negative number: “ prefix to negative numbers

## dependencies 

scalaVersion := "2.13.8" 

Kafka Steams

## run kafka

kafka zookeeper

kafka server

### name of a topic suggest type of input/output (String or Int)

kafka producer --topic `inputW` (for example)

kafka producer --topic `outputW` (for example)

kafka consumer --topic `inputINT` (for example)

kafka consumer --topic `outputINT` (for example)

## To run application:

### application arguments

1. multiplication: -m number to multiple 
2. topicInputW: -t input topic for words
3. topicInputNum: -n output topic for words
4. topicOutW: -p input topic for numbers
5. topicOutNum: -o output topic for numbers

Example od terminal command: `sbt 'run -m 5 -t inputW -n outputW -p inputINT -o outputINT'`

Or modify run configuration of a file Main and run from your IDE