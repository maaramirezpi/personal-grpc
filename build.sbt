
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val CirceVersion = "0.14.0-M5"

lazy val domain = (project in file("domain"))
  .settings(
    name := "customer-service",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.1.1",
      "org.typelevel" %% "cats-effect" % "3.4.4",
      "org.typelevel" %% "log4cats-slf4j"   % "2.7.0",
    ),
  )

lazy val protobuf =
  project
    .in(file("protobuf"))
    .settings(
      name := "protobuf",
      //scalaVersion := scala3Version
    )
    .enablePlugins(Fs2Grpc)

lazy val `persistence-postgres-skunk` =
  project
    .in(file("persistence-postgres-skunk"))
    .settings(
      libraryDependencies ++= Seq(
        "org.tpolecat" %% "skunk-core" % "0.6.4"
      )
    )
    .dependsOn(domain)

lazy val `main-grpc-postgres-skunk` =
  project
    .in(file("main-grpc-postgres-skunk"))
    .settings(
      libraryDependencies ++= Seq(

        "io.circe"        %% "circe-generic"       % CirceVersion,
        "io.grpc" % "grpc-netty-shaded" % scalapb.compiler.Version.grpcJavaVersion,
        "ch.qos.logback" % "logback-classic" % "1.5.6" % Runtime
      )
    )
    .dependsOn(domain, `persistence-postgres-skunk`, protobuf)

lazy val root = (project in file("."))
  .settings(
    name := "personal-grpc",
  ).aggregate(domain)
