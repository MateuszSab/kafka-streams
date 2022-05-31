import org.apache.kafka.streams.KafkaStreams

object Main extends App {

  val application = Application(args(0), args(1), args(2), args(3), args(4))
//  multiplication, topicInputW, topicInputNum, topicOutW, topicOutNum
  val multiplication = args(0)
  val topicInputW = args(1)
  val topicInputNum = args(2)
  val topicOutW = args(3)
  val topicOutNum = args(4)

  val streams1: KafkaStreams = new KafkaStreams(application.createTopologyW(topicInputW, topicOutW), application.props)
  streams1.start()

  val streams2: KafkaStreams = new KafkaStreams(application.createTopologyINT(multiplication, topicInputNum, topicOutNum), application.props)
  streams2.start()

  sys.ShutdownHookThread {
    streams1.close()
  }

  sys.ShutdownHookThread {
    streams2.close()
  }

}
