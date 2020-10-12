import java.io.Serializable;
import java.util.Observable;
import java.util.ArrayList;


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
