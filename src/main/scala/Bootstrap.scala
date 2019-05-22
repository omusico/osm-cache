import com.ponkotuy.controllers.OSM
import javax.servlet.ServletContext
import skinny.micro.LifeCycle

class Bootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    skinny.DBSettings.initialize()
    OSM.mount(context)
  }
}
