package com.ponkotuy.models

import com.ponkotuy.http.Tile
import scalikejdbc._
import skinny.orm.{Alias, SkinnyNoIdCRUDMapper}

case class TileImage(x: Int, y: Int, zoom: Int, image: Array[Byte], created: Long = System.currentTimeMillis()) extends Tile

object TileImage extends SkinnyNoIdCRUDMapper[TileImage] {
  override def defaultAlias: Alias[TileImage] = createAlias("t")

  override def extract(rs: WrappedResultSet, n: ResultName[TileImage]): TileImage = autoConstruct(rs, n)

  def create(t: TileImage)(implicit session: DBSession): Unit = {
    createWithAttributes(
      'x -> t.x,
      'y -> t.y,
      'zoom -> t.zoom,
      'image -> t.image,
      'created -> System.currentTimeMillis()
    )
  }
}
