package learn.scala

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession
import org.scalatest.funsuite.AnyFunSuite


class SparkBasicTest extends AnyFunSuite with LazyLogging {
  test("basic01") {
    val logFile = "./build.sbt" // Should be some file on your system
    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local")
      //      .master("spark://localhost:7077")
      .getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }

}
