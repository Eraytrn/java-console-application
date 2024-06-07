/**
 * 
@file Menu.java
@brief This file serves as the main application file for the Menu.
@details This file contains the entry point of the application, which is the main method. It initializes the necessary components and executes the Menu.
*/

/**
@package com.turankanbur.calculator
@brief The com.ucoruh.calculator package contains all the classes and files related to the Recipe Cost Calculator Application.
*/
package com.turankanbur.calculator;

/**
 * Imports the FileInputStream class, which is used to read data from a file as a stream of bytes.
 */
import java.io.FileInputStream;

/**
 * Imports the FileOutputStream class, which is used to write data to a file as a stream of bytes.
 */
import java.io.FileOutputStream;

/**
 * Imports the IOException class, which signals that an I/O exception of some sort has occurred.
 */
import java.io.IOException;

/**
 * Imports the ObjectInputStream class, which is used to deserialize objects from an InputStream.
 */
import java.io.ObjectInputStream;

/**
 * Imports the ObjectOutputStream class, which is used to serialize objects to an OutputStream.
 */
import java.io.ObjectOutputStream;

/**
 * Imports the Serializable interface, which is used to indicate that a class can be serialized.
 */
import java.io.Serializable;

/**
 * Imports the ArrayList class, which is used to create resizable arrays.
 */
import java.util.ArrayList;

/**
 * Imports the List interface, which is a collection used to store elements.
 */
import java.util.List;

/**
 * Imports the Scanner class, which is used to get user input from the console.
 */
import java.util.Scanner;



/**
 * Represents a menu interface for the Recipe Cost Calculator application.
 * Provides functionality for user interaction and navigation within the application.
 */
public class Menu implements Serializable {

	/** Unique identifier for ensuring version compatibility of serialized objects. */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * The main entry point of the Recipe Cost Calculator application.
     * Creates an instance of the Menu class, initializes a scanner object for user input,
     * and calls the userMenu method to display the main menu and handle user interactions.
     *
     * @param args the command line arguments (not used in this application)
     */
	public static void main(String[] args) {

		Menu rcp = new Menu();
		Scanner scanner = new Scanner(System.in);
		rcp.userMenu(scanner);
	}

	/**
	 * Clears the console screen.
	 *
	 * This method attempts to clear the console screen by running the cmd command
	 * with the /c option and the cls command on Windows. The standard input and
	 * output streams of the new process are redirected to those of the current
	 * process. If an exception is thrown while running the process, it is caught
	 * and printed to the console.
	 *
	 * Note: This method is specific to the Windows operating system and may not
	 * work on other platforms. To clear the console screen on other platforms, you
	 * may need to use different commands or methods.
	 */
	public void clearScreen()

	{

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

		/**
     * Displays the main menu for user selection.
     *
     * @param scanner the scanner object for user input
     * @return true if exit is requested, false otherwise
     */
	public boolean userMenu(Scanner scanner) {
		clearScreen();

		System.out.println("----------------------");

		System.out.println("User Menu");

		System.out.println("----------------------");

		System.out.println("1-Register");

		System.out.println("2-Login");

		System.out.println("3-Guest Mode");

		System.out.println("4-Exit");

		System.out.println("Enter your choice:");

		String choice = scanner.nextLine();
		boolean exitRequested = false;
		switch (choice) {
		case "1":
		case "R":
		case "r":
			registerUser(scanner);
			break;
		case "2":
		case "L":
		case "l":
			loginUser(scanner);
			break;
		case "3":
	    case "G":
	    case "g":
			guestMode(scanner);
			break;
		case "4":
	   	case "E":
	 	case "e":
			exitRequested = true;
			
			break;
        default: 
	        System.err.println("Please enter a valid key!");
	        userMenu(scanner);
		}
		scanner.close();
		return exitRequested;
	}

	
	 /**
     * Registers a new user.
     *
     * @param scanner the scanner object for user input
     */
	public void registerUser(Scanner scanner) {
		clearScreen();

		System.out.println("----------------------");

		System.out.println("Register User");

		System.out.println("----------------------");

		System.out.println("Enter username: ");
		String username = scanner.nextLine();

		System.out.println("Enter password: ");
		String password = scanner.nextLine();

		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("register.bin"))) {
			outputStream.writeObject(new User(username, password));

			System.out.println("User registered successfully.");
		} catch (IOException e) {
			;
		}

