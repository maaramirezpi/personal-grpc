package domain.customer

import cats.data.OptionT

trait CustomerService[F[_]] {

  def notifyCustomer(customer: Customer): OptionT[F, Customer]
}
