package modules

import adapters.PostgresAdapter
import com.google.inject.AbstractModule
import contract.PersistencePort

object OutPortModule extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[PersistencePort]) to classOf[PostgresAdapter]
  }

}