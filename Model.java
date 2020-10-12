import java.io.Serializable;
import java.util.Observable;
import java.util.ArrayList;
import java.util.List;


public class Model extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;    // UID for serializable
	
	private ArrayList<Book> bookList;
	
	//constructor
	public Model() {
		
		bookList= new ArrayList<Book>();/* initializes the list storing books
		
										 * This is just for testing purposes rn, this will be
										 * different once we get communication working between the
										 * model and the MySQL databases
		 								*/
		
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
		this.bookList.add(toAdd);
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
	public List<Book> search(String keyword){
		List<Book> searchResult= new ArrayList<Book>();
		for(Book book : bookList) {
			if (book.getPublisher().equals(keyword)){
				searchResult.add(book);
			}
		}
		return searchResult;
	}
	
	
	/*
	 * Purpose: to be used when making a reservation 
	 * for a certrain item to see if it is 
	 * available for checkout or not
	 * 
	 */
	private boolean isAvailable() {
		/*
		 * if the book is available on the databse
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
