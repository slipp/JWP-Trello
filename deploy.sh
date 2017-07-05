#!/bin/bash

./gradlew clean build
echo "build successful!"

pkill -9 -f jwp-trello

cd build/libs

java -Dserver.port=8000 -jar jwp-trello-1.0.0.jar >> trello.log 2>&1 &

