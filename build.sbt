val reactJS          = "16.13.1"
val reactVirtualized = "9.21.1"
val scalaJsReact     = "1.7.7"

addCommandAlias("restartWDS",
                "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

addCommandAlias("restartWDS",
                "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

// resolvers in Global += Resolver.sonatypeRepo("staging")
Global / onChangedBuildSource := ReloadOnSourceChanges

Global / resolvers += Resolver.sonatypeRepo("public")

inThisBuild(
  List(
    homepage := Some(url("https://github.com/cquiroz/scalajs-react-virtualized")),
    licenses := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
    developers := List(
      Developer("cquiroz",
                "Carlos Quiroz",
                "carlos.m.quiroz@gmail.com",
                url("https://github.com/cquiroz")
      )
    ),
    scmInfo := Some(
      ScmInfo(url("https://github.com/cquiroz/scalajs-react-virtualized"),
              "scm:git:git@github.com:cquiroz/scalajs-react-virtualized.git"
      )
    )
  )
)

val root =
  project
    .in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade, demo)
    .settings(
      name := "root",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )

lazy val demo =
  project
    .in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      scalaJSUseMainModuleInitializer := true,
      webpackBundlingMode := BundlingMode.LibraryOnly(),
      webpackDevServerExtraArgs := Seq("--inline"),
      fastOptJS / webpackConfigFile := Some(baseDirectory.value / "dev.webpack.config.js"),
      webpack / version := "4.30.0",
      webpackCliVersion / version := "3.3.2",
      startWebpackDevServer / version := "3.3.1",
      // don't publish the demo
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )
    .dependsOn(facade)

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "react-virtualized",
      Compile / npmDependencies ++= Seq(
        "react" -> reactJS,
        "react-dom" -> reactJS,
        "react-virtualized" -> reactVirtualized
      ),
      // Requires the DOM for tests
      Test / requireJsDomEnv := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      webpack / version := "4.44.1",
      webpackCliVersion / version := "3.3.11",
      startWebpackDevServer / version := "3.11.0",
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      Test / scalaJSStage := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test" % scalaJsReact % "test",
        "io.github.cquiroz.react" %%% "common" % "0.11.3",
        "io.github.cquiroz.react" %%% "test" % "0.11.3" % Test,
        "io.github.cquiroz.react" %%% "cats" % "0.11.3",
        "org.scalameta" %%% "munit" % "0.7.29" % Test,
        "org.typelevel" %%% "cats-core" % "2.6.1" % Test
      ),
      Test / webpackConfigFile := Some(baseDirectory.value / "test.webpack.config.js"),
      testFrameworks += new TestFramework("munit.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := "2.13.6",
  organization := "io.github.cquiroz.react",
  sonatypeProfileName := "io.github.cquiroz",
  description := "scala.js facade for react-virtualized",
  homepage := Some(url("https://github.com/cquiroz/scalajs-react-virtualized")),
  licenses := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
  Test / publishArtifact := false,
  scalacOptions ~= (_.filterNot(
    Set(
      // By necessity facades will have unused params
      "-Wdead-code",
      "-Wunused:params",
      "-Ywarn-dead-code",
      "-Ywarn-unused:params",
      "-Wunused:explicits"
    )
  ))
)
