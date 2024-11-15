package service

import cats.Monad
import cats.effect.kernel.Async
import customer.protos.customer.{CustomerServiceFs2Grpc, NotifyCustomerRequest, NotifyCustomerResponse, Customer => ProtoCustomer}
import domain.customer.{Customer, CustomerService}
import fs2.Compiler.Target.{forConcurrent, forSync}
import io.grpc.Metadata

class GrpcCustomerService[F[_]: Monad](customerService: CustomerService[F]) extends CustomerServiceFs2Grpc[F, Metadata]{

  override def notifyCustomer(request: NotifyCustomerRequest, ctx: Metadata): F[NotifyCustomerResponse] = {
    customerService
      .notifyCustomer(toDomainCustomer(request.getCustomer))
      .map(c => NotifyCustomerResponse(Some(toProtoCustomer(c)), notified = true))
      .getOrElse(NotifyCustomerResponse(None))
  }

  private def toDomainCustomer(customer: ProtoCustomer) = {
    Customer(customer.id, customer.firstName, customer.lastName)
  }

  private def toProtoCustomer(customer: Customer) = {
    ProtoCustomer(customer.customerId, customer.firstName, customer.lastName)
  }
}
