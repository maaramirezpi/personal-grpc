package contract

import scala.concurrent.{ExecutionContext, Future}

abstract class Service[Request, Response] {

  /**
   * We should wrap all of computations to handle exceptions in a functional way.
   * Scala's [[scala.util.Try]] is the other option. Since it is synchronous, it is not a good option.
   *
   * @see [[Future]]
   * @see [[scala.util.Try]]
   */
  def call(request: Request)(implicit ec: ExecutionContext): Future[Response]

}
