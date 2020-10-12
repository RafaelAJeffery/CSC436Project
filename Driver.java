import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
		/*
		 * TODO: be able to read in a csv file and create book objects out of
		 * that file
		 */
		Model model= new Model();
		try {
			File database= new File(args[0]);
			Scanner scan= new Scanner(database);
			scan.nextLine();// skips the first line of the file
			String[] dataSplit; // will be filled when iterating through the file
			String series;
			int ISBN;
			String publicationName;
			String publisher;
			
				while(scan.hasNextLine()) {
					dataSplit= scan.nextLine().split(",");// splitting entry sections by comma
					
					//fields to make a Book object
					series= dataSplit[0];
					ISBN= Integer.valueOf(dataSplit[1]);
					publicationName= dataSplit[2];
					publisher= dataSplit[3];
					
					Book toAdd= new Book(series, ISBN, publicationName,publisher);
					
				}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
