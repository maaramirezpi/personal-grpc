package domain

import domain.customer.{CustomerRepository, CustomerService}

trait Environment[F[_]] {

  def customerRepository: CustomerRepository[F]

  def customerService: CustomerService[F]
}
