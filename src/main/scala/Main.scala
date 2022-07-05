import org.apache.kafka.streams.KafkaStreams
import scopt.OParser

object Main extends App {

//  val multiplication = args(0)
//  val topicInputW = args(1)
//  val topicInputNum = args(2)
//  val topicOutW = args(3)
//  val topicOutNum = args(4)

  case class Config(multiplication: String,
                    topicInputW: String,
                    topicInputNum: String,
                    topicOutW: String,
                    topicOutNum: String)

  val myBuilder = OParser.builder[Config]

  val myParser = {
    import myBuilder._
    OParser.sequence(
      programName("kafka-streams"),
      head("scopt", "1.0.0-alpha"),
      help("help").text("prints this usage text"),
      opt[String]('m', "multiplication")
        .action((value, cfg) => cfg.copy(multiplication = value))
        .text("number by which you multiply an input"),
      opt[String]('t', "topicInputW")
        .action((value, cfg) => cfg.copy(topicInputW = value))
        .text("input topic for the words"),
      opt[String]('n', "topicInputNum")
        .action((value, cfg) => cfg.copy(topicInputNum = value))
        .text("input topic for numbers"),
      opt[String]('p', "topicOutW")
        .action((value, cfg) => cfg.copy(topicOutW = value))
        .text("output topic for words"),
      opt[String]('o', "topicOutNum")
        .action((value, cfg) => cfg.copy(topicOutNum = value))
        .text("output  topic for numbers")
    )
  }

  val config = OParser.parse(myParser, args,
    Config(topicInputNum = "", topicInputW = "", topicOutNum = "",topicOutW = "", multiplication = "0")).getOrElse {
    println("Not enough or incorrect command-line arguments. Exiting...")
    sys.exit(-1)
  }

  val application = Application(config.multiplication, config.topicInputW, config.topicInputNum, config.topicOutW, config.topicOutNum)
  //  multiplication, topicInputW, topicInputNum, topicOutW, topicOutNum


  val streams1: KafkaStreams = new KafkaStreams(application.createTopologyW(config.topicInputW, config.topicOutW), application.props)
  streams1.start()

  val streams2: KafkaStreams = new KafkaStreams(application.createTopologyINT(config.multiplication, config.topicInputNum, config.topicOutNum), application.props)
  streams2.start()

  sys.ShutdownHookThread {
    streams1.close()
  }

  sys.ShutdownHookThread {
    streams2.close()
  }

}
