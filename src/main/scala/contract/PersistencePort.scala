package contract

import domain.Customer

import scala.concurrent.Future

abstract class PersistencePort {

  def getCustomer(id: Long): Future[Option[Customer]]
}
