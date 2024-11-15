package domain.customer

import cats.data.OptionT

trait CustomerRepository[F[_]] {

  def getCustomerById(customer: Customer): OptionT[F, Customer]
}
