# System_design_RateLimiter

# Rate Limiter Design (Token Bucket)

## Overview

This repository demonstrates the **design and implementation of a scalable rate limiter**
used to control API request traffic and prevent abuse in distributed systems.

The solution is designed with **low latency**, **high concurrency**, and **horizontal scalability**
in mind.

---

## Requirements

- Limit requests per user or IP
- Support burst traffic
- Work in distributed environments
- Be thread-safe and efficient

---

## Algorithms Considered

| Algorithm      | Pros                       | Cons                       |
| -------------- | -------------------------- | -------------------------- |
| Fixed Window   | Simple                     | Burst at window boundaries |
| Sliding Window | Accurate                   | Higher memory cost         |
| Token Bucket   | Allows bursts, smooth rate | Slight complexity          |
| Leaky Bucket   | Stable output              | Drops burst traffic        |

✅ **Chosen Algorithm: Token Bucket**

---

## Token Bucket Approach

- Tokens are added to the bucket at a constant rate
- Each request consumes one token
- Requests are allowed only if tokens are available
- Bucket size controls burst capacity

## Architectural Diagram

+--------+ +---------------+. +-------------+
| API Server | <---- | Load Balancer | <---- | Client|
+--------+. +---------------+ +-------------+
|
v
+--------------+
| Rate Limiter |
| (TokenBucket)|
+--------------+
|
v
+--------------+
| Redis Cache |
+--------------+
|
v
+--------------+
| Backend APIs |
+--------------+
