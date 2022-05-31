import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.apache.kafka.streams.{TopologyTestDriver}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import scala.jdk.CollectionConverters._

class ApplicationSpec extends AnyFunSpec with Matchers {
  private val stringSer = new StringSerializer()
  private val stringDes = new StringDeserializer()

  describe("Running topology") {
  }
  it("generate events TEXT") {
    val target = Application("5", "inputW", "outputW", "inputINT", "outputINT")
// "5", "inputW", "outputW", "inputINT", "outputINT"
    val topology = target.createTopologyW("inputW", "outputW")
    val driver = new TopologyTestDriver(topology, target.props)

    val inputTopic = driver.createInputTopic("inputW", stringSer, stringSer)
    val outputTopic = driver.createOutputTopic("outputW", stringDes, stringDes)

    inputTopic.pipeInput("key1", "value")

    val results = outputTopic.readKeyValuesToMap().asScala

    results should be(Map("key1" -> "VALUE"))
  }

  describe("Running topology") {
  }
  it("generate events INT") {
    val target = Application("5", "inputW", "outputW", "inputINT", "outputINT")

    val topology = target.createTopologyINT("5","inputINT", "outputINT")
    val driver = new TopologyTestDriver(topology, target.props)

    val inputTopic = driver.createInputTopic("inputINT", stringSer, stringSer)
    val outputTopic = driver.createOutputTopic("outputINT", stringDes, stringDes)

    inputTopic.pipeInput("key1", "1")

    val results = outputTopic.readKeyValuesToMap().asScala

    results should be(Map("key1" -> "5"))
  }

  describe("Running topology") {
  }
  it("generate events negative INT") {
    val target = Application("5", "inputW", "outputW", "inputINT", "outputINT")

    val topology = target.createTopologyINT("5","inputINT", "outputINT")
    val driver = new TopologyTestDriver(topology, target.props)

    val inputTopic = driver.createInputTopic("inputINT", stringSer, stringSer)
    val outputTopic = driver.createOutputTopic("outputINT", stringDes, stringDes)

    inputTopic.pipeInput("key1", "-1")

    val results = outputTopic.readKeyValuesToMap().asScala

    results should be(Map("key1" -> "negative number: -5"))
  }
}