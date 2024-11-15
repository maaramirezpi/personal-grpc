package domain.customer
import cats.{Functor, Monad}
import cats.data.OptionT
import org.typelevel.log4cats.Logger

class CustomerServiceImpl[F[_]: Monad: Logger](repository: CustomerRepository[F]) extends CustomerService[F] {

  override def notifyCustomer(customer: Customer): OptionT[F, Customer] = {
    for {
      _ <- OptionT.liftF(Logger[F].info("In the service"))
      customer <- repository.getCustomerById(customer)
    } yield customer
  }
}