		userMenu(scanner);
		scanner.close();
	}

	
	/**
     * Logs in an existing user.
     *
     * @param scanner the scanner object for user input
     */
	public void loginUser(Scanner scanner) {
		clearScreen();

		System.out.println("--------------------");

		System.out.println("Login");

		System.out.println("--------------------");

		System.out.print("Enter username: ");
		String username = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("register.bin"))) {
			User user;
			while ((user = (User) inputStream.readObject()) != null) {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

					clearScreen();
					printMenu(scanner);
					return;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error");
		}

		System.out.println("Incorrect username or password. Please try again.");
		userMenu(scanner);
		scanner.close();

	}

	 /**
     * Enters guest mode.
     *
     * @param scanner the scanner object for user input
     */
	public void guestMode(Scanner scanner) {
		clearScreen();

		System.out.println("--------------------");

		System.out.println("Guest Mode");

		System.out.println("--------------------");

		// Clear the screen
		clearScreen();

		System.out.println("Welcome To Recipe Cost Calculator App(Guest Mode)");

		System.out.println("1-Ingredient Management");

		System.out.println("2-Recipe Costing");

		System.out.println("3-Plan Meal");

		System.out.println("4-About");

		System.out.println("5-Exit");

		selectMenuForGuest(scanner);
	}

	 /**
     * Displays the main menu options.
     *
     * @param Scanner the scanner object for user input
     */
	public void printMenu(Scanner Scanner) {

		// Clear the screen
		clearScreen();

		// Print the welcome message and the menu options

		System.out.println("Welcome To Recipe Cost Calculator App");

		System.out.println("1-Ingredient Management");

		System.out.println("2-Recipe Costing");

		System.out.println("3-Plan Meals");

		System.out.println("4-About");

		System.out.println("5-Exit");
		selectMenu(Scanner);

	}

	
	 /**
     * Displays the main menu for guest users.
     *
     * @param scanner the scanner object for user input
     */
	public void printMenuForGuest(Scanner scanner) {
		

		// Clear the screen
		clearScreen();

		// Print the welcome message and the menu options

		System.out.println("Welcome To Recipe Cost Calculator App");

		System.out.println("1-Ingredient Management");

		System.out.println("2-Recipe Costing");

		System.out.println("3-Plan Meals");

		System.out.println("4-About");

		System.out.println("5-Exit");

		selectMenuForGuest(scanner);
	}

	/**
     * Selects menu options based on user input.
     *
     * @param scanner the scanner object for user input
     */
	public void selectMenu(Scanner scanner) {
		try {
			System.out.println("Press First Character for further Operations");
			String choice = scanner.next();

			switch (choice) {
			case "1":
			case "I":
			case "i":
				IngredientManagement(scanner);
				break;
			case "2":
			case "R":
			case "r":
				RecipeCosting(scanner);
				break;
			case "3":
			case "P":
			case "p":
				PlanMeal(scanner);
				break;
			case "4":
			case "A":
			case "a":

				About(scanner);
				break;
			case "5":
			case "E":
			case "e":
				break;
			default: 
		        System.err.println("Please enter a valid key!");
		        printMenu(scanner);
			}
		} finally {
			scanner.close(); 
		}
	}

	
	/**
     * Selects menu options for guest users based on user input.
     *
     * @param scanner the scanner object for user input
     */
	public void selectMenuForGuest(Scanner scanner) {

		try {
			System.out.println("Press First Character for further Operations");
			String choice = scanner.next();

			switch (choice) {
			case "1":
			case "I":
			case "i":
				IngredientManagementForGuest(scanner);
				break;
			case "2":
			case "R":
			case "r":
				RecipeCostingForGuest(scanner);
				break;
			case "3":
			case "P":
		    case "p":
				PlanMealForGuest(scanner);
				break;

			case "4":
			case "A":
			case "a":
				clearScreen();
				AboutForGuest(scanner);
				break;

			case "5":
			case "E":
			case "e":
				break;
			default: 
		        System.err.println("Please enter a valid key!");
		        printMenuForGuest(scanner);

			}
		} finally {
			scanner.close();
		}

	}

	
	/**
     * Manages ingredient operations.
     *
     * @param scanner the scanner object for user input
     */
	public void IngredientManagement(Scanner scanner) {
		clearScreen();

		System.out.println("----------------");
		System.out.println("Ingredient Management Menu");
		System.out.println("----------------");
		System.out.println("1-Add Ingredient");
		System.out.println("2-View Ingredient");
		System.out.println("3-Edit Ingredient");
		System.out.println("4-Remove Ingredient");
		System.out.println("5-Main Menu");
		try {
		System.out.println("Press First Character for further Operation ");

		String choice = scanner.next();

		switch (choice) {
		case "1":
		case "A":
		case "a":
			clearScreen();
			addIngredient(scanner);
			break;
		case "2":
		case "V":
		case "v":
			clearScreen();
			viewIngredient(scanner);
			break;
		case "3":
		case "E":
		case "e":
			editIngredient(scanner);
			break;
		case "4":
		case "R":
		case "r":
			removeIngredient(scanner);
			break;
		case "5":
		case "M":
		case "m":
			clearScreen();
			printMenu(scanner);
			break;
			
		default: 
	        System.err.println("Please enter a valid key!");
	        IngredientManagement(scanner);

		   }
		} finally {
			scanner.close();
		}
	}

	
	/**
     * Manages ingredient operations for guest users.
     *
     * @param scanner the scanner object for user input
     */
	public void IngredientManagementForGuest(Scanner scanner) {

		clearScreen();

		System.out.println("----------------");

		System.out.println("Ingredient Management Menu");

		System.out.println("----------------");

		System.out.println("1-View Ingredient");

		System.out.println("2-Main Menu");

		System.out.println("Press First Character for further Operation ");

		String choice = scanner.next();

		switch (choice) {

		case "1":
		case "V":
		case "v":
			clearScreen();
			viewIngredientForGuest(scanner);
			break;

		case "2":
		case "M":
		case "m":
			clearScreen();
			printMenuForGuest(scanner);
			break;

		default: 
	        System.err.println("Please enter a valid key!");
	        IngredientManagementForGuest(scanner);
		}

		scanner.close();
	}

	
	/**
     * Manages recipe costing operations.
     *
     * @param scanner the scanner object for user input
     */
	public void RecipeCosting(Scanner scanner) {

		clearScreen();

		System.out.println("----------------");

		System.out.println("Recipe Costing Menu");

		System.out.println("----------------");

		System.out.println("1-Create Recipe");

		System.out.println("2-View Recipes");

		System.out.println("3-Edit Recipes");

		System.out.println("4-Main Menu");

		System.out.println("Press First Character for further Operation ");

		String choice = scanner.next();

		switch (choice) {

		case "1":
		case "C":
		case "c":
			CreateRecipe(scanner);
			break;

		case "2":
		case "V":
		case "v":
			clearScreen();
			RecipeManagement.loadFromFile("recipes.bin");
			viewRecipe(scanner);
			break;

		case "3":
		case "E":
		case "e":
			editIngredient(scanner);
			break;

		case "4":
		case "M":
		case "m":
			clearScreen();
			printMenu(scanner);
			break;

		default: 
	        System.err.println("Please enter a valid key!");
	        RecipeCosting(scanner);
		}
		scanner.close();
	}

	
	/**
     * Manages recipe costing operations for guest users.
     *
     * @param scanner the scanner object for user input
     */
	public void RecipeCostingForGuest(Scanner scanner) {

		Menu a = new Menu();
		a.clearScreen();

		System.out.println("----------------");

		System.out.println("Recipe Costing Menu");

		System.out.println("----------------");

		System.out.println("1-View Recipe");

		System.out.println("2-Main Menu");

		System.out.println("Press First Character for further Operation ");

		String choice = scanner.next();

		switch (choice) {

		case "1":
		case "V":
		case "v":
			viewRecipeForGuest(scanner);
			break;

		case "2":
		case "M":
		case "m":
			clearScreen();
			printMenuForGuest(scanner);
			break;

		default: 
	        System.err.println("Please enter a valid key!");
	        RecipeCostingForGuest(scanner);
		}
		scanner.close();

	}

	
	/**
     * Plans meal operations.
     *
     * @param scanner the scanner object for user input
     */
	public void PlanMeal(Scanner scanner) {

		clearScreen();

		System.out.println("----------------");

		System.out.println("Plan Meal Menu");

		System.out.println("----------------");

		System.out.println("1-Create Meal");

		System.out.println("2-View Meals");

		System.out.println("3-Main Menu");

	
		System.out.println("Press First Character for further Operation ");

		String choice = scanner.next();

		switch (choice) {

		case "1":
		case "C":
		case "c":
			clearScreen();
			CreateMeal(scanner);
			break;

		case "2":
     	case "V":
		case "v":
			clearScreen();
			viewMeal(scanner);
			break;

		case "3":
		case "M":
		case "m":
			clearScreen();
			printMenu(scanner);
			break;

		default: 
	        System.err.println("Please enter a valid key!");
	        PlanMeal(scanner);
		}
		scanner.close();
	}

	
	 /**
     * Plans meal operations for guest users.
     *
     * @param scanner the scanner object for user input
     */
	public void PlanMealForGuest(Scanner scanner) {

		clearScreen();

		System.out.println("----------------");

		System.out.println("Plan Meal Menu");

		System.out.println("----------------");

		System.out.println("1-View Meals");

		System.out.println("2-Main Menu");

		System.out.print("Press First Character for further Operation ");

		String choice = scanner.next();

		switch (choice) {

		case "1":
		case "V":
		case "v":
			clearScreen();
			viewMealForGuest(scanner);
			break;

		case "2":
		case "M":
		case "m":
			clearScreen();
			printMenuForGuest(scanner);
			break;

		default: 
	        System.err.println("Please enter a valid key!");
	        PlanMealForGuest(scanner);
		}

		scanner.close();
	}

	/**
	 * Adds a new ingredient to the ingredient list. The user is prompted to enter the name and price of the ingredient.
	 * The ingredient is then saved to the file "ingredients.bin".
	 * After adding the ingredient, the user is redirected to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void addIngredient(Scanner scanner) {

		clearScreen();

		System.out.println("______________");

		System.out.println("Ingredients-Add Menu");

		System.out.println("______________");
		scanner.nextLine();
		System.out.print("Ingredients Name:\n");
		String name = scanner.nextLine();

		System.out.println("Ingredients Price:");
		int price = scanner.nextInt();

		Ingredient ingredient = new Ingredient(name, price);

		IngredientManagement management1 = new IngredientManagement("null", 0);
		management1.loadFromFile("ingredients.bin");

		management1.addIngredient(ingredient, "ingredients.bin");

		management1.saveToFile("ingredients.bin");

		printMenu(scanner);

		scanner.close();
	}

	
	/**
	 * Displays the list of available ingredients to the user. The user can press 'c' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void viewIngredient(Scanner scanner) {

		clearScreen();
		// Ingredient recipeCalculator = new Ingredient(null, 0);

		IngredientManagement management = new IngredientManagement("null", 0);

		management.loadFromFile("ingredients.bin");
		management.listIngredients();

		// Calculate and display total stock and price

		System.out.println("Press 'c' to return to the main menu.");

		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("c")) {
			printMenu(scanner);

		}
		scanner.close();
	}

	
	/**
	 * Displays the list of available ingredients to the guest user. The guest user can press 'c' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void viewIngredientForGuest(Scanner scanner) {

		clearScreen();
		// Ingredient recipeCalculator = new Ingredient(null, 0);

		IngredientManagement management = new IngredientManagement("null", 0);

		management.loadFromFile("ingredients.bin");
		management.listIngredients();

		// Calculate and display total stock and price

		System.out.println("Press 'c' to return to the main menu.");

		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("c")) {
			printMenuForGuest(scanner);
		} else {
			System.out.println("Invalid input. Returning to the main menu...");
			printMenuForGuest(scanner);
		}
		scanner.close();
	}

	
	/**
	 * Allows the user to edit the price of an existing ingredient. The user is prompted to enter the name of the ingredient
	 * to edit, as well as the new price. After editing the ingredient, the user can choose to save or cancel the operation
	 * and return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */	
	public void editIngredient(Scanner scanner) {

		clearScreen();
		// Ingredient rps = new Ingredient(null, 0);
		// Ingredient listing = new Ingredient(null, 0);

		System.out.println("______________");

		System.out.println("Ingredients-Edit Menu");

		System.out.println("______________");
		IngredientManagement management = new IngredientManagement("null", 0);

		management.loadFromFile("ingredients.bin");

		management.listIngredients();
		scanner.nextLine();

		System.out.println("Enter Ingredient Name to Edit:");

		String editName = scanner.next();

		// IngredientManagement management2 = new IngredientManagement(editName, 0);

		// Display existing ingredients for user to choose from
		IngredientManagement management1 = new IngredientManagement("null", 0);
		management1.loadFromFile("ingredients.bin");

		// Find the ingredient by name

		Ingredient ingredientToEdit = management1.findIngredientByName(editName);

		if (ingredientToEdit != null) {
			// Prompt user for new values

			System.out.println("New Ingredients Price:");

			int newPrice = scanner.nextInt();

			// Update the ingredient

			ingredientToEdit.setPrice(newPrice);

			// Save to file
			management1.saveToFile("ingredients.bin");
			// Display updated list

		} else {
			System.out.println("Ingredient not found. Please enter a valid name.");
		}
		clearScreen();

		System.out.println("Save");

		System.out.println("Cancel");

		System.out.println("Press First character for the operation : ");

		String a1 = scanner.next();

		switch (a1) {
		case "s":
		case "S":
			printMenu(scanner);
			break;

		case "c":
		case "C":
			printMenu(scanner);
			break;
		}

		scanner.close();
	}

	
	
	/**
	 * Allows the user to remove an ingredient from the ingredient list. The user is prompted to enter the name of the
	 * ingredient to remove. After removing the ingredient, the user can choose to save or cancel the operation and return
	 * to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void removeIngredient(Scanner scanner) {

		clearScreen();

		IngredientManagement listing = new IngredientManagement(null, 0);

		System.out.println("______________");

		System.out.println("Ingredients-Remove Menu");

		System.out.println("______________");

		listing.loadFromFile("ingredients.bin");
		listing.listIngredients();
		scanner.nextLine();

		System.out.println("Enter Ingredient Name to Remove:");

		String removeName = scanner.next();

		// Load ingredients from file

		IngredientManagement ingredientManagement = new com.turankanbur.calculator.IngredientManagement(removeName, 0);

		// Display existing ingredients for user to choose from
		ingredientManagement.loadFromFile("ingredients.bin");

		// Find the ingredient by name
		Ingredient ingredientToRemove = ingredientManagement.findIngredientByName(removeName);

		if (ingredientToRemove != null) {
			// Remove the ingredient
			ingredientManagement.getIngredients().remove(ingredientToRemove);

			// Save to file
			ingredientManagement.saveToFile("ingredients.bin");
			System.out.println("Ingredient removed successfully.");
		} else {
			System.out.println("Ingredient not found. Please enter a valid name.");
		}

		clearScreen();

		System.out.println("Save");

		System.out.println("Cancel");

		System.out.println("Press First character for the operation : ");

		String a1 = scanner.next();

		switch (a1) {
		case "s":
		case "S":
			printMenu(scanner);
			break;
		case "c":
		case "C":
			printMenu(scanner);
			break;
		}

		scanner.close();
	}

	
	/**
	 * Allows the user to create a new recipe by selecting ingredients from the list of available ingredients. The user
	 * selects ingredients and specifies their quantities, and then provides a name for the recipe. The created recipe is
	 * then saved to the file "recipes.bin".
	 * After creating the recipe, the user is redirected to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void CreateRecipe(Scanner scanner) {
		scanner.nextLine();
		IngredientManagement management = new IngredientManagement("null", 0);

		management.loadFromFile("ingredients.bin");

		// Available ingredients from management
		List<Ingredient> availableIngredients = management.getIngredients();

		// Display available ingredients to the user
		System.out.println("Available Ingredients:");
		for (int i = 0; i < availableIngredients.size(); i++) {
			Ingredient ingredient = availableIngredients.get(i);
			System.out.println((i + 1) + ". " + ingredient.getName() + " - Price: " + ingredient.getPrice()+"$");
		}

		List<Ingredient> selectedIngredients = new ArrayList<>();
		double totalCost = 0.0;
		System.out.println("Enter ingredient numbers (press 'q' to finish):");
		while (true) {
			System.out.print("Ingredient number: ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("q")) {
				break;
			}
			try {
				int index = Integer.parseInt(input) - 1;
				if (index < 0 || index >= availableIngredients.size()) {
					System.out.println("Invalid ingredient number. Please try again.");
					continue;
				}
				Ingredient ingredient = availableIngredients.get(index);
				System.out.print("Enter quantity for " + ingredient.getName() + ": ");
				int quantity = Integer.parseInt(scanner.nextLine()); // Get quantity input from user

				// Calculate and accumulate cost for selected ingredients
				double ingredientCost = ingredient.getPrice() * quantity;
				totalCost += ingredientCost;

				selectedIngredients.add(ingredient); // Add the selected ingredient to the list
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
			}

		}
		System.out.print("Enter recipe name: ");
		String recipeName = scanner.nextLine();
		RecipeManagement recipes = new RecipeManagement();
		RecipeManagement.createRecipeFromUserInput(availableIngredients, recipes, recipeName, totalCost,
				selectedIngredients);

		printMenu(scanner);

		scanner.close();

	}

	
	/**
	 * Displays the list of available recipes to the user. The user can press 'c' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void viewRecipe(Scanner scanner) {

		RecipeManagement recipeManager = new RecipeManagement();

		recipeManager.listRecipes("recipes.bin");

		System.out.println("Press 'c' to return to the main menu.");

		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("c")) {
			printMenu(scanner);
		} else {
			System.out.println("Invalid input. Returning to the main menu...");
			printMenu(scanner);
		}
		scanner.close();
	}

	
	/**
	 * Displays the list of available recipes to the guest user. The guest user can press 'c' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void viewRecipeForGuest(Scanner scanner) {
		RecipeManagement recipeManager = new RecipeManagement();

		recipeManager.listRecipes("recipes.bin");

		System.out.println("Press 'c' to return to the main menu.");

		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("c")) {
			printMenuForGuest(scanner);
		} else {
			System.out.println("Invalid input. Returning to the main menu...");
			printMenuForGuest(scanner);
		}
		scanner.close();
	}

	
	/**
	 * Allows the user to create a new meal by selecting recipes from the list of available recipes. The user selects
	 * recipes to include in the meal, provides a name for the meal, and then saves the created meal to the file "meals.bin".
	 * After creating the meal, the user is redirected to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void CreateMeal(Scanner scanner) {
		scanner.nextLine();

		RecipeManagement recipeManager = new RecipeManagement();
		RecipeManagement.loadFromFile("recipes.bin");
		recipeManager.listRecipes("recipes.bin");
		List<Recipe> availableRecipes = recipeManager.getRecipes();

		System.out.println("Available Recipes:");
		for (int i = 0; i < availableRecipes.size(); i++) {
			Recipe recipe = availableRecipes.get(i);
			System.out.println((i + 1) + ". " + recipe.getName());
		}
		List<Recipe> selectedRecipes = new ArrayList<>();
		while (true) {
			System.out.print("Select a recipe number to include in the meal (press 'q' to finish): ");
			String userInput = scanner.nextLine();

			if (userInput.equalsIgnoreCase("q")) {
				break;
			}

			try {
				int selectedRecipeIndex = Integer.parseInt(userInput) - 1;
				if (selectedRecipeIndex < 0 || selectedRecipeIndex >= availableRecipes.size()) {
					System.out.println("Invalid recipe number selected. Please try again.");
					continue;
				}

				Recipe selectedRecipe = availableRecipes.get(selectedRecipeIndex);
				selectedRecipes.add(selectedRecipe);

			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid recipe number or 'q' to finish.");
			}
		}

		System.out.print("Enter meal name: ");
		String mealName = scanner.nextLine();
		MealManagement meals = new MealManagement();
		meals.createMealFromUserInput(mealName, selectedRecipes, recipeManager);
		System.out.println("Press 'c' to return to the main menu.");

		printMenu(scanner);

		scanner.close();

	}

	
	/**
	 * Displays the list of available meals to the user. The user can press 'c' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void viewMeal(Scanner scanner) {

		MealManagement mealmanagement = new MealManagement();

		mealmanagement.listMeals("meals.bin");

		System.out.println("Press 'c' to return to the main menu.");

		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("c")) {
			printMenu(scanner);
		} else {
			System.out.println("Invalid input. Returning to the main menu...");
			selectMenu(scanner);
		}
		scanner.close();
	}

	
	/**
	 * Displays the list of available meals to the guest user. The guest user can press 'c' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void viewMealForGuest(Scanner scanner) {

		MealManagement mealmanagement = new MealManagement();

		mealmanagement.listMeals("meals.bin");

		System.out.println("Press 'c' to return to the main menu.");

		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("c")) {
			printMenuForGuest(scanner);
		} else {
			System.out.println("Invalid input. Returning to the main menu...");
			selectMenu(scanner);
		}
		scanner.close();

	}

	
	/**
	 * Displays information about the Recipe Cost Calculator application. The user can press '1' to return to the main menu.
	 * 
	 * @param scanner The Scanner object used to get user input.
	 */
	public void About(Scanner scanner) {


		clearScreen();

		System.out.println("***** RECIPE COST CALCULATOR APP *****");

		System.out.println("=> This Project Is About Recipe Cost ");

		System.out.println("=> In This Project We Can Add , View, Edit And Remove Ingredient");

		System.out.println("=> In This Project We Can Create and View Recipes");

		System.out.println("=> In This Project We Can Create and View Meals");

		System.out.println("<<<<-Press 1 for Main Menu->>>>");

		String choice = scanner.next();
		switch (choice) {

		case "1":
			clearScreen();
			printMenu(scanner);
			
			break;
		default: 
	        System.err.println("Please enter a valid key!");
	        About(scanner);
		}
		scanner.close();
	}

	/**
	 * Displays information about the Recipe Cost Calculator App for guest users.
	 * This method clears the screen and prints information about the project,
	 * including its purpose and functionalities(Just Included View Option). 
	 * After displaying the information,
	 * it prompts the user to press 1 to return to the main menu.
	 * 
	 * @param scanner Scanner object to read user input.
	 */
	public void AboutForGuest(Scanner scanner) {
		
		clearScreen();

		System.out.println("***** RECIPE COST CALCULATOR APP *****");

		System.out.println("=> This Project Is About Recipe Cost ");

		System.out.println("=> In This Project We Can Add , View, Edit And Remove Ingredient");

		System.out.println("=> In This Project We Can Create and View Recipes");

		System.out.println("=> In This Project We Can Create and View Meals");

		System.out.println("<<<<-Press 1 for Main Menu->>>>");

		String choice = scanner.next();
		switch (choice) {

		case "1":
			clearScreen();
			printMenuForGuest(scanner);
			
			break;
		default: 
	        System.err.println("Please enter a valid key!");
	        AboutForGuest(scanner);
		}
		scanner.close();
	}
}