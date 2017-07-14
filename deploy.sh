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

echo "move latest image to old image"

docker tag $PROJECT_NAME:latest $PROJECT_NAME:$REVISION_NO
docker rmi $PROJECT_NAME:latest

echo "build source, docker image"

./gradlew clean build sonarqube buildDocker

rc=$?

if [[ $rc -ne 0 ]] ; then
  echo "gradle build failed"; exit $rc
fi

echo "docker-compose restart"

cd docker

echo "current dir : $(pwd)"

docker-compose -p trello -f common-services.yml -f docker-compose-dev.yml down

docker-compose -p trello -f common-services.yml -f docker-compose-dev.yml up --build -d