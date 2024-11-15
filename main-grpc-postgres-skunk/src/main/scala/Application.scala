import cats.effect.{ExitCode, IO, IOApp, Sync}
import config.LiveAppEnvironment
import domain.customer.Customer
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

object Application extends IOApp{


  override def run(args: List[String]): IO[ExitCode] = {

    implicit def logger[F[_]: Sync]: Logger[F] = Slf4jLogger.getLogger[F]

    val environment= new LiveAppEnvironment[IO]()

    for {
      _ <- logger[IO].info("Starting")
      x <- environment.customerService.notifyCustomer(Customer(2, "Manuel", "Ramirez")).value
      _ <- logger[IO].info(s"Customer is $x")
    } yield ExitCode.Success
  }
}
