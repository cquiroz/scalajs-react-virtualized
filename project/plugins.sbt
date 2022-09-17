val scalaJSVersion =
  Option(System.getenv("SCALAJS_VERSION")).getOrElse("1.11.0")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % scalaJSVersion)

addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0")

addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.10")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.4")

addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.20")
