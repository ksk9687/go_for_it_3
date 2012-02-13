import AssemblyKeys._

seq(assemblySettings: _*)

name := "cryptography"

version := "1.0"

scalaVersion := "2.9.1"

mainClass in assembly := Some("vc.ksk.cryptography.Main")

jarName in assembly <<= name {_ + ".jar"}

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"

// Junit is just required for eclipse to be able to start tests.
libraryDependencies += "junit" % "junit" % "4.10" % "test"
