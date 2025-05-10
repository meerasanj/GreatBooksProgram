# Great Books Search Program

## Overview

The **Great Books Search Program** is a console-based Java application that allows users to load and manage a library of book records. Users can:

1. Display all book records sorted alphabetically by title.
2. Search for a book by title using binary search.
3. Exit the program gracefully.

The program uses **selection sort** for organizing book records and **binary search** for efficient lookup.

---

## Program Workflow

### Initial Input Menu

Upon launch, the program prompts the user to enter the name of a file containing book data (e.g., `library.dat` or `play.dat`). If the filename is valid:

- The program reads the data using a `Scanner`.
- The book records are stored in an `ArrayList`.
- The books are sorted alphabetically by title using selection sort.
- A message indicating how many books were loaded is shown.

If the input is invalid, an error message is displayed, and the user is asked to try again.

---

## Main Menu Options

Once the data is loaded, the following menu options are available:

### 1️⃣ Display All Book Records

- Books are displayed in alphabetical order (sorted via selection sort).
- Results are paginated, allowing the user to:
  - Press **Enter** to continue to the next page.
  - Press **M** to return to the main menu.

### 2️⃣ Search for a Book by Title

- The user is prompted to enter a title.
- A **binary search** is performed on the sorted list:
  - If the title is found, the book's details are displayed.
  - If not found, a message is shown.
- After displaying the result, the user presses **Enter** to return to the main menu.
- Users can search repeatedly until they choose to return.

### 3️⃣ Exit the Program

- Displays a goodbye message and exits cleanly.

---

## Algorithm Explanation

### Selection Sort (for Book Titles)

Given an unsorted array (e.g., `{4, 1, 3, 5, 2}`):
- Find the minimum value and swap it with the first element: `{1, 4, 3, 5, 2}`
- Continue finding the next smallest and swap in place: `{1, 2, 3, 5, 4}`
- Repeat until fully sorted: `{1, 2, 3, 4, 5}`

### Binary Search (for Searching Titles)

Given a sorted array:
- Compare the target with the middle element.
- If equal, return the result.
- If less, repeat in the left half.
- If greater, repeat in the right half.
- Continue until the target is found or the subarray is empty.

Example: Searching for `3` in `{1, 2, 3, 4, 5}` compares with middle element `3` → found at index 2.

---

## Input Files

- `library.dat`
- `play.dat`

These files should contain properly formatted book records that the program can parse and load.

---

## Utility Method

- `clearScreen()` is used to clear the console output between different views (may depend on system compatibility).

---

## How to Compile and Run
```bash
javac play.java
java play
```
