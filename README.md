# 🎓 Quiz Manager ( Gestionnaire de Quiz )

## 📖 Project Description
This project is a **Java console application** designed to build and manage a complete quiz system for students and teachers.

It provides a simple, text-based interface to handle all key operations, from quiz creation and management to student participation and score tracking.

The system is designed to manage:
- A secure, password-protected space for Teachers
- A separate login space for Students
- Creation and deletion of quizzes (QCMs)
- Student quiz-taking and score-keeping
- Quiz corrections and results

---

## 👥 Authors
- **Amine Yengui**
- **Khalil Naffeti**

---

## 🗂️ Project Structure & Key Features

### 1. Core Architecture
The system is a **Java console application** that uses the `Scanner` class for all user input. It is built with core OOP principles, using classes like `Quiz`, `Question`, `Option`, and `Etudiant` to manage data. A key feature is its robust input validation (`saisirEntier`), which prevents the application from crashing if a user enters text instead of a number.

### 2. Espace Enseignant (Teacher Space)
This is a secure area for quiz management, **protected by a password** ("java000").
- **Add Quiz:** Create new quizzes by defining a theme, author, and a specific number of questions.
- **Delete Quiz:** Remove a quiz from the system by specifying its theme name.
- **View All Quizzes:** Get a complete list of all available quizzes, showing all questions and which options are marked as correct.
- **View Results:** Check the scores of all students who have taken a specific quiz.

### 3. Espace Étudiant (Student Space)
This area allows students to test their knowledge.
- **Login & Signup:** Students log in with their unique ID (NCIN). If the ID isn't found, the system prompts them to create a new account with their name.
- **View Quiz List:** See all available quizzes and their current status (e.g., "Already Participated" or "Not Participated").
- **Take Quiz:** Students can answer a quiz's questions one by one. The system enforces the rule that a student can only take each quiz **one time**.
- **View My Scores:** Check a personal list of all completed quizzes and see the final scores (e.g., "Score: 8 / 10").
- **View Correction:** After completing a quiz, a student can view the full correction to see the correct answers. This is only allowed *after* participating.

---

## 🚀 Installation & Usage
1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/YourUsername/YourRepoName.git](https://github.com/YourUsername/YourRepoName.git)
    ```
2.  **Compile the Code:**
    * Navigate to the project's `src` directory.
    * Run `javac *.java` to compile all the Java files.
3.  **Run the Application:**
    * From the directory containing the compiled `.class` files, run the main class (e.g., `java Main`).
    * The main menu will appear in your console.
