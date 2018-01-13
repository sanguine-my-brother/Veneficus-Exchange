name := """veneficus-exchange"""
organization := "nl.hyranasoftware"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)



libraryDependencies ++= Seq(
  openId,
  javaJdbc,
  evolutions
)

scalaVersion := "2.12.4"

libraryDependencies += guice
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.6"
libraryDependencies += "io.ebean" % "ebean" % "9.5.1"

libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.1"
libraryDependencies += "com.typesafe.play" %% "play-ebean" % "4.1.0"


