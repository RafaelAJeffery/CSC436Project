import java.util.*;
public class Book {
	
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

}
