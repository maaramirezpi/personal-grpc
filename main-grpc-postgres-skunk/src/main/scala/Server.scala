import cats.effect.{IO, Resource}
import cats.effect.kernel.Async
import customer.protos.customer.CustomerServiceFs2Grpc
import domain.Environment
import fs2.grpc.syntax.all.fs2GrpcSyntaxServerBuilder
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import io.grpc.{Server, ServerServiceDefinition}
import service.GrpcCustomerService

object Server {

  def createServer[F[_]: Async](env: Environment[F]) = {
    val customerService = CustomerServiceFs2Grpc.bindServiceResource(new GrpcCustomerService(env.customerService))

    def runServer(
                           service: ServerServiceDefinition
                         ): Resource[F, Server] =
      NettyServerBuilder
        .forPort(9999)
        .addService(service)
        .resource[F]

    customerService
        .flatMap(x => runServer(x))

  }
}
