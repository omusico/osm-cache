package com.ponkotuy.actors

import akka.actor.Actor
import com.ponkotuy.models.TileImage
import scalikejdbc.AutoSession

class SaveImageActor extends Actor {
  implicit val session = AutoSession
  def receive = {
    case Update(tile) =>
      TileImage.updateBy(tile.where)
          .withAttributes('image -> tile.image, 'created -> System.currentTimeMillis())
    case Create(tile) =>
      TileImage.create(tile)
  }
}

case class Update(tile: TileImage)
case class Create(tile: TileImage)
