# 🚀 AI Job Tracker

A Full Stack Application to track job applications, interviews, and offers.
Built with **Spring Boot 3**, **React**, **PostgreSQL**, and **Docker**.

## 📸 Screenshots

_(You will add a screenshot here later)_    
![alt text](assets/health.png)

## 🛠️ Tech Stack

- **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Hibernate
- **Frontend:** React 18, Vite, Mantine UI, Axios
- **Database:** PostgreSQL 16 (Dockerized)
- **Tools:** Docker Compose, Maven, Swagger UI

## ✨ Features

- **Dashboard:** Real-time statistics (Total Applications, Interviews, Offers).
- **CRUD Operations:** Add, Edit, and Delete job applications.
- **Search:** Instant client-side filtering by Company or Title.
- **REST API:** Fully documented with Swagger UI.

## 🚀 How to Run

### 1. Start the Infrastructure

```bash
docker-compose up -d
```


## 🏗️ Architecture

```mermaid
graph TD
    User[User / Browser] -->|React (Port 5173)| Frontend
    Frontend -->|Axios JSON| Backend
    subgraph Docker
        Backend[Spring Boot API (Port 8080)]
        DB[(PostgreSQL 16)]
    end
    Backend -->|Hibernate| DB
    Backend -->|Actuator| Monitoring[Health Checks]