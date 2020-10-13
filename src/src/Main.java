package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;

public class Main {
	
	public static String[] savedArgs;
	public static void main(String[] args) {
		savedArgs = args;
		Application.launch(View.class);
	}
}
