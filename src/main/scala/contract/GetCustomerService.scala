package contract

import domain.Customer

abstract class GetCustomerService extends Service[GetCustomerService.request, Customer]

object GetCustomerService{

  case class request(id: Long)
}
