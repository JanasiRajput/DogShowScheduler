

# Dog Show Scheduler

This is a **Spring Boot web application** for organizing dog show judging. It allows users to manage owners, dogs, and judges, and assign relationships between them. The application uses **Thymeleaf** for front-end templates, **H2** for the database, and **Bootstrap** for styling.

## Features

* Add **Owners**, **Dogs**, and **Judges**.
* Assign **Owners to Dogs**.
* Assign **Judges to Dogs** with dropdown selections.
* View details for a **Dog**: its owner and assigned judges.
* Drop a judge from a dog directly from the dog details page.
* View details for an **Owner**: all dogs and judges per dog.
* View details for a **Judge**: all dogs to be judged and their owners.
* Fully navigable from a **root page** (`localhost:8080`).

## Dummy Data

* 6 Owners
* 30 Dogs
* 3 Judges

Data is initialized using the **BootstrapData** class.

## Tech Stack

* **Java** + **Spring Boot**
* **Thymeleaf**
* **H2 Database**
* **Bootstrap** (CSS/HTML for styling)
* **JavaScript** (for styling/animations only, not core functionality)

## Navigation

All pages are linked to the root page, allowing easy navigation between entities and their relationships.

---


