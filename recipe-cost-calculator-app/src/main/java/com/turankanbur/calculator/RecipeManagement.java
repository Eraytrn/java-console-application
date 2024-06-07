/**
 * Package containing classes related to meal and recipe management.
 */
package com.turankanbur.calculator;

/**
 * Provides functionality for handling end-of-file (EOF) conditions.
 */
import java.io.EOFException;

/**
 * Provides functionality for reading bytes from a file input stream.
 */
import java.io.FileInputStream;

/**
 * Provides functionality for writing bytes to a file output stream.
 */
import java.io.FileOutputStream;

/**
 * Provides functionality for handling input/output (I/O) operations.
 */
import java.io.IOException;

/**
 * Provides functionality for reading objects from an input stream.
 */
import java.io.ObjectInputStream;

/**
 * Provides functionality for writing objects to an output stream.
 */
import java.io.ObjectOutputStream;

/**
 * Allows classes to implement serialization, allowing their objects to be converted into byte streams.
 */
import java.io.Serializable;

/**
 * Provides an implementation of the List interface backed by an array.
 */
import java.util.ArrayList;

/**
 * Provides functionality for storing and manipulating collections of objects.
 */
import java.util.List;

/**
 * Manages recipes, including adding, saving, loading, and listing recipes. This
 * class provides functionality to create recipes from user input and interact
 * with recipe data stored in files.
 */
public class RecipeManagement  implements Serializable {

	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/** List of recipes managed by this RecipeManagement instance. */
	private List<Recipe> recipes;

	/** Constructs a RecipeManagement object with an empty list of recipes. */
	public RecipeManagement() {
		
		this.recipes = new ArrayList<>();
	}

	/**
	 * Adds a recipe to the list of managed recipes and saves it to a file.
	 *
	 * @param recipe   the recipe to add
	 * @param fileName the name of the file to save the recipes to
	 */
	
	
	public void addRecipe(Recipe recipe, String fileName) {
		// Load existing recipes from file
		List<Recipe> existingRecipes = loadFromFile(fileName);

		// Add the new recipe to the existing list
		existingRecipes.add(recipe);

		// Save all recipes to file
		saveToFile(existingRecipes, fileName);
	}

	/**
	 * Retrieves the list of managed recipes.
	 *
	 * @return the list of managed recipes
	 */
	public List<Recipe> getRecipes() {
		return this.recipes;
	}

	/**
	 * Saves a list of recipes to a file.
	 *
	 * @param recipes  the list of recipes to save
	 * @param fileName the name of the file to save to
	 */
	public static void saveToFile(List<Recipe> recipes, String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			for (Recipe r : recipes) {
				oos.writeObject(r);
				oos.writeUTF("#END_RECIPE#");
			}

		} catch (IOException e) {
			System.err.println("Error saving to file: " + e.getMessage());
		}
	}

	/**
	 * Loads recipes from a file and returns them as a list.
	 *
	 * @param fileName the name of the file to load from
	 * @return the list of loaded recipes
	 */
	public static List<Recipe> loadFromFile(String fileName) {
		List<Recipe> loadedRecipes = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {

			while (true) {
				try {
					Recipe loadedRecipe = (Recipe) ois.readObject();
					loadedRecipes.add(loadedRecipe);

					ois.readUTF();

				} catch (EOFException e) {
					break; // Reached end of file
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading from file: " + e.getMessage());
		}
		return loadedRecipes;
	}

	/**
	 * Creates a recipe from user input and adds it to the list of managed recipes.
	 *
	 * @param availableIngredients the list of available ingredients
	 * @param recipeManager        the RecipeManagement object to interact with
	 *                             recipe data
	 * @param recipeName           the name of the recipe
	 * @param totalCost            the total cost of the recipe
	 * @param selectedIngredients  the list of selected ingredients for the recipe
	 */
	public static void createRecipeFromUserInput(List<Ingredient> availableIngredients, RecipeManagement recipeManager,
			String recipeName, double totalCost, List<Ingredient> selectedIngredients) {

		// Print recipe details
		System.out.println("\nRecipe Name: " + recipeName);
		System.out.println("Total Cost: " + totalCost + "$");
		System.out.println("Ingredients:");
		for (Ingredient ingredient : selectedIngredients) {
			System.out.println("- " + ingredient.getName() + ": " + ingredient.getPrice() + "$" + " per unit");
		}

		// Create the recipe
		Recipe recipe = new Recipe(recipeName, selectedIngredients, totalCost);

		// Add the recipe to the existing recipe manager and save to file
		recipeManager.addRecipe(recipe, "recipes.bin");

	}

	/**
	 * Lists all managed recipes along with their names, total costs, and
	 * ingredients.
	 *
	 * @param filename the name of the file to load recipes from
	 */
	
	public void listRecipes(String filename) {
		// Load recipes from file
		recipes = loadFromFile(filename);

		if (recipes.isEmpty()) {
			System.out.println("No recipes found to list.");
			return;
		}

		for (int i = 0; i < recipes.size(); i++) {
			Recipe recipe = recipes.get(i);
			System.out.println("\nRecipe " + (i + 1) + ":");
			System.out.println("    Name: " + recipe.getName());
			System.out.println("    Total Cost: " + recipe.getTotalCost()+ "$");
			System.out.println("    Ingredients:");
			List<Ingredient> ingredients = recipe.getIngredients();
			for (int j = 0; j < ingredients.size(); j++) {
				Ingredient ingredient = ingredients.get(j);
				System.out.println("        - " + ingredient.getName() + ": " + ingredient.getPrice ()+ "$" + " per unit");
			}
		}
		System.out.println("-------------------------\n");
	}

	
	/**
	 * Sets the list of recipes managed by this RecipeManagement instance.
	 *
	 * @param recipes2 the list of recipes to set
	 */
	public void setRecipes(List<Recipe> recipes2) {
		this.recipes = recipes2;

	}

}