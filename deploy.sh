#!/usr/bin/env bash

git fetch
master=$(git rev-parse master)
remote=$(git rev-parse origin/master)

#if [[ $master == $remote ]]; then
#    echo "[$(date)] Nothing to do"
#    exit 0
#fi

PROJECT_NAME="jwp-trello"
REVISION_NO=$(git rev-parse --short HEAD)

echo "latest revision no : [$REVISION_NO]"

echo "build trello image"

docker tag $PROJECT_NAME:latest $PROJECT_NAME:$REVISION_NO
docker rmi $PROJECT_NAME:latest

./gradlew clean buildDocker

cd docker

echo "current dir : $(pwd)"

docker-compose -p trello down

docker-compose -p trello up --build -d

cd ..

./gradlew sonarqube



