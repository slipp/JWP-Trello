#!/usr/bin/env bash

echo "Init SonarQube"

cd docker

docker-compose -p sonarqube -f docker-compose-sonarqube.yml up --build -d





