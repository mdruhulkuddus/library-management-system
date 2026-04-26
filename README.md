# Library Management System

A console-based Library Management System built with **Core Java** using **Object-Oriented Programming (OOP)** principles. This project was built as a learning exercise to practice OOP concepts in a real-world scenario.

---

## Project Structure

```
LibrarySystem/src/
├── LibraryItem.java     ← Abstract base class for all library items
├── Book.java            ← Concrete implementation of LibraryItem
├── Person.java          ← Abstract base class for all persons
├── Member.java          ← Concrete implementation of Person
├── Transaction.java     ← Records every borrow/return event
├── Library.java         ← Core business logic and data management
└── Main.java            ← Console menu and entry point
```

---

## What Each Class Does

| Class | Type | Responsibility |
|---|---|---|
| `LibraryItem` | Abstract Class | Blueprint for all library items. Holds common fields: `id`, `title`, `isAvailable`. Declares abstract `displayInfo()` |
| `Book` | Concrete Class | Extends `LibraryItem`. Adds `author` field. Implements `displayInfo()` to print book details |
| `Person` | Abstract Class | Blueprint for all people in the system. Holds common fields: `id`, `name`, `email`. Declares abstract `displayInfo()` |
| `Member` | Concrete Class | Extends `Person`. Adds `borrowedBookIds` list. Manages borrow limit with `canBorrowMore()` |
| `Transaction` | Concrete Class | Records a single borrow event. Stores `bookId`, `memberId`, `issueDate`, `dueDate`, `returnDate`, and `fine` |
| `Library` | Concrete Class | The control room. Holds lists of books, members, transactions. Contains all business logic: `issueBook()`, `returnBook()`, `calculateFine()` |
| `Main` | Entry Point | Console menu. Takes user input via `Scanner` and calls the correct `Library` methods |

---

## How Classes Are Connected

```
LibraryItem (abstract)
    └── Book

Person (abstract)
    └── Member

Library
    ├── holds → List<Book>
    ├── holds → List<Member>
    └── holds → List<Transaction>

Transaction
    ├── references → bookId (String)
    └── references → memberId (String)
```

---

## Features

- Add new books to the library
- View all books with availability status
- Search books by title or author
- Register new members
- View all members
- Issue a book to a member (with validation)
- Return a book (with automatic fine calculation)
- View all transaction history
- Pre-loaded sample data for quick testing

---

## OOP Concepts Used

| Concept | Where Used | Description |
|---|---|---|
| **Abstraction** | `LibraryItem`, `Person` | Both are abstract classes. Object cannot be created directly. Only the essential structure is exposed. `displayInfo()` is declared but not defined |
| **Inheritance** | `Book extends LibraryItem` | Book inherits `id`, `title`, `isAvailable` and all getter/setters from `LibraryItem` without rewriting them |
| **Inheritance** | `Member extends Person` | Member inherits `id`, `name`, `email` from `Person`. Avoids code duplication |
| **Polymorphism** | `displayInfo()` in `Book`, `Member` | Same method name, different behavior. `book.displayInfo()` prints book info, `member.displayInfo()` prints member info. Decided at runtime |
| **Encapsulation** | All classes | All fields are `private`. Access is only through `public` getter/setter methods. Prevents invalid data from entering the system |

---

## Other Java Concepts Used

| Term | Where Used | Why Used |
|---|---|---|
| `static` | `library`, `scanner` in `Main.java` | Static methods (`main`, `addBookFlow`) can only access static variables. Declaring them static makes them shared across all methods |
| `static final` | `FINE_PER_DAY = 10` in `Library.java` | Constant value that never changes. `final` prevents modification, `static` means one copy shared by all — no memory waste |
| `abstract` | `LibraryItem`, `Person` | Prevents direct object creation. Forces subclasses to implement `displayInfo()` |
| `@Override` | `displayInfo()` in `Book`, `Member` | Tells compiler this method overrides a parent method. If parent method doesn't exist, compiler throws error |
| `extends` | `Book`, `Member` | Establishes IS-A relationship. Book IS-A LibraryItem. Member IS-A Person |
| `super()` | Constructor of `Book`, `Member` | Calls parent class constructor to initialize inherited fields (`id`, `title`, etc.) |
| `this` | Constructors, setters | Refers to current object. Differentiates field from parameter when names are same |
| `.equals()` | `findBookByID()`, `findMemberByID()` | Compares String content. `==` compares reference (memory address), `.equals()` compares actual value |
| `instanceof` | — | Not used here but available if type-checking is needed between `LibraryItem` types |
| `ArrayList` | `Library.java` | Dynamic list. Size grows automatically as books/members are added. Used instead of fixed-size array |
| `List<T>` | `Library.java`, `Member.java` | Interface type used for declaration. `ArrayList` is the implementation. Follows "program to interface" principle |
| `LocalDate` | `Transaction.java`, `Library.java` | Modern Java 8+ date API. Used for `issueDate`, `dueDate`, `returnDate` |
| `LocalDate.now()` | `Transaction.java` | Gets today's date automatically at the time of issue/return |
| `.plusDays(7)` | `Transaction.java` | Calculates due date: issue date + 7 days |
| `ChronoUnit.DAYS.between()` | `Library.java` | Calculates exact number of days between two dates for fine calculation |
| `null` check | `returnBook()`, `issueBook()` | Checks if book/member/transaction exists before proceeding. Prevents `NullPointerException` |
| `try-catch` | `getIntInput()` in `Main.java` | Catches `NumberFormatException` when user enters text instead of number |
| `private static` | Methods in `Main.java` | Helper methods only used inside `Main`. `private` hides from outside, `static` allows call without object |
| `trim()` | `getStringInput()` | Removes leading/trailing whitespace from user input |
| `toLowerCase()` | `searchBooks()` | Makes search case-insensitive. "JAVA" and "java" both match |
| `Scanner` | `Main.java` | Reads user input from console (`System.in`) |
| `while(true)` | `Main.java` | Keeps menu running until user selects Exit (case 9) |
| `return` | Multiple methods | Exits method early when validation fails (e.g., book not found, limit reached) |

---

## How to Run

```bash
# 1. Compile all Java files
javac *.java

# 2. Run the Main class
java Main
```

---

## Sample Console Output

```
Sample data loaded successfully!

==================================
   LIBRARY MANAGEMENT SYSTEM
==================================
1. Add Book
2. View All Books
3. Search Books
4. Register Member
5. View All Members
6. Issue Book
7. Return Book
8. View All Transactions
9. Exit
==================================
Enter your choice: 6

--- Issue Book ---
Enter Member ID: M1
Enter Book ID: B1

Book issued successfully!
Transaction ID: T1
Due Date: 2026-05-03
```

---

## Known Limitations (Prototype)

- Data is stored **in-memory only** — all data is lost when program exits
- No duplicate ID validation when adding books or members
- No persistent storage (no file or database)
- No user authentication
- No unit tests

These would be addressed in a production version using JDBC, Hibernate, and Spring Boot.

---

## Tech Stack

- **Language:** Java (JDK 8+)
- **Storage:** In-memory (ArrayList)
- **Interface:** Console (Scanner)
- **IDE:** IntelliJ IDEA
- **Version Control:** Git + GitHub
