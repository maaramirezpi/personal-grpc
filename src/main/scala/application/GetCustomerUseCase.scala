package application

import com.google.inject.{Inject, Singleton}
import contract.{GetCustomerService, PersistencePort}
import domain.Customer

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetCustomerUseCase @Inject()(adapter: PersistencePort) extends GetCustomerService {

  override def call(request: GetCustomerService.request)(implicit ec: ExecutionContext): Future[Customer] = {
    for {
      customerOption <- adapter.getCustomer(request.id)
      customer <- customerOption match {
        case Some(value) => Future.successful(value)
        case None => Future.failed(new Exception(s"Customer ${request.id} not found!"))
      }
    } yield customer
  }
}
