# customer-ticketing-system-id-28043979-
Console-based Customer Service Ticketing System using Core Java, MySQL, and JDBC. Manage customer service tickets, assignments, and resolutions. Features include creating, viewing, updating, and deleting tickets, assignments, and resolutions. Includes DAO for data access, models for entities, and exception handling.


Customer Service Ticketing System
Overview
This console-based application, developed using Core Java, MySQL, and JDBC, simulates a customer service environment. It provides functionalities for managing customer service tickets, assignments, and resolutions, demonstrating proficiency in Java programming and database interactions.

Features
:-Ticket Management: Create, view, update, and delete tickets.
:-Ticket Assignment: Assign tickets, view assignments, update, and delete assignment records.
:-Ticket Resolution: Resolve tickets, view resolutions, update, and delete resolution records.

Project Structure
src/com/ticketingsystem/Main/

Main.java: Entry point of the application.
src/com/ticketingsystem/dao/

TicketDAO.java: Manages CRUD operations for tickets.
AssignmentDAO.java: Manages CRUD operations for assignments.
ResolutionDAO.java: Manages CRUD operations for resolutions.
src/com/ticketingsystem/exception/

TicketingException.java: Custom exceptions for error handling.
src/com/ticketingsystem/model/

Ticket.java: Model for ticket data.
Assignment.java: Model for assignment data.
Resolution.java: Model for resolution data.
src/com/ticketingsystem/util/

DBConnection.java: Database connection management.



Database Setup: Create a MySQL database and tables using the SQL schema provided in the resources/sql.schema file.

Configure Database Connection: Update DBConnection.java with your MySQL database URL, username, and password.
