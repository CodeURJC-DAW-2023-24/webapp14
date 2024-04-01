#!/bin/bash
docker login
docker build -t iireenees012/webappImage -f docker/Dockerfile .
docker push iireenees012/webapp14
