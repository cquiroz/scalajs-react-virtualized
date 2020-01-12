val reactJS          = "16.7.0"
val reactVirtualized = "9.21.1"
val scalaJsReact     = "1.4.2"

parallelExecution in (ThisBuild, Test) := false

addCommandAlias("restartWDS",
                "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer")

addCommandAlias("restartWDS",
                "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer")

// resolvers in Global += Resolver.sonatypeRepo("staging")

inThisBuild(
  List(
    homepage := Some(url("https://github.com/cquiroz/scalajs-react-virtualized")),
    licenses := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
    developers := List(
      Developer("cquiroz",
                "Carlos Quiroz",
                "carlos.m.quiroz@gmail.com",
                url("https://github.com/cquiroz"))
    ),
    scmInfo := Some(
      ScmInfo(url("https://github.com/cquiroz/scalajs-react-virtualized"),
              "scm:git:git@github.com:cquiroz/scalajs-react-virtualized.git")
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
      webpackConfigFile in fastOptJS := Some(baseDirectory.value / "dev.webpack.config.js"),
      version in webpack := "4.30.0",
      version in webpackCliVersion := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
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
      npmDependencies in Compile ++= Seq(
        "react" -> reactJS,
        "react-dom" -> reactJS,
        "react-virtualized" -> reactVirtualized
      ),
      // Requires the DOM for tests
      requireJsDomEnv in Test := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      version in webpack := "4.30.0",
      version in webpackCliVersion := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      scalaJSStage in Test := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test" % scalaJsReact % "test",
        "io.github.cquiroz.react" %%% "common" % "0.3.7",
        "io.github.cquiroz.react" %%% "test" % "0.3.7" % Test,
        "io.github.cquiroz.react" %%% "cats" % "0.3.7",
        "com.lihaoyi" %%% "utest" % "0.7.3" % Test,
        "org.typelevel" %%% "cats-core" % "2.1.0" % Test
      ),
      webpackConfigFile in Test := Some(baseDirectory.value / "test.webpack.config.js"),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := "2.12.10",
  organization := "io.github.cquiroz.react",
  sonatypeProfileName := "io.github.cquiroz",
  description := "scala.js facade for react-virtualized",
  homepage := Some(url("https://github.com/cquiroz/scalajs-react-virtualized")),
  licenses := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
  publishArtifact in Test := false,
  scalacOptions := Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-Xfuture", // Turn on future language features.
    "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
    "-Xlint:by-name-right-associative", // By-name parameter of right associative operator.
    "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
    "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
    "-Xlint:option-implicit", // Option.apply used implicit view.
    "-Xlint:package-object-classes", // Class or object defined in package object.
    "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
    "-Xlint:unsound-match", // Pattern match may not be typesafe.
    "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
    "-Ypartial-unification", // Enable partial unification in type constructor inference
    "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
    "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
    "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
    "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
    "-Ywarn-numeric-widen", // Warn when numerics are widened.
    "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals", // Warn if a local definition is unused.
    // "-Ywarn-unused:params",              // Warn if a value parameter is unused.
    // "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates", // Warn if a private member is unused.
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
    "-P:scalajs:sjsDefinedByDefault",
    "-Yrangepos"
  )
)
