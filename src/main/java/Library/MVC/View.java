package Library.MVC;

import Library.SQLConnection.SQLConnection;

import java.util.ArrayList;
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
	private int forumCount;
	private int commentCount = 0;
	
	private Stage stage;
	private Stage forumStage;
	private BorderPane pane;
	private GridPane grid = new GridPane();
	private GridPane forumGrid = new GridPane();
	
	private String query;
	private String selection;
	
	private int itemCount = 0;
	private MenuBar cart = new MenuBar();
	private Menu cartMenu = new Menu("Cart (" + itemCount + ")");
	private MenuItem reserve = new MenuItem("Confirm Reservation");
	private MenuItem none = new MenuItem("Cart is Empty");
	
	private ArrayList<Book> bookList = new ArrayList<Book>();
	private Model model = new Model();
	private Controller controller = new Controller();
	private int numBooks = 2;
	private String username;
	
	public static void main(String[] args) {
		SQLConnection.makeConnection();
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("SafeSearch: Log In");
		pane = new BorderPane();
		ScrollPane scroll = new ScrollPane();
		Scene scene;
		
		stage.setHeight(400);
		stage.setWidth(600);
		
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		pane.setPadding(new Insets(100, 200, 100, 200));
		
		TextField username = new TextField("Username");
		TextField password = new TextField("Password");
		Button enter = new Button("Enter");
		VBox signIn = new VBox(3);
		
		signIn.prefWidthProperty().bind(pane.widthProperty());
		signIn.prefHeightProperty().bind(pane.heightProperty());
		signIn.setAlignment(Pos.CENTER);
		
		signIn.getChildren().addAll(username, password, enter);
		
		
		pane.setCenter(signIn);
		
		scroll.setContent(pane);
		
		scene = new Scene(scroll);
		
		enter.setOnAction((openUp) -> {
			boolean userFound = true; // set to the user search
			if(userFound = true) {
				this.username = username.getText();
				stage.setTitle("SafeSearch: Search Menu");
				cart.prefWidthProperty().bind(primaryStage.widthProperty());
				cartMenu.getItems().add(none);
				cart.getMenus().add(cartMenu);
				pane.setTop(cart);
				pane.setPadding(new Insets(0, 0, 0, 0));
	
				
				String selections[] = {"Book Title", "ISBN Number", "Format", "Genre", "Publisher", "Author Name", "Keywords"};
				
				ComboBox dropDown = new ComboBox(FXCollections.observableArrayList(selections));
				
				dropDown.setOnAction((getQuery) -> {
					selection = (String) dropDown.getValue();
					System.out.println(selection);
				});
				
				Label searchQuery = new Label("Search What?");
				TextField searchTerm = new TextField("");
				Button searchButton = new Button("Search!");
				Button forumOpen = new Button("Open Forum");
				
				searchButton.setOnAction((getQuery) -> {
					query = searchTerm.getText();
					System.out.println(query);
					List<Book> bookList;
					if(query.isEmpty()) {
						bookList = model.getBookList();
					}else {
						bookList = controller.search((String)dropDown.getValue(), query);
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
				
				forumOpen.setOnAction((openForum) -> {
					forumStage();
				});
				
				HBox searchBox = new HBox(5);
				
				searchBox.getChildren().addAll(searchQuery, searchTerm, dropDown, searchButton, forumOpen);
				searchBox.setAlignment(Pos.CENTER);
				searchBox.prefWidthProperty().bind(primaryStage.widthProperty());
				
				Label welcome = new Label("Welcome to the Library!");
				
				grid.add(welcome, 0, 0);
				grid.add(searchBox, 0, 1);
				
				GridPane.setHalignment(welcome, HPos.CENTER);
				GridPane.setHalignment(searchBox, HPos.CENTER);
				pane.setCenter(grid);
				
				scroll.setContent(pane);
				stage.setScene(scene);
				stage.show();
			}
		});

		stage.setOnCloseRequest(e -> {
			SQLConnection.closeConnection();
		});
		
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
		
		
		
		reserve.setOnAction((reserveWindow) -> {
			reserveStage();
		});
		
		
		
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
				if(cartMenu.getItems().contains(reserve)) {
					cartMenu.getItems().remove(reserve);
				}
				cartMenu.getItems().add(add);
				cartMenu.getItems().add(reserve);
				bookList.add(book);
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
				bookList.remove(book);
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
	
	public Stage reserveStage() {
		Stage reserveStage = new Stage();
		BorderPane border = new BorderPane();
		ScrollPane scroll = new ScrollPane();
		VBox books = new VBox(bookList.size());
		
		border.setPadding(new Insets(10, 10, 10, 10));
		
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		reserveStage.setHeight(200);
		reserveStage.setWidth(300);
		
	
		Label header = new Label("You Reserved:");
		border.setTop(header);
		BorderPane.setAlignment(header, Pos.TOP_CENTER);
		
		for(Book book : bookList) {
			Label label = new Label(book.getTitle());
			books.getChildren().add(label);
		}
		books.setAlignment(Pos.TOP_CENTER);
		border.setCenter(books);
		BorderPane.setAlignment(books, Pos.TOP_CENTER);
		
		String selections[] = {"8:00","8:15","8:30","8:45","9:00","9:15","9:30","9:45","10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","1:00","1:15","1:30","1:45","2:00","2:15","2:30","2:45","3:00","3:15","3:30","3:45","4:00","4:15","4:30", "4:45"}; // Replace this with getter later
		
		ComboBox dropDown = new ComboBox(FXCollections.observableArrayList(selections));
		dropDown.setPromptText("Pickup Time");
		Button select = new Button("Place Order");
		
		select.setOnAction((closeWindow) -> {
			//Add functionality here
			itemCount = 0;
			cartMenu.setText("Cart ("+ itemCount + ")");
			cartMenu.getItems().clear();
			cartMenu.getItems().add(none);
			reserveStage.close();
		});
		
		HBox orderBox = new HBox(2);
		orderBox.getChildren().addAll(dropDown, select);
		orderBox.setAlignment(Pos.CENTER);
		
		orderBox.setPadding(new Insets(10, 10, 10, 10));
		
		border.setBottom(orderBox);
		scroll.setContent(border);


		reserveStage.setScene(new Scene(scroll));
		reserveStage.show();
		return reserveStage;
	}
	
	public Stage forumStage() {
		forumCount = 2;
		forumStage = new Stage();
		BorderPane border = new BorderPane();
		ScrollPane scroll = new ScrollPane();
		VBox books = new VBox(bookList.size());
		
		forumGrid.setHgap(10);
		forumGrid.setVgap(10);
		forumGrid.setPadding(new Insets(10, 10, 10, 10));
		border.setPadding(new Insets(10, 10, 10, 10));
		
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		forumStage.setHeight(400);
		forumStage.setWidth(600);
		
		Button newPost = new Button("Make Post");

		newPost.setOnAction((makePost) -> {
			makePost(forumCount);
			forumCount ++;
		});
		
		border.setTop(newPost);
		border.setCenter(forumGrid);
		scroll.setContent(border);


		forumStage.setScene(new Scene(scroll));
		forumStage.show();
		return forumStage;
	}
	
	public void makePost(int row) {
		GridPane post = new GridPane();
		BorderPane background = new BorderPane();
		post.setHgap(10);
		post.setVgap(10);
		post.setPadding(new Insets(10, 10, 10, 10));
		background.setPadding(new Insets(10, 10, 10, 10));
		
		HBox title = new HBox(2);
		HBox body = new HBox(1);
		HBox reply = new HBox(2);
		
		title.prefWidthProperty().bind(background.widthProperty());
		body.prefWidthProperty().bind(background.widthProperty());
		post.prefWidthProperty().bind(background.widthProperty());
		reply.prefWidthProperty().bind(background.widthProperty());
		background.prefWidthProperty().bind(stage.widthProperty());
		TextField titleInput = new TextField("Title");
		Button send = new Button("Post");
		
		titleInput.setPrefWidth(450);
		
		title.getChildren().addAll(titleInput, send);
		
		
		TextArea bodyInput = new TextArea("Write your post here...");
		bodyInput.setMinHeight(200);
		bodyInput.setPrefWidth(500);
		
		body.getChildren().addAll(bodyInput);
		
		TextField replyInput = new TextField("Reply");
		Button sendReply = new Button("Send Reply");
		replyInput.setPrefWidth(415);
		

		
		reply.getChildren().addAll(replyInput, sendReply);
		
		post.add(title, 0, commentCount);
		commentCount++;
		post.add(body, 0, commentCount);
		commentCount++;
		
		send.setOnAction((makePost) -> {
			title.setMouseTransparent(true);
			body.setMouseTransparent(true);
			post.add(reply, 0, commentCount);
			commentCount++;
		});
		
		sendReply.setOnAction((postReply) -> {
			makeComment(reply, post);
		});
		
		background.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		background.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		background.setCenter(post);
		
		forumGrid.add(background, 0, row);

		GridPane.setHalignment(post, HPos.CENTER);
	}
	
	void makeComment(HBox reply, GridPane post) {
		HBox temp = new HBox(2);
		TextField tempReplyInput = new TextField("Reply");
		Button tempSendReply = new Button("Send Reply");
		tempReplyInput.setPrefWidth(415);
		temp.getChildren().addAll(tempReplyInput, tempSendReply);
		
		tempSendReply.setOnAction((recourse) -> {
			makeComment(reply, post);
		});
		
		reply.setMouseTransparent(true);
		
		post.add(temp, 0, commentCount);
		commentCount++;
		System.out.println(commentCount);
	}
}

