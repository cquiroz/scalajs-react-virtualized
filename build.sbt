val `scalajs-react-virtualized` =
  project.in(file("."))
    .aggregate(facade, demo)

lazy val demo =
  project.in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % Test
    )
    .dependsOn(facade)

lazy val facade =
  project.in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
    )

lazy val commonSettings = Seq(
  scalaVersion := "2.12.3"
)
