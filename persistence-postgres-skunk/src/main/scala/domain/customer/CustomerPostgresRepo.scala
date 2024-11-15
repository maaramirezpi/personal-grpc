package domain.customer

import cats.data.OptionT
import cats.effect.{Async, Resource}
import cats.implicits._
import org.typelevel.log4cats.Logger
import skunk.codec.all.{int4, int8, varchar}
import skunk.implicits._
import skunk.{Codec, Query, Session}

class CustomerPostgresRepo [F[_]: Async: Logger](session: Resource[F, Session[F]]) extends CustomerRepository[F] {

  val codec: Codec[Customer] =
    (int8, varchar(255), varchar(255)).tupled.imap {
      case (id, name, last) => Customer(id, name, last)
    } { customer => (customer.customerId, customer.firstName, customer.lastName) }

  val selectById: Query[Long, Customer] =
    sql"""
        SELECT * FROM customer
        WHERE id = $int8
      """.query(codec)

  override def getCustomerById(customer: Customer): OptionT[F, Customer] = {

    OptionT {
      for {
        _ <- Logger[F].info("calling the DB")
        customer <- session.use { s =>
          for {
            q  <- s.prepare(selectById)
            rowCount <- q.option(customer.customerId)
          } yield rowCount
        }
      } yield customer
    }
  }
}
