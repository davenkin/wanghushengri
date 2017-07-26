#!/usr/bin/env bash

./gradlew -Dfile.encoding=UTF-8 -Dspring.profiles.active="build" clean build

scp build/libs/wanghushengri.war davenkin@123.57.137.49:./software/apache-tomcat-8.5.15/webapps/ROOT.war




