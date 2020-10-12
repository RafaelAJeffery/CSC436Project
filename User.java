import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


/*
 * TODO: make this an abstract class and make other classes
 * for a normal user, librarian, etc.
 * 
 * for librarain: allow them to be able to modify the ADTs for overdue
 * and current items. Once database is established and user accounts are as well
 * allow the librarian to make accounts and close them.
 */

public class User {
	
	private int userID;
	private String firstName;
	private String lastName;
	private Set<Book> overdueItems;
	private ArrayList<Book> currentItems; // represents current items the user has currently checked out
	
	
	public User(int ID, String fName, String lName) {
		this.userID = ID;
		this.firstName= fName;
		this.lastName= lName;
		
		this.overdueItems= new TreeSet<Book>();
		this.currentItems= new ArrayList<Book>();
	}
	
	public int getID() {
		return this.userID;
	}
	
	public String getFName() {
		return this.firstName;
	}
	
	public String getLName() {
		return this.lastName;
	}
	
	public String getUserName() {
		String fullName= firstName + lastName;
		return fullName;
	}
	
	public Set<Book> getOverdueItems(){
		return this.overdueItems;
	}
	
	public void addOverdueItem(Book overdueBook) {
		this.overdueItems.add(overdueBook);
		
	}
	
	public void removeOverdueItem(Book item) {
		this.overdueItems.remove(item);
	}
	
	public void setUserID(int newID) {
		this.userID= newID;
	}
	
	public void setFName(String newFName) {
		this.firstName= newFName;
	}
	
	public void setLName(String newLName) {
		this.lastName= newLName;
	}
	
}
