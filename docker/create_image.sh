#!/bin/bash
docker login
docker build -t iireenees012/webapp14 .
docker push iireenees012/webapp14
