import java.util.*;
public class Book {
/**
 * @author zackleibowitz
 * 
 */
	/*
	 * @TODO: insert fields that the books will posses 
	 * when searching for them.
	 * 
	 * */
	
	private int ISBN;
	private int yearPublished;
	private int numPages;
	private String title;
	private String author;
	private String genre;
	private ArrayList<String> tags;
	
	//fields for second constructor not shared w/ first constructor
	private String series;
	private String publicationName;
	private String publisher;
	
	
	// constructor
	public Book(int ISBN, int yearPub, int numPages, String title, String author, String genre) {
		//initialize fields from the databse entry
		this.ISBN= ISBN;
		this.yearPublished= yearPub;
		this.numPages= numPages;
		this.title= title; 
		this.author= author;
		this.genre= genre;
		
			this.tags= new ArrayList<String>();
	}
	
	/*
	 * NOTE: THIS SECOND CONSTRUCTOR IS FOR TESTING PURPOSES ONLY
	 * it creates a book object with the fields included in the 
	 * test1.csv file. A thought is that we may need to re work this 
	 * object at some point after this iteration
	 * 
	 */
	public Book(String series, int ISBN, String pubName, String publisher) {
		this.series= series; 
		this.ISBN= ISBN;
		this.publicationName= pubName;
		this.publisher= publisher;
		
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
	public int getISBN() {
		return this.ISBN;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public int getYP() {
		return this.yearPublished;
	}
	
	public int getNP() {
		return this.numPages;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public ArrayList<String> getTags(){
		return this.tags;
	}
	
	public void setTitle(String newTitle) {
		this.title= newTitle;
	}
	
	public void setISBN(int newISBN) {
		this.ISBN= newISBN;
	}
	
	public void setYearPublished(int newYear) {
		this.yearPublished = newYear;
	}
	
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
	//getters and setters for second constructor
	public String getSeries() {
		return this.series;
	}
	
	public String getPublicationName(){
		return this.publicationName;
		
	}
	
	public String getPublisher() {
		return this.publisher;
	}
	
	

}
