# Student Feedback System

A comprehensive **Full Stack Java** application designed to collect, manage, and analyze student feedback effectively. This project focuses on providing a streamlined interface for students to provide input and for administrators to view processed reports.

## 🚀 Features
* **User Authentication:** Secure login for Students and Admins.
* **Feedback Collection:** Intuitive UI for students to submit feedback on various parameters.
* **Admin Dashboard:** Tools for administrators to view and manage feedback data.
* **Database Integration:** Robust data storage using MySQL.
* **Multi-threading:** Implements Java Threads for background processing (Summary Generation).

## 🛠️ Tech Stack
* **Language:** Java 17+
* **Database:** MySQL
* **Tools:** Eclipse IDE, Git/GitHub
* **Architecture:** MVC (Model-View-Controller) pattern

## 📂 Project Structure
* `Ui`: Handles the graphical interface (Swing/AWT).
* `db`: Database connection logic and DAO classes.
* `service`: Business logic and feedback operations.
* `model`: Data entities (User, Student, Feedback).
* `thread`: Background processing tasks.

## ⚙️ Setup
1. Clone the repository: `git clone https://github.com/sw216/StudentFeedbackSystem.git`
2. Import the project into **Eclipse**.
3. Add the `mysql-connector-j` JAR to your build path.
4. Run the `Main.java` file.
