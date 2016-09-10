package com.ponkotuy

import org.openjdk.jmh.annotations._
import skinny.http.HTTP

import scala.io.Source
import scala.util.Try

@State(Scope.Thread)
class HttpClientSpeed {
  val lines = Source.fromFile("src/main/resources/tiles.csv").getLines()
  val tiles = lines.map(Tile.fromLine).flatMap(_.toOption)
  val urls = tiles.map(_.toURL).toList

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  def skinny(): Unit = {
    urls.foreach { url =>
      assert(HTTP.get(url).status == 200)
    }
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  def skinnyPar(): Unit = {
    urls.par.foreach { url =>
      assert(HTTP.get(url).status == 200)
    }
  }
}
case class Tile(x: Int, y: Int, zoom: Int) {
  def toURL: String = s"http://localhost:9001/maps/${zoom}/${x}/${y}.png"
}

object Tile {
  def fromLine(line: String): Try[Tile] = Try {
    val Array(x, y, zoom) = line.split(',')
    Tile(x.toInt, y.toInt, zoom.toInt)
  }
}
