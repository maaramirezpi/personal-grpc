import com.google.inject.Guice
import contract.GetCustomerService
import modules.{OutPortModule, ServiceModule}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val injector = Guice.createInjector(OutPortModule, ServiceModule)

    val customerService = injector getInstance classOf[GetCustomerService]

    val result = customerService.call(GetCustomerService.request(123))

    result.onComplete {
      case Success(value) => println(value.firstName)
      case Failure(exception) => println("there was an exception")
    }
  }
}