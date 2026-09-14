# Java Prep — Securities Finance Platform

A progressively built Java/Spring Boot platform built as preparation for
the Jefferies Technical Lead role (Equities – Securities Finance Technology).

## What this project covers
- Real-time trade event processing (Kafka)
- Position tracking with Redis caching
- Securities Finance domain: SBL, collateral, MTM
- Kubernetes-ready deployment
- Prometheus/Grafana observability

## Running locally
```bash
mvn spring-boot:run
# API: http://localhost:8080/api/status
# Health: http://localhost:8080/actuator/health
```

## Stack
Java 17 · Spring Boot 3.3 · Kafka · Redis · Postgres · Docker · Kubernetes

## Day-by-day build log
| Day | Feature added |
|-----|--------------|
| 1   | Project scaffold, REST API, Docker |