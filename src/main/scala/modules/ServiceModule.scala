package modules

import application.GetCustomerUseCase
import com.google.inject.AbstractModule
import contract.GetCustomerService

object ServiceModule extends AbstractModule {
  override def configure(): Unit = {

    bind(classOf[GetCustomerService]) to classOf[GetCustomerUseCase]

  }
}
