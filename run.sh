#!/bin/sh

java -verbose:class -cp ./rshell/target/reverse-shell-spi-1.0-RELEASE.jar:./sample-client-app/target/sample-app-1.0-SNAPSHOT.jar org.example.Main

