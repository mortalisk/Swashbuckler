name := "Swashbuckler"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.1"

seq(slickSettings: _*)

unmanagedSourceDirectories in Compile ~= { it => file("src/main/generated") +: it }

