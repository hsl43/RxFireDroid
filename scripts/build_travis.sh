#!/bin/bash

if [ $TRAVIS_BRANCH = "master" ]; then
    ./gradlew clean bintrayUploadAll
else
    ./gradlew clean assembleAll
fi