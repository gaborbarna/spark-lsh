package com.lesbroot.sparklsh

import org.apache.spark.mllib.linalg.SparseVector
import scala.util.Random


object Hash {
  private[sparklsh] def a(p: Int) = new Random().nextInt(p - 1) + 1

  private[sparklsh] def b(p: Int) = new Random().nextInt(p)

  private[sparklsh] def hash(p: Int, m: Int, a: Int, b: Int)(x: Int) = {
    (((a.longValue * x) + b) % p).intValue % m
  }

  def getHasher(p: Int, m: Int) = hash(p, m, a(p), b(p)) _

  def minHash(v: SparseVector, hasher: ((Int) => Int)) = v.indices.map(hasher(_)).min
}
