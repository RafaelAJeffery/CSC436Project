package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Observable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Model extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;    // UID for serializable
	
	private ArrayList<Book> bookList;

	
	//constructor
	public Model() {
		/*
		 * TODO: be able to read in a csv file and create book objects out of
		 * that file
		 */
		bookList= new ArrayList<Book>();/* initializes the list storing books
		 								 * This is just for testing purposes rn, this will be
		 								 * different once we get communication working between the
		 								 * model and the MySQL databases
		 								 */
		
		try {
			File database= new File(Main.savedArgs[0]);
			Scanner scan= new Scanner(database);
			scan.nextLine();// skips the first line of the file
			String[] dataSplit; // will be filled when iterating through the file
			while(scan.hasNextLine()) {
				dataSplit= scan.nextLine().split(",");// splitting entry sections by comma
				
				//fields to make a Book object
				String series= dataSplit[0];
				String ISBN = dataSplit[1];
				String publicationName= dataSplit[2];
				String publisher= dataSplit[3];
				
				Book toAdd= new Book(publicationName, ISBN, "Book", "Nonfiction", publisher);
				
				addBook(toAdd);
				
			}
			scan.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		

		
	}
	
	
	/*
	 * Purpose: returns a book if its name matches
	 * bookName
	 */
	public Book getBook(String bookName) {
		Book expected;
		for(Book book : bookList) {
			if (book.getTitle().equals(bookName)){
				expected= book;
				return expected;
			}
		}
				return null;
	}
	
	public void addBook(Book toAdd) {
		this.bookList.add(bookList.size(), toAdd);
	}
	
	/*
	 * *******************README**********************
	 * 
	 * Purpose: for the purpose of iteration 1, this 
	 * function will search by matching some keyword
	 * to the publisher of a book. This is preetty much 
	 * just searching by publisher name 
	 * 
	 * @returns a List of books that contains the search 
	 * results
	 */
	public List<Book> search(String keyword, String scope){
		List<Book> searchResult= new ArrayList<Book>();
		for(Book book : bookList) {
			if(scope.equals("Book Title")) {
				if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
					searchResult.add(book);
				}
			}else if(scope.equals("ISBN Number")) {
				if (book.getISBN().toLowerCase().contains(keyword.toLowerCase())){
					searchResult.add(book);
				}
			}else if(scope.equals("Format")) {
				if (book.getFormat().toLowerCase().contains(keyword.toLowerCase())){
					searchResult.add(book);
				}
			}else if(scope.equals("Genre")) {
				if (book.getGenre().toLowerCase().contains(keyword.toLowerCase())){
					searchResult.add(book);
				}
			}else if(scope.equals("Publisher")) {
				if (book.getPub().toLowerCase().contains(keyword.toLowerCase())){
					searchResult.add(book);
				}
			}else {
				return bookList;
			}
		}
		return searchResult;
	}
	
	
	/*
	 * Purpose: to be used when making a reservation 
	 * for a certain item to see if it is 
	 * available for checkout or not
	 * 
	 */
	private boolean isAvailable() {
		/*
		 * if the book is available on the database
		 * return true, otherwise return false
		 * */
		return false;
	}
	
	
	/*
	 * Purpose: to reserve an item that is 
	 * in the database
	 * 
	 */
	public void makeReservation() {
		//database.reserve(user,book)
		
	}
	
	public void returnBook(Book toReturn) {
		
		
	}
	
	public ArrayList<Book> getBookList(){
		return this.bookList;
	}
	
	
	/*
	 * for later use with more complicated UI
	 * 
	 */
	public void updatePane() {
		setChanged();
		notifyObservers(bookList);
	}

}// end of class