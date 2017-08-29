#!/usr/bin/env bash

#run with cleaning DB
#./gradlew -Dfile.encoding=UTF-8 -Dspring.profiles.active="local" cleanDB bootRun

#run without cleaning DB
./gradlew -Dfile.encoding=UTF-8 -Dspring.profiles.active="production"  bootRun