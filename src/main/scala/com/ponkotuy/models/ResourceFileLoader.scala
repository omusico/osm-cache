package com.ponkotuy.models

import com.google.common.io.ByteStreams

import scala.collection.mutable

/**
  * resource内のFileをloadしてcacheしておく
  * heap注意
  */
class ResourceFileLoader {
  private[this] val cache = new mutable.WeakHashMap[String, Array[Byte]]()

  def apply(path: String): Array[Byte] = {
    cache.getOrElseUpdate(path, initLoad(path))
  }

  def initLoad(path: String): Array[Byte] = {
    Option(getClass.getResourceAsStream(path)).map(ByteStreams.toByteArray).getOrElse(Array.empty)
  }
}
