# Sporty Group Challenge — Backend Engineer (Kafka + Mock RocketMQ)

This project implements the required event-driven flow:

1) A REST API receives an **Event Outcome** and publishes it to Kafka topic **`event-outcomes`**.
2) A Kafka consumer listens to **`event-outcomes`**, matches Bets by **Event ID** from an in-memory store, and publishes settlement messages to **`bet-settlements`** using a **Mock RocketMQ producer**.
3) A **Mock RocketMQ consumer** consumes `bet-settlements` and **settles** each bet (WON/LOST) based on winner comparison, storing the result in an in-memory ledger.

Kafka is real and runs locally via Docker.  
RocketMQ is mocked in-process (as allowed by the assignment).

The system is structured using DDD-inspired boundaries and ports/adapters:
- `domain`: core models + interfaces
- `action`: use-cases + ports
- `infrastructure`: adapters (Kafka, in-memory stores, mock RocketMQ)
- `api`: REST controllers

---

# Architecture Flow
POST /event-outcomes
│
▼
Kafka Topic: event-outcomes
│
▼
Kafka Consumer
(match bets by eventId)
│
▼
Mock RocketMQ Publisher
(topic: bet-settlements)
│
▼
Mock RocketMQ Consumer
(SettleBetSettlementUseCase)
│
▼
InMemory Settlement Ledger


---

# Tech Stack

- Java 21
- Spring Boot
- Spring Kafka
- Gradle
- Docker (for Kafka)
- In-memory concurrent data structures

---

# Requirements

- Java 21 installed
- Docker installed and running
- Gradle wrapper (included in the repository)

---

# How to Run the Application

## 1️⃣ Start Kafka (Docker Compose)

From the project root (where `docker-compose.yml` is located):

```bash
#Running Tests
./gradlew test

#Start Kafka
docker compose up -d

#Start the Spring Boot Application
./gradlew clean bootRun

#Runs at
http://localhost:8080

#Verify seeded bets
curl -i "http://localhost:8080/bets?eventId=event-100"

#Publish an Event Outcome
curl -i -X POST "http://localhost:8080/event-outcomes" \
  -H "Content-Type: application/json" \
  -d '{"eventId":"event-100","eventName":"A vs B","eventWinnerId":"winner-10"}'

#Observe Logs If multiple bets exist for that event, multiple settlement logs should appear.
Consumed event-outcome eventId=event-100 -> matchedBets=1
Mock RocketMQ publish to topic='bet-settlements' payload=...
Settled betId=bet-1 result=WON eventId=event-100 ...