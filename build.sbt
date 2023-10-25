ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val applicationDependencies = Seq(
  // Config
  "com.typesafe" % "config" % "1.4.2",
)
lazy val exceptionsDependencies = applicationDependencies
lazy val futureUtilsDependencies = exceptionsDependencies
lazy val dependencyInjectionDependencies = applicationDependencies ++ Seq(
  // Dependency Injection
  "com.google.inject" % "guice" % "5.1.0",
)

lazy val root = (project in file("."))
  .settings(
    name := "personal-grpc",
    libraryDependencies ++= dependencyInjectionDependencies
  )
