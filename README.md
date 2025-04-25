
# 🍽️ Restaurant Management System - Java

---
You can review endpoints from the Following link: http://restauran-manage-app-env.eba-vgpfpeda.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html#/

## 📖 Overview

A **desktop application** built with Java for managing restaurant operations including:

- Tables
- Orders
- Menu Items
- Staff

The project was developed to practice Java backend development with database integration and business logic design.

---

## 🧰 Technologies Used

### 🔹 Core Stack
- **Java 17** – Primary programming language  
- **Spring Boot** – Backend framework  
- **Spring Security (JWT)** – Authentication and authorization  
- **PostgreSQL** – Relational database  
- **JDBC / Spring Data JPA** – Database access and ORM  
- **Maven** – Build & dependency management  

### 🔸 Supporting Libraries & Tools
- **Lombok** – Reduces boilerplate code  
- **ModelMapper** – DTO ↔ Entity mapping  
- **Javax Validation API** – Input validation  
- **SLF4J / Logback** – Logging  
- **Java Time API** – Date & time operations  
- **Spring Cloud / AWS SDK** *(planned)* – Cloud storage & file handling (e.g. images)

---

## 🌟 Key Features

### 1️⃣ Table Management
- View all restaurant tables and their status _(Vacant / Occupied)_
- Assign customers to tables  
- Clear tables after service  

### 2️⃣ Order Processing
- Create new orders linked to tables  
- Add / remove items from orders  
- Modify item quantities  
- View order details  
- **🧾 Print order receipts**

### 3️⃣ Menu Management
- Browse menu by categories  
- Add new menu items  
- Update existing items  
- Categorize items _(Food, Drinks, etc.)_

### 4️⃣ Staff Management
- Add new staff members  
- View staff list  
- Assign roles _(Waiter, Manager, etc.)_  
- Manage basic staff information  

---

## 🗃️ Database Structure

| Table Name     | Description                        |
|----------------|------------------------------------|
| `users`        | Staff accounts and credentials     |
| `menu_items`   | Available food/drink items         |
| `orders`       | Customer orders                    |
| `order_items`  | Items within each order            |
| `tables`       | Restaurant table information       |

---

## 🔐 Login Screen

- Secure login for staff members  
- **Role-based access control** for different staff types (e.g., Manager vs Waiter)

---

## 🧭 Main Dashboard

- Quick access to all system features  
- Status overview of:
  - Active orders  
  - Table occupancy  

---

## 🪑 Table Management Screen

- Visual layout of restaurant tables  
- **Color-coded** indicators for Vacant/Occupied  
- Table assignment and clearing options  

---

## 🧾 Order Screen

- Create and modify customer orders  
- Search and add items from menu  
- Calculate totals, taxes, and generate receipts  

---

## 🧆 Menu Management

- Add/edit/delete menu items  
- Set prices, categories, and availability  
- Organize by type (e.g., Starters, Drinks, Mains)

---

## ⚙️ How the System Works

1. Staff logs in with credentials  
2. System verifies role and grants access  
3. Depending on access level, staff can:
   - Manage tables and customers
   - Create and update orders
   - Add menu items
   - Process payments

---

## 🧠 Business Logic Highlights

> 🎯 These backend mechanics ensure smooth operations:

- Real-time table status tracking  
- Role-based function access  
- Order total calculation with tax handling  
- Receipt generation with restaurant branding  
- Clean separation of concerns with layered architecture  

---

## ✅ Future Plans

- Image upload to cloud for menu items (e.g. AWS S3)  
- Reporting module for daily sales summary  
- Email notifications and staff scheduling features  

---

## 🤝 Contributions

This is a solo learning project, but suggestions and improvements are welcome!  
Feel free to fork or raise an issue.

---
![Screenshot 2025-04-11 193206](https://github.com/user-attachments/assets/68723430-2e0f-47c2-899e-cd993805d02a)
![Screenshot 2025-04-11 193149](https://github.com/user-attachments/assets/76ecdae7-1c6b-41f3-84f7-9c7ad23f6634)
![Screenshot 2025-04-11 193118](https://github.com/user-attachments/assets/bee0be72-1715-4f0f-bcdc-09483256663d)

Made with ☕ & code by **[Nurlan](https://github.com/nurlanlan)**
