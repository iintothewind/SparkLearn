package learn.scala

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.scalatest.funsuite.AnyFunSuite

class SparkBasicTest extends AnyFunSuite with LazyLogging {
  System.setProperty("hadoop.home.dir", "C:\\hadoop")

  test("basic01") {
    val logFile = "./build.sbt" // Should be some file on your system
    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local") // use local
      .getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }

  test("basic02") {
    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local") // use local
      .getOrCreate()
    val df = spark.read
      .format("csv")
      .option("header", "true")
      .option("delimiter", ",")
      .schema(StructType(Seq(
        StructField("country_id", IntegerType, true),
        StructField("country_code", StringType, true),
        StructField("country_name", StringType, true),
      )))
      .load("src/test/resources/country1.csv")

    df.printSchema()

    df.show()

    df.select("country_name").show()


    df.filter(col("country_id") < 5).show()

    import spark.implicits._
    df.filter($"country_id" < 5).show()

    df.createOrReplaceTempView("country")

    val sqlDf: DataFrame = spark.sql("select * from country")
    sqlDf.show()

    spark.stop()
  }

}
