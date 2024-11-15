package config

import cats.effect.Async
import cats.effect.std.Console
import domain.Environment
import domain.customer.{CustomerPostgresRepo, CustomerRepository, CustomerService, CustomerServiceImpl}
import org.typelevel.log4cats.Logger

class LiveAppEnvironment[F[_]: Async: Console: Logger] extends Environment[F] {

  override def customerRepository: CustomerRepository[F] = {

    new CustomerPostgresRepo[F](DBSession.session())
  }

  override def customerService: CustomerService[F] = {
    new CustomerServiceImpl(customerRepository)
  }
}