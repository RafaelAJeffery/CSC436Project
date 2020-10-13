package src;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View extends Application {

	private Stage stage;
	private BorderPane pane;
	private GridPane grid = new GridPane();
	private String query;
	private String selection;
	private int itemCount = 0;
	private MenuBar cart = new MenuBar();
	private Menu cartMenu = new Menu("Cart (" + itemCount + ")");
	private MenuItem none = new MenuItem("Cart is Empty");
	private Model model = new Model();
	private int numBooks = 2;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		pane = new BorderPane();
		ScrollPane scroll = new ScrollPane();
		
		
		cart.prefWidthProperty().bind(primaryStage.widthProperty());
		cartMenu.getItems().add(none);
		cart.getMenus().add(cartMenu);
		pane.setTop(cart);
		
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		stage.setHeight(400);
		stage.setWidth(600);
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		String selections[] = {"Book Title", "ISBN Number", "Format", "Genre", "Publisher", "Author Name", "Keywords"};
		
		ComboBox dropDown = new ComboBox(FXCollections.observableArrayList(selections));
		
		dropDown.setOnAction((getQuery) -> {
			selection = (String) dropDown.getValue();
			System.out.println(selection);
		});
		
		Label searchQuery = new Label("Search What?");
		TextField searchTerm = new TextField("");
		Button searchButton = new Button("Search!");
		
		searchButton.setOnAction((getQuery) -> {
			query = searchTerm.getText();
			System.out.println(query);
			List<Book> bookList;
			if(query.isEmpty()) {
				bookList = model.getBookList();
			}else {
				bookList = model.search(query, (String)dropDown.getValue());
			}
			
			for(int i = 2; i < numBooks; i++) {
				grid.getChildren().remove(2);
			}
			
			numBooks = 2;
			for(Book book : bookList) {
				makeBookImage(numBooks, book);
				numBooks++;
			}
		});
		
		HBox searchBox = new HBox(4);
		
		searchBox.getChildren().addAll(searchQuery, searchTerm, dropDown, searchButton);
		searchBox.setAlignment(Pos.CENTER);
		searchBox.prefWidthProperty().bind(primaryStage.widthProperty());
		
		Label welcome = new Label("Welcome to the Library!");
		
		grid.add(welcome, 0, 0);
		grid.add(searchBox, 0, 1);
		
		GridPane.setHalignment(welcome, HPos.CENTER);
		GridPane.setHalignment(searchBox, HPos.CENTER);
		pane.setCenter(grid);
		
		scroll.setContent(pane);

		Scene scene = new Scene(scroll);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void makeBookImage(int row, Book book) {
		GridPane BookImage = new GridPane();
		BorderPane background = new BorderPane();
		BookImage.setHgap(10);
		BookImage.setVgap(10);
		BookImage.setPadding(new Insets(10, 10, 10, 10));
		background.setPadding(new Insets(10, 10, 10, 10));
		
		HBox title = new HBox(2);
		HBox isbnFormat = new HBox(5);
		HBox genrePub = new HBox(5);
		HBox avaliable = new HBox(4);
		
		title.prefWidthProperty().bind(background.widthProperty());
		isbnFormat.prefWidthProperty().bind(background.widthProperty());
		genrePub.prefWidthProperty().bind(background.widthProperty());
		avaliable.prefWidthProperty().bind(background.widthProperty());
		BookImage.prefWidthProperty().bind(background.widthProperty());
		background.prefWidthProperty().bind(stage.widthProperty());
		
		Label isbnTab = new Label("		");
		Label genreTab = new Label("			   ");
		
		Label titleLabel = new Label("Title: ");
		Label bookName = new Label(book.getTitle());
		
		title.getChildren().addAll(titleLabel, bookName);
		BookImage.add(title, 0, 0);
		
		Label isbnLabel = new Label("ISBN: ");
		Label isbnName = new Label(book.getISBN());
		Label formatLabel = new Label("Format: ");
		Label formatName = new Label(book.getFormat());
		
		isbnFormat.getChildren().addAll(isbnLabel, isbnName, isbnTab, formatLabel, formatName);
		BookImage.add(isbnFormat, 0, 1);
		
		Label genreLabel = new Label("Genre: ");
		Label genreName = new Label(book.getGenre());
		Label pubLabel = new Label("Publisher: ");
		Label pubName = new Label(book.getPub());
		
		genrePub.getChildren().addAll(genreLabel, genreName, genreTab, pubLabel, pubName);
		BookImage.add(genrePub, 0, 2);
		
		MenuItem add = new MenuItem(book.getTitle());
		Label avaliableLabel = new Label("Avaliable: ");
		Label avaliableName;
		String buttonString = "Make Reservation";
		if(book.getAva() == true) {
			avaliableName = new Label("Avaliable");
		}else {
			avaliableName = new Label("Not avaliable");
			for(MenuItem item: cartMenu.getItems()){
				if(item.getText().equals(book.getTitle())) {
					buttonString = "In Cart";
				}else {
					buttonString = "Not Avaliable";
				};
			};
			
		};
		Button reserveButton = new Button(buttonString);
		Label tab = new Label("					");
		
		
		
		
		
		reserveButton.setOnAction((reserveItem) -> { 
			if(reserveButton.getText() == "Make Reservation") {
				System.out.println("reserved!");
				reserveButton.setText("In Cart");
				avaliableName.setText("Not avaliable");
				book.setAva(false);
				itemCount ++;
				cartMenu.setText("Cart ("+ itemCount + ")");
				if(cartMenu.getItems().contains(none)) {
					cartMenu.getItems().remove(none);
				}
				cartMenu.getItems().add(add);
			}else {
				System.out.println("unreserved!");
				reserveButton.setText("Make Reservation");
				avaliableName.setText("Avaliable");
				book.setAva(true);
				itemCount --;
				cartMenu.setText("Cart ("+ itemCount + ")");
				if(itemCount == 0) {
					cartMenu.getItems().add(none);
				}
				cartMenu.getItems().remove(add);
			}
			
		});
		
		avaliable.getChildren().addAll(avaliableLabel, avaliableName, tab, reserveButton);
		BookImage.add(avaliable, 0, 3);
		
		background.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		background.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		background.setCenter(BookImage);
		
		grid.add(background, 0, row);
		GridPane.setHalignment(BookImage, HPos.CENTER);
	}


}
