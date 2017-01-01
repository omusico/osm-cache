package com.ponkotuy.actors

import akka.actor.Actor
import com.ponkotuy.models.TileImage
import scalikejdbc.AutoSession

class SaveImageActor extends Actor {
  implicit val session = AutoSession
  val ImageMinSize = 150 // broken tile if smaller than this
  def receive = {
    case Update(tile) =>
      if(ImageMinSize < tile.image.length)
        TileImage.updateBy(tile.where)
            .withAttributes('image -> tile.image, 'created -> System.currentTimeMillis())
    case Create(tile) =>
      if(ImageMinSize < tile.image.length)
        TileImage.create(tile)
  }
}

case class Update(tile: TileImage)
case class Create(tile: TileImage)
