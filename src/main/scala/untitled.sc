import java.util.Properties
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig, Topology}

case class Application(multiplication: String, topicInputW: String, topicInputNum: String, topicOutW: String, topicOutNum: String) {

  import org.apache.kafka.streams.scala._

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "Kafka App")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    p.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0)
    p
  }

  def createTopologyW(topicInputW: String, topicOutW: String): Topology = {
    val builder1 = new StreamsBuilder
    builder1
      .stream[String, String](topicInputW)
      .flatMapValues(text => text.toUpperCase.split("\\W+"))
      .to(topicOutW)
    builder1.build
  }

  def createTopologyINT(multiply: String, topicInputNum: String, topicOutNum: String): Topology = {
    val builder2 = new StreamsBuilder
    builder2
      .stream[String, String](topicInputNum)
      .mapValues(n => if (n.contains('-')) ("negative number: " + n.toInt * multiply.toInt) else (n.toInt * multiply.toInt).toString )
      .to(topicOutNum)
    builder2.build
  }



}
