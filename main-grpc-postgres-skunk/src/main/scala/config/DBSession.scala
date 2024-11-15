package config

import cats.effect.Resource
import cats.effect.kernel.Async
import cats.effect.std.Console
import natchez.Trace.Implicits.noop
import skunk._

object DBSession {

  def session[F[_]: Async: Console](): Resource[F, Session[F]] =
    Session.single[F](
      host = "localhost",
      //host = "postgres",
      port = 5455,
      user = "postgres",
      database = "personal_db",
      password = Some("postgres")
    )


}
