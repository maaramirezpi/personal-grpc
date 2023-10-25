package adapters

import contract.PersistencePort
import domain.Customer

import scala.concurrent.Future

class PostgresAdapter extends PersistencePort{
  override def getCustomer(id: Long): Future[Option[Customer]] = {
    Future.successful(Some(Customer(1, "Manuel", "Ramirez")))
  }
}
