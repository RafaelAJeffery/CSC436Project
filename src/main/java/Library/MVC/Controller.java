package Library.MVC;

import Library.SQLConnection.SQLConnection;
import Library.MVC.Book;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    /*
    Get book from search terms
    */
    public ArrayList<Book> getBookResults(String[] args) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        String selectStr = "SELECT " + " WHATEVER" + "FROM Media.Book";
        try {
            Statement stmt = SQLConnection.sqlConnection.createStatement();

            ResultSet rs = stmt.executeQuery(selectStr);

            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("Title"));
                book.setISBN(rs.getString("ISBN"));
                // and etc.
                bookList.add(new Book());
            }
        }
        catch(SQLException e) {
            SQLConnection.closeConnection();
        }

        return bookList;
    }

}
