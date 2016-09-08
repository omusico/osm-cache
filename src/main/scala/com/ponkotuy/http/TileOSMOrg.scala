package com.ponkotuy.http

import skinny.http._

import scala.util.Random

object TileOSMOrg {
  def tileURL(server: String, tile: Tile) =
    s"http://${server}.tile.osm.org/${tile.zoom}/${tile.x}/${tile.y}.png"

  val Servers = Vector("a", "b", "c")

  def get(tile: Tile) = {
    val server = Servers(Random.nextInt(3))
    val url = tileURL(server, tile)
    HTTP.get(url)
  }
}

case class Tile(x: Int, y: Int, zoom: Int)
