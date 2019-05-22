import javax.servlet.ServletContext

import com.ponkotuy.controllers.OSM
import skinny.micro.LifeCycle
import scalikejdbc._

class Bootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    skinny.DBSettings.initialize()
    sql"create table person (id bigint not null);".update().apply()(AutoSession)
    OSM.mount(context)
  }
}
