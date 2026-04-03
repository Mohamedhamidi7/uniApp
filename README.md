
---

# API Endpoints

## Authentication
| Method | Endpoint | Description |
|-------|----------|-------------|
| POST | /public/register | Register new user |
| POST | /public/login | Login user |

---

## Servers
| Method | Endpoint | Description |
|-------|----------|-------------|
| GET | /private/servers | Get servers of current user |
| GET | /private/servers/{serverName} | Get server by name |
| GET | /private/servers/{serverName}/members | Get server members |
| POST | /private/servers | Create new server |
| PUT | /private/servers/{serverName} | Update server |
| DELETE | /private/servers/{serverName} | Delete server |

---

## Server Members Controlling
| Method | Endpoint | Description |
|-------|----------|-------------|
| POST | /private/servers/{serverName}/members/{username} | Add member to server |
| PUT | /private/servers/{serverName}/members/{username}?role=ROLE | Update member role |
| DELETE | /private/servers/{serverName}/members/{username} | Remove member from server |

---

## Roles
| Role |
|------|
| OWNER |
| EDITOR |
| CONTROLLER |
| MEMBER |

---

## Authentication
All `/private/**` endpoints require JWT token.

---

## Technologies
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- PostgreSQL
