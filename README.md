Restaurant Management System - Java
Overview
A desktop application built with Java for managing restaurant operations including tables, orders, menu items, and staff. 
Technologies Used
Core Stack
Java: Primary programming language 

PostgreSql: Database backend

JDBC: For database connectivity

Supporting Libraries
For date/time operations

For utility functions

For input validation

Key Features
1. Table Management
View all restaurant tables and their status (Vacant/Occupied)

Assign customers to tables

Clear tables after service

2. Order Processing
Create new orders linked to tables

Add/remove items from orders

Modify quantities

View order details

Print order receipts

3. Menu Management
Browse menu by categories

Add new menu items

Update existing items

Categorize items (Food, Drinks, etc.)

4. Staff Management
Add new staff members

View staff list

Assign roles (Waiter, Manager, etc.)

Basic staff information management


Database Structure
Database contains these main tables:

users - Staff accounts and credentials

menu_items - All available food/drink items

orders - Customer orders

order_items - Items within each order

tables - Restaurant tables

Login Screen
Authentication for staff members

Role-based access control

Main Dashboard
Quick access to all system functions

Status overview of tables and orders

Table Management Screen
Visual representation of tables

Color-coded status indicators

Table assignment functionality

Order Screen
Create and modify orders

Search/add menu items

Calculate totals

Print receipts

Menu Management
Add/edit/delete menu items

Set prices and categories

Update availability

How the System Works
Staff logs in with their credentials

System checks role and grants appropriate access

Staff can:

View and manage tables

Create new orders for customers

Add items to orders from the menu

Process payments

Manage menu items

Business Logic Highlights
Order calculation with proper tax handling

Table status tracking in real-time

Receipt generation with restaurant branding





