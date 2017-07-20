#!/usr/bin/env bash

PROJECT_NAME="javajigi/jwp-trello"

echo "docker-compose restart"

cd docker

echo "current dir : $(pwd)"

docker-compose -p trello -f common-services.yml -f compose-prod.yml down

docker-compose -p trello -f common-services.yml -f compose-prod.yml up -d