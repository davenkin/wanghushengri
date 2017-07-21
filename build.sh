#!/usr/bin/env bash

./gradlew -Dfile.encoding=UTF-8 -Dspring.profiles.active="build" clean build jacocoTestReport jacocoApiTestReport




