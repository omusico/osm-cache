import javax.servlet.ServletContext

import com.ponkotuy.controllers.OSM
import skinny.micro.LifeCycle

class Bootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    OSM.mount(context)
  }
}
