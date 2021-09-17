#!/usr/bin/env bash

echo 'Creating application user and db'

mongo authorization-service \
        --host localhost:27017 \
        --authenticationDatabase admin \
        --eval "db.createUser({user: 'authorization-service', pwd: 'authorization-service', roles:[{role:'dbOwner', db: 'authorization-service'}]});"