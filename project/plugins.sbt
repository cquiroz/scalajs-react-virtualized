val scalaJSVersion =
  Option(System.getenv("SCALAJS_VERSION")).getOrElse("0.6.29")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % scalaJSVersion)

addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.15.0-0.6")

addSbtPlugin("com.geirsson" % "sbt-ci-release" % "1.4.31")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.2.1")
