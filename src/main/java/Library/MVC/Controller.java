package Library.MVC;

import Library.SQLConnection.SQLConnection;
import Library.MVC.Book;
import Library.MVC.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	public void insertBook(Book book) {
		if (SQLConnection.sqlConnection == null)	return;
		
		String query = String.format("INSERT INTO BOOK VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");", 
				book.getTitle(),
				book.getISBN(),
				book.getFormat(),
				book.getGenre(),
				book.getPub());
		
		SQLConnection.execute(query);
	}
	
	/*
    Get book from search terms
    */
    public List<Book> search(String scope, String keyword) {
    	String filterTag = "";
    	if (scope.equals("Book Title")) {
    		filterTag = "title";
    	} else if (scope.equals("ISBN Number")) {
    		filterTag = "isbn";
    	} else if (scope.equals("Format")) {
    		filterTag = "format";
    	} else if (scope.equals("Genre")) {
    		filterTag = "genre";
    	} else if (scope.equals("Publisher")) {
    		filterTag = "publisher";
    	}
    	List<Book> bookList = selectBooksByFilter(15, new Filter(filterTag, keyword));
        return bookList;
    }
	
    /*
     * select books from sql based on params
     * @param numResults {int} number of results to return (-1: all, 15: default)
     * @param filter {Filter} filter to add to the select statement
     * @return results of select statement
     */
	private List<Book> selectBooksByFilter(int numResults, Filter filter) {
		String query = "";
		if (filter.toString().equals("")) {
			query ="SELECT * FROM BOOK ORDER BY title DESC";
		} else {
			query = String.format("SELECT * FROM BOOK %s ORDER BY title", filter.toString());
		}
		
		if (numResults > 0) {
			// query += String.format(" LIMIT %d", numResults);
		}
		
		ResultSet rs = SQLConnection.executeQuery(query);
		
		List<Book> books = new ArrayList<Book>();
		
		if (rs != null) {
			try {
				while(rs.next()) {
					Book book = new Book();
					
					book.setTitle(rs.getString("title"));
					book.setISBN(rs.getString("isbn"));
					book.setFormat(rs.getString("format"));
					book.setGenre(rs.getString("genre"));
					book.setPub(rs.getString("publisher"));
					
					books.add(book);
				}
			} catch (SQLException e) {
				books = new ArrayList<Book>();
			}
		}
		
		return books;
	}
    
    // Filter & FilterSet
    
	/*
	 * Filter (scope and keyword) for SQL statements
	 */
    private class Filter {
    	
    	private String scope;
    	private String keyword;
    	
    	public Filter(String scope, String keyword) {
    		this.scope = scope;
    		this.keyword = keyword;
    	}
    	
    	@Override
    	public String toString() {
    		if (scope.equals("")) return "";
    		return "WHERE " + scope + " LIKE \'%" + keyword + '%' + '\'';
    	}
    }
}
