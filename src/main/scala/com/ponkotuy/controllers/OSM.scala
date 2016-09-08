package com.ponkotuy.controllers

import javax.servlet._
import skinny.micro._

object OSM extends WebApp {
  get("/") {
    "Hello, world!"
  }
}

