## 🔌⚡ Circuit Breaker Implementation using Resilience4j (Spring Boot)

A **learning-focused microservices project** demonstrating the **Circuit Breaker pattern** using **Resilience4j** in a **Spring Boot–based distributed system** 🧩🚀.

This repository provides a **clean skeleton implementation** to understand how microservices can be made **fault-tolerant, resilient, and self-healing**.

---

## 🎯 Purpose

This project showcases how to:

✨ Prevent **cascading failures** across microservices
🛡️ Improve **system resilience & availability**
🔁 Handle downstream failures using **fallback mechanisms**
⚙️ Design robust **service-to-service communication**

> ⚠️ This is a **reference & learning project**, not production-ready.

---

## 🧰 Tech Stack

☕ Java
🌱 Spring Boot
🔗 Resilience4j
🌐 REST APIs
🏗️ Microservices Architecture
📊 Spring Boot Actuator

---

## 🧠 Key Concepts Covered

🔌 Circuit Breaker Pattern
🛑 Closed, Open & Half-Open States
📉 Failure-rate & slow-call thresholds
🔄 Fallback methods & graceful degradation
📡 Resilient inter-service communication

---

## 🏗️ Architecture Overview

### 📐 High-Level Architecture (Image-ready / Draw.io)

```text
                ┌────────────────────┐
                │   API Gateway /     │
                │   Client App        │
                └─────────┬──────────┘
                          │
                          ▼
┌────────────────────┐  REST Call  ┌────────────────────┐
│    Service A       │────────────▶│     Service B      │
│ (Order / User)    │              │ (Downstream API)  │
│                  │◀────Fallback──│                    │
│  Resilience4j     │              │                    │
│  Circuit Breaker  │              │                    │
└────────────────────┘              └────────────────────┘
```

🖼️ This diagram can be directly recreated in **Draw.io / Lucidchart** using simple rectangles and directional arrows.

---

## 🐳 Docker & Docker Compose Setup

### 📦 Dockerfile (Spring Boot Service)

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

### 🧩 docker-compose.yml

```yaml
version: '3.8'
services:
  service-a:
    build: ./service-a
    ports:
      - "8081:8080"
    depends_on:
      - service-b

  service-b:
    build: ./service-b
    ports:
      - "8082:8080"
```

🐳 Enables **local multi-service testing** with failure simulation.

---

## ☸️ Kubernetes + Istio Circuit Breaker

### 📦 Kubernetes Deployment (Sample)

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: service-a
spec:
  replicas: 2
  selector:
    matchLabels:
      app: service-a
  template:
    metadata:
      labels:
        app: service-a
    spec:
      containers:
        - name: service-a
          image: service-a:latest
          ports:
            - containerPort: 8080
```

### 🔌 Istio DestinationRule (Circuit Breaking)

```yaml
apiVersion: networking.istio.io/v1beta1
kind: DestinationRule
metadata:
  name: service-b-circuit-breaker
spec:
  host: service-b
  trafficPolicy:
    connectionPool:
      http:
        http1MaxPendingRequests: 1
        maxRequestsPerConnection: 1
    outlierDetection:
      consecutive5xxErrors: 3
      interval: 5s
      baseEjectionTime: 30s
      maxEjectionPercent: 50
```

☸️ Demonstrates **infrastructure-level resilience** using Istio.

---

## 📊 Grafana Dashboard (Resilience4j Metrics)

```json
{
  "title": "Resilience4j Circuit Breaker",
  "panels": [
    {
      "type": "stat",
      "title": "Circuit Breaker State",
      "targets": [
        {
          "expr": "resilience4j_circuitbreaker_state"
        }
      ]
    },
    {
      "type": "graph",
      "title": "Failed Calls",
      "targets": [
        {
          "expr": "resilience4j_circuitbreaker_calls_total{outcome=\"failed\"}"
        }
      ]
    }
  ]
}
```

📈 Import this JSON directly into **Grafana**.

---

## 🧪 JUnit Test Snippets (Circuit Breaker)

```java
@SpringBootTest
class CircuitBreakerTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldTriggerFallbackWhenServiceFails() {
        String response = userService.getUserOrders("electronics");
        assertNotNull(response);
        assertTrue(response.contains("Fallback"));
    }
}
```

🧪 Validates **fallback execution & resilience behavior**.

---

## ⚙️ Resilience4j Configuration (Sample)

```yaml
resilience4j:
  circuitbreaker:
    instances:
      serviceB:
        failureRateThreshold: 50
        slowCallRateThreshold: 50
        slowCallDurationThreshold: 2s
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3
```

---

## 📊 Metrics & Monitoring (Spring Boot Actuator)

Spring Boot Actuator is enabled to expose **Resilience4j metrics** 📈.

### 🔍 Available Endpoints

* `/actuator/health` 🟢
* `/actuator/metrics` 📊
* `/actuator/circuitbreakers` 🔌
* `/actuator/circuitbreakerevents` 📜

These metrics can be easily integrated with:

📊 Prometheus
📈 Grafana
🧭 Observability tools

---

## 🧪 Testing & Failure Simulation

### ✅ Unit & Integration Testing

🧪 JUnit
🧪 Spring Boot Test
🧪 Mock downstream service failures

### 💥 Failure Scenarios Simulated

❌ Service downtime
⏳ Slow responses (timeouts)
📉 High failure rate triggering Circuit Breaker
🔁 Automatic fallback execution

> This helps validate **resilience behavior before production rollout**.

---

## 🚀 Use Cases

📖 Learn **Resilience4j integration** step-by-step
🧪 Practice **fault-tolerant microservice design**
🎯 Interview prep for **Backend / System Design roles**
🛠️ Reference for implementing **resiliency patterns**

---

## 📈 GitHub Discoverability (SEO Optimized)

🔍 Keywords:

`Spring Boot Circuit Breaker`
`Resilience4j Example`
`Microservices Fault Tolerance`
`Spring Boot Resilience`
`Distributed Systems Design Patterns`

⭐ Star the repo if it helps you learn!

---

## 🛣️ Future Enhancements

✨ Retry & Rate Limiter patterns
📊 Prometheus + Grafana dashboards
🔐 Security with Spring Security
☁️ Docker & Kubernetes deployment
🧠 Chaos engineering experiments

---

## 🤝 Contributions & Collaboration

💡 Fork • Explore • Enhance
🤝 Open to collaboration & knowledge sharing
🌱 Built with **learning, clarity, and growth** in mind

---

🚀 *Happy Learning & Building Resilient Systems!*
