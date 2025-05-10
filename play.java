import java.util.*;
import java.io.*;

public class play {

	public static void main(String[] args) {
		ArrayList<LibraryBook> books = new ArrayList<>(50); // creates an ArrayList to store LibraryBook objects 
		Scanner scanner = new Scanner(System.in); // creates Scanner for user input 
	
		initialMenu(books); // calls the initial menu to select input file 
		
		String choice;
		do {
			mainMenu(); // calls the main menu with three options for user 
			choice = scanner.nextLine();
			switch(choice) {
				case "1": // to display all book records  
					if(!books.isEmpty()) {
						clearScreen();
						System.out.println("Displaying All Book Records: ");
						displayRecords(books, scanner);
					} else {
						System.out.println("No Book Records Found");
					}
					break;
				case "2": // to search for a book by title and display it  
					int numBooks = books.size();
					sortBooks(books);
					binarySearch(books, numBooks);
					break;
				case "3": // to exit the program 
					System.out.println("Exiting the program. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while(!choice.equals("3"));
		
		scanner.close();
	}

	// reads book information from a file and populates the ArrayList.
	public static int inputBooks(String inputFile, ArrayList<LibraryBook> books) {
		try {
			Scanner in = new Scanner(new File(inputFile));
			while(in.hasNext()) { // reads book info from file until the end of file 
				Scanner lsc = new Scanner(in.nextLine()).useDelimiter(";");
				String title = lsc.next();
				String name = lsc.next();
				int copyright = lsc.nextInt();
				double price = lsc.nextDouble();
				String genreConvert = lsc.next();
		
				String genreString;
				switch (genreConvert) { // converts genres to be user-friendly
					case "f":
						genreString = "Fiction";
						break;
					case "p":
						genreString = "Poetry";
						break;
					case "d":
						genreString = "Drama";
						break;
					case "n":
						genreString = "Nonfiction";
						break;
					default:
						genreString = "Unknown Genre";
				}
				books.add(new LibraryBook(title, name, copyright, price, genreString)); // adds book to arrayList 
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return books.size();
	}

	// displays the initial menu for file selection.
	public static void initialMenu(ArrayList<LibraryBook> books) {
		clearScreen();
		
		// displays file selection prompt 
		System.out.println("THE BOOK SEARCH PROGRAM");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What file is your book data stored in? ");
		System.out.println("Here are the files in the current directory: ");
		System.out.println("library.dat  play.dat");

		Scanner scanner = new Scanner(System.in); // creates scanner for user input 
		System.out.print("Filename: "); // user enters file name
		String newFileType = scanner.nextLine(); 

		// reads and sorts books from input file based on user's choice 
		if (newFileType.equals("library.dat")) {
			inputBooks(newFileType, books);
			sortBooks(books);
			System.out.println("A total of " + books.size() + " books have been input and sorted by title.");
		} else if (newFileType.equals("play.dat")) {
			inputBooks(newFileType, books);
			sortBooks(books);
			System.out.println("A total of " + books.size() + " books have been input and sorted by title.");
		} else {
			System.out.print(" ** Can't open input file. Try again. **");
		}
		System.out.println("Please Hit Return to Continue..."); // allows user to continue
		scanner.nextLine();
	}

	// displays the second menu for display of all book records, book search, and program exit.
	public static void mainMenu() {
		clearScreen();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("THE GREAT BOOKS SEARCH PROGRAM");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("1) Display all book records");
		System.out.println("2) Search for a book by Title");
		System.out.println("3) Exit Search Program");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print("Please Enter Your Choice > ");
	}

	// displays book records with an option to continue or return to the menu.
	public static void displayRecords(ArrayList<LibraryBook> books, Scanner scanner) {
		int numBooks = books.size();
		for (int i = 0; i < numBooks; i++) { // iterates through books in the array list
			System.out.println();
			printRecord(books, i); // calls printRecord to display book details
			System.out.println();
			if (i < numBooks) {
				System.out.println("Please Hit Return to Continue or M for menu...");
				String userInput = scanner.nextLine().toLowerCase();
				while (!userInput.isEmpty() && !userInput.equals("m")) {
					System.out.println("Invalid input. Please Hit Return to Continue or M for menu...");
					userInput = scanner.nextLine().toLowerCase();
				}
				if (userInput.equals("m")) {
					clearScreen();
					break;
				}
			}
			clearScreen();
		}
	}

	// searches for a book by title and displays the results.
	public static void binarySearch(ArrayList<LibraryBook> books, int bookCount) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Search Title > "); // prompts user
		String searchTitle = scan.nextLine();
	
		// binary search to find the book 
		int first = 0;
		int last = books.size() - 1;
		int middle = 0;
		int location = 0;
		boolean found = false;

		do {
			middle = (first + last) / 2;
			if(searchTitle.compareTo(books.get(middle).getTitle()) == 0) {
				found = true;
			} else if(searchTitle.compareTo(books.get(middle).getTitle()) < 0) {
				last = middle - 1;
			} else {
				first = middle + 1;
			}
		} while(!found && first <= last);

		location = middle; 
		if(found) {
			clearScreen();
			printRecord(books, location);
		} else {
			System.out.println("Book not found.");
		}		
		System.out.println("Please Hit Return to Continue...");
		scan.nextLine();
	} 


	// displays a single book record
	private static void printRecord(ArrayList<LibraryBook> books, int location) {
		System.out.println("Record #" + (location + 1) + " :\n");
		System.out.println("Title: " + books.get(location).getTitle());
		System.out.println("Author's Name: " + books.get(location).getAuthor());
		System.out.println("Copyright: " + books.get(location).getCopyright());
		System.out.println("Price: " + books.get(location).getPrice());
		System.out.println("Genre: " + books.get(location).getGenre() + "\n");
	}	

	// selection sort to sort the list of books based on their titles.
	public static void sortBooks(ArrayList<LibraryBook> books) {
		int minIndex;
		int size = books.size();
		for(int i = 0; i < size - 1; i++) {
			minIndex = i;
			for(int j = i + 1; j < size; j++) {
				if((books.get(j).getTitle().compareTo(books.get(minIndex).getTitle())) < 0){
					minIndex = j;
				}
			}
			if(minIndex != i) {
				Collections.swap(books, i, minIndex);
			}			
		}
		// System.out.println("Debug: Books after sorting: " + books); 
	}

	// clears the console screen.
	public static void clearScreen() {
		System.out.println("\u001b[H\u001b[2J");
	}
}
