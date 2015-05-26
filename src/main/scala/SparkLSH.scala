package com.lesbroot.sparklsh

import org.apache.spark.{SparkContext, SparkConf}


object SparkLSH {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkLSH task")
    val sc = new SparkContext(conf)
    sc.stop()
  }
}
