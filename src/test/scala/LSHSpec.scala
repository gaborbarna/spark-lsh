package com.lesbroot.sparklsh


import org.scalatest._
import org.apache.spark.mllib.linalg.SparseVector
import sparktest.SparkTestUtils
import scala.util.Random


class LSHSpec extends SparkTestUtils with ShouldMatchers {

  def getRandomTestData(n: Int, m: Int, ones: Int) = {
    Seq.fill(n)(
      new SparseVector(m, Array.fill(ones)(Random.nextInt(m)), Array.fill(ones)(1.0))
    )
  }

  sparkTest("fit") {
    val data = sc.parallelize(getRandomTestData(1000, 25, 20))
    val lsh = new LSH(n=1000, p=65537, m=1000, bands=25)
    val fitted = lsh.fit(data)
    println(fitted.count())
  }
}


