package com.ponkotuy.http

import com.ponkotuy.models.TileImage
import scalikejdbc._
import skinny.http._

import scala.util.Random

object TileOSMOrg {
  def tileURL(server: String, tile: Tile) =
    s"https://${server}.tile.openstreetmap.jp/${tile.zoom}/${tile.x}/${tile.y}.png"

  val Servers = Vector("j")

  def get(tile: Tile): TileImage = {
    val server = Servers(Random.nextInt(Servers.length))
    val url = tileURL(server, tile)
    val res = HTTP.get(url)
    if(res.status != 200) throw new GetTileError(url, res.status)
    tile.withImage(res.body)
  }

  class GetTileError(url: String, status: Int) extends RuntimeException(s"Error when GET ${url}. Status is ${status}")
}

trait Tile {
  def x: Int
  def y: Int
  def zoom: Int
  def withImage(image: Array[Byte]) = TileImage(x, y, zoom, image)
  lazy val where: SQLSyntax = {
    import com.ponkotuy.models.Aliases.t
    val r = sqls.eq(t.x, x).and.eq(t.y, y).and.eq(t.zoom, zoom)
    r
  }
}

case class TilePosition(x: Int, y: Int, zoom: Int) extends Tile
