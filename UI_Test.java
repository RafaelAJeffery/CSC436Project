package src;

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

public class UI_Test extends Application {

	private Stage stage;
	private BorderPane pane;
	private GridPane grid = new GridPane();
	private Pane canvas = new Pane();
	private String query;
	private String selection;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		pane = new BorderPane();
		ScrollPane scroll = new ScrollPane();
		
		scroll.setPrefSize(420,  620);
		
		stage.setHeight(400);
		stage.setWidth(600);
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		String selections[] = {"", "Book Title", "ISBN Number", "Author Name", "Format", "Genre", "Publisher", "Avaliablity", "Keywords"};
		
		ComboBox dropDown = new ComboBox(FXCollections.observableArrayList(selections));
		
		dropDown.setOnAction((getQuery) -> {
			selection = (String) dropDown.getValue();
			System.out.println(selection);
		});
		
		Label searchQuery = new Label("Search What?");
		TextField searchTerm = new TextField();
		Button searchButton = new Button("Search!");
		
		searchButton.setOnAction((getQuery) -> {
			query = searchTerm.getText();
			System.out.println(query);
			// Search Here
			// for item found
			makeBookImage(2);

		});
		
		HBox searchBox = new HBox(4);
		
		searchBox.getChildren().addAll(searchQuery, searchTerm, dropDown, searchButton);
		searchBox.setAlignment(Pos.CENTER);
		searchBox.prefWidthProperty().bind(primaryStage.widthProperty());
		
		Label welcome = new Label("Welcome to the [FEDERAL] Library!");
		
		grid.add(welcome, 0, 0);
		grid.add(searchBox, 0, 1);
		
		GridPane.setHalignment(welcome, HPos.CENTER);
		GridPane.setHalignment(searchBox, HPos.CENTER);
		pane.setCenter(grid);
		
		scroll.setContent(grid);

		Scene scene = new Scene(scroll);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void makeBookImage(int row) {
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
		background.prefWidthProperty().bind(stage.widthProperty());
		
		Label isbnTab = new Label("		");
		Label genreTab = new Label("			   ");
		
		Label titleLabel = new Label("Title:");
		Label bookName = new Label("Birds: Are They Real?");
		
		title.getChildren().addAll(titleLabel, bookName);
		BookImage.add(title, 0, 0);
		
		Label isbnLabel = new Label("ISBN:");
		Label isbnName = new Label("1234567891011");
		Label formatLabel = new Label("Format:");
		Label formatName = new Label("Book");
		
		isbnFormat.getChildren().addAll(isbnLabel, isbnName, isbnTab, formatLabel, formatName);
		BookImage.add(isbnFormat, 0, 1);
		
		Label genreLabel = new Label("Genre:");
		Label genreName = new Label("Nonfiction");
		Label pubLabel = new Label("Publisher:");
		Label pubName = new Label("The United States Office of Homeland Security");
		
		genrePub.getChildren().addAll(genreLabel, genreName, genreTab, pubLabel, pubName);
		BookImage.add(genrePub, 0, 2);
		
		Label avaliableLabel = new Label("Avaliable:");
		Label avaliableName = new Label("REDACTED");
		
		Label tab = new Label("					");
		Button reserveButton = new Button("Make Reservation");
		
		reserveButton.setOnAction((reserveItem) -> { 
			if(reserveButton.getText() == "Make Reservation") {
				System.out.println("reserved!");
				reserveButton.setText("In Cart");
			}else {
				System.out.println("unreserved!");
				reserveButton.setText("Make Reservation");
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
