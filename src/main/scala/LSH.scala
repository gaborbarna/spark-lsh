package com.lesbroot.sparklsh

import org.apache.spark.mllib.linalg.SparseVector
import org.apache.spark.rdd.RDD


class LSH(n: Int, p: Int, m: Int, bands: Int) extends Serializable {
  val hashers = (0 until n).map(i => (Hash.getHasher(p, m), i))

  def fit(data: RDD[SparseVector], minClusterSize: Int = 2) = {
    val bands = getBands(data)
    bands.filter({case (_, v) => v.size >= minClusterSize}).map({
      case (_, v) => v.sorted
    }).distinct()
  }

  def getBands(data: RDD[SparseVector]) = {
    val dataWithIndex = data.zipWithIndex().cache()
    dataWithIndex.flatMap({case (v, v_i) => {
      hashers.map({case (h, h_i) => ((v_i, h_i % bands), (Vector(Hash.minHash(v, h)), v))})
    }}).reduceByKey({case ((acc, _), (h, v)) => (acc ++ h, v)}).map({case ((v_i, band), (h, v)) => {
      ((band, h), v)
    }}).groupByKey().cache()
  }
}
