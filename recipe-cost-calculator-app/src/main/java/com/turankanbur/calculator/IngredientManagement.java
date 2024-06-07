/**
 * The package com.turankanbur.calculator contains classes related to a calculator application.
 * It includes classes for managing ingredients, performing calculations, and other utility functions.
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
 * Manages the ingredients used in recipes. This class provides methods for
 * adding, listing, editing, and loading/saving ingredients to/from a file.
 */
 

public class IngredientManagement extends  Ingredient implements Serializable, IMainIngredient {

	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Manages the ingredients used in recipes. This class provides methods for
	 * adding, listing, editing, and loading/saving ingredients to/from a file.
	 */
	public List<Ingredient> ingredients;

	/**
	 * Constructs an IngredientManagement object with the specified name and price
	 * for the base ingredient. Initializes the list of ingredients.
	 *
	 * @param name  the name of the base ingredient
	 * @param price the price of the base ingredient
	 */
	

	public IngredientManagement(String name, int price) {
		super(name, price);
		this.ingredients = new ArrayList<>();
	}

	/**
	 * Adds an ingredient to the list of managed ingredients.
	 *
	 * @param ingredient the ingredient to add
	 * @param file       the name of the file to save the ingredients to
	 */
	@Override
	public void addIngredient(Ingredient ingredient, String file) {

		ingredients.add(ingredient);

	}

	/**
	 * Retrieves the list of managed ingredients.
	 *
	 * @return the list of managed ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Sets the list of managed ingredients.
	 *
	 * @param ingredients the list of ingredients to set
	 */
	public void setIngredient(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Saves the managed ingredients to a file.
	 *
	 * @param fileName the name of the file to save to
	 */
	@Override
	public void saveToFile(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			for (Ingredient ingredient : ingredients) {
				oos.writeObject(ingredient);
				oos.writeUTF("#END_INGREDIENT#"); // Add a marker between ingredients
			}

			System.err.println("Ingredient has been succesfully saved to file");

		} catch (IOException e) {
			System.err.println("Error saving to file: " + e.getMessage());
		}
	}

	/**
	 * Loads ingredients from a file and populates the managed ingredients list.
	 *
	 * @param fileName the name of the file to load from
	 */
	@Override
	public void loadFromFile(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {

			while (true) {
				try {
					Ingredient loadedIngredient = (Ingredient) ois.readObject();
					ingredients.add(loadedIngredient);

					// Read and discard the marker
					ois.readUTF();
				} catch (EOFException e) {
					break; // Reached end of file
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading from file: " + e.getMessage());
		}
	}

	/**
	 * Lists all managed ingredients along with their names and prices.
	 */
	@Override
	public void listIngredients() {
		if (ingredients.isEmpty()) {
			System.out.println("No ingredients found to list.");
			return;
		}

		for (int i = 0; i < ingredients.size(); i++) {
			Ingredient ingredient = ingredients.get(i);
			System.out.println("\n" + "Ingredient " + (i + 1) + ":");
			System.out.println("    Name: " + ingredient.getName());
			System.out.println("    Price: " + ingredient.getPrice()+ "$");
			System.out.println("-------------------------\n");
		}
	}

	/**
	 * Edits the price of a specified ingredient.
	 *
	 * @param ingredientName the name of the ingredient to edit
	 * @param newPrice       the new price of the ingredient
	 */
	@Override
	public void editIngredientPrice(String ingredientName, int newPrice) {
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getName().equals(ingredientName)) {
				ingredient.setPrice(newPrice);
				System.out.println("The price of" + ingredientName + " ingredient has been succesfully updated.");
				// saveToFile("example.bin");
				return;
			}
		}
		System.out.println("The price of specified ingredient could not found.");
	}

	/**
	 * Finds an ingredient by its name.
	 *
	 * @param name the name of the ingredient to find
	 * @return the ingredient if found, or null if not found
	 */
	@Override
	public Ingredient findIngredientByName(String name) {
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getName().equalsIgnoreCase(name)) {
				return ingredient;
			}
		}
		return null;
	}

}