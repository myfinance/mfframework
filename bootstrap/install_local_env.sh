#!/bin/bash

# This script sets up a local development environment with MongoDB, RabbitMQ, and Keycloak using Docker Compose.
# It is intended to be run in OrbStack.

echo "Starting local environment services (MongoDB, RabbitMQ, Keycloak)..."

# Ensure Docker Compose is available
if ! command -v docker-compose &> /dev/null
then
    echo "docker-compose could not be found. Please install Docker Desktop or Docker Compose."
    exit 1
fi

# Start the services in detached mode
docker-compose -f "$(dirname "$0")/docker-compose.yml" up -d

if [ $? -eq 0 ]; then
    echo "Local environment services started successfully."
    echo "MongoDB: mongodb://admin:admin@localhost:27017"
    echo "RabbitMQ Management: http://localhost:15672 (user: admin, pass: admin)"
    echo "Keycloak Admin Console: http://localhost:8080 (user: admin, pass: admin)"
    echo "Keycloak Realm 'myfinance' imported."
else
    echo "Failed to start local environment services."
fi
