package Library.MVC;

public class Book {
	
	private String title;
	private String ISBN;
	private String format;
	private String genre;
	private String publisher;
	private boolean avaliable;
	
	public Book() {
		this("","","","","");
	}

	public Book(String title, String ISBN, String format, String genre, String publisher) {
		this.title = title;
		this.ISBN = ISBN;
		this.genre = genre;
		this.format = format;
		this.publisher = publisher;
		this.avaliable = true;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getPub() {
		return publisher;
	}
	
	public void setPub(String publisher) {
		this.publisher = publisher;
	}
	
	public boolean getAva() {
		return avaliable;
	}
	
	public void setAva(boolean avaliable) {
		this.avaliable = avaliable;
	}
}
