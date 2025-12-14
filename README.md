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

### 📐 Logical Flow

```text
┌────────────┐       REST Call        ┌────────────┐
│  Service A │ ───────────────────▶ │  Service B │
│ (Consumer) │                       │ (Provider) │
└─────┬──────┘                       └─────┬──────┘
      │   Circuit Breaker (Resilience4j)   │
      │──────────────────────────────────▶│
      │                                    │
      │◀──────── Fallback Response ───────│
```

### 🧩 Behavior

🟢 **Closed** → Calls flow normally
🟡 **Open** → Calls blocked, fallback triggered
🔵 **Half-Open** → Limited test calls to check recovery

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
