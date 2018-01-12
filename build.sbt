name := """veneficus-exchange"""
organization := "nl.hyranasoftware"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.1"

libraryDependencies ++= Seq(
  openId
)

scalaVersion := "2.12.4"

libraryDependencies += guice
