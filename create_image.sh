##!/bin/bash

docker login
docker build -t ticketevent .
docker push ticketevent

