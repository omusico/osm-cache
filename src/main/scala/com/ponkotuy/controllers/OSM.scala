package com.ponkotuy.controllers

import akka.actor.{ActorSystem, Props}
import com.ponkotuy.actors.{Create, SaveImageActor, Update}
import com.ponkotuy.http.{Tile, TileOSMOrg, TilePosition}
import com.ponkotuy.models.{TileImage, ResourceFileLoader}
import skinny.micro._

import scala.concurrent.duration._

object OSM extends WebApp {
  private[this] val system = ActorSystem()
  private[this] val saveImage = system.actorOf(Props[SaveImageActor])
  private[this] val xmlLoader = new ResourceFileLoader()

  get("/") {
    Ok(xmlLoader("/html/index.html"), contentType = Some("text/html"))
  }

  get("/maps/:zoom/:x/:y.png") {
    parse(params).fold(NotFound()) { tile =>
      val dbImage = TileImage.findBy(tile.where)
      val image: TileImage = dbImage.fold {
        val image = TileOSMOrg.get(tile)
        saveImage ! Create(image)
        image
      } { fromDB =>
        if((fromDB.created + 365.days.toMillis) < System.currentTimeMillis()) {
          val fromWeb = TileOSMOrg.get(tile)
          saveImage ! Update(fromWeb)
          fromWeb
        } else fromDB
      }
      Ok(
        body = image.image,
        contentType = Some("image/png"),
        headers = Map(
          "Cache-Control" -> "public, max-age=604800"
        )
      )
    }
  }

  private[this] def parse(params: Params): Option[Tile] = {
    for {
      x <- params.getAs[Int]("x")
      y <- params.getAs[Int]("y")
      zoom <- params.getAs[Int]("zoom")
    } yield TilePosition(x, y, zoom)
  }
}
