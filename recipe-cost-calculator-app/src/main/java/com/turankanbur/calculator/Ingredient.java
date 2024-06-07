/**
 * The package com.turankanbur.calculator contains classes related to a calculator application.
 * It includes classes for managing ingredients, performing calculations, and other utility functions.
 */
package com.turankanbur.calculator;

/**
 * Provides functionality for serializing and deserializing objects.
 * This interface is implemented by classes whose objects need to be persisted.
 */
import java.io.Serializable;

/**
 * Represents an ingredient used in a recipe. This class provides methods to get
 * and set the name and price of the ingredient.
 */

/**
 * Interface representing main ingredients in a recipe or dish.
 */
interface IMainIngredient{
	
	
	/**
	 * Adds an ingredient to the list of managed ingredients.
	 *
	 * @param ingredient the ingredient to add
	 * @param file       the name of the file to save the ingredients to
	 */
	 void addIngredient(Ingredient ingredient, String file);
	
    /**
	 * Saves the managed ingredients to a file.
	 *
	 * @param fileName the name of the file to save to
	 */
	 void saveToFile(String fileName);

	    /**
	 * Loads ingredients from a file and populates the managed ingredients list.
	 *
	 * @param fileName the name of the file to load from
	 */
	    void loadFromFile(String fileName);
	    
		/**
	 * Lists all managed ingredients along with their names and prices.
	 */
	 void listIngredients();

	 /**
	 * Edits the price of a specified ingredient.
	 *
	 * @param ingredientName the name of the ingredient to edit
	 * @param newPrice       the new price of the ingredient
	 */
	 void editIngredientPrice(String ingredientName, int newPrice);

	   /**
	 * Finds an ingredient by its name.
	 *
	 * @param name the name of the ingredient to find
	 * @return the ingredient if found, or null if not found
	 */
	 Ingredient findIngredientByName(String name);
}


/**
 * Represents an ingredient used in a recipe. This class provides methods to get
 * and set the name and price of the ingredient.
 */
public class Ingredient implements Serializable {

	
	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the ingredient.
	 */
	private String name;

	/**
	 * The price of the ingredient.
	 */
	private int price;

	/**
	 * Constructs an Ingredient object with the specified name and price.
	 *
	 * @param name  the name of the ingredient
	 * @param price the price of the ingredient
	 */
	public Ingredient(String name, int price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * Retrieves the name of the ingredient.
	 *
	 * @return the name of the ingredient
	 */
	public String getName() {
		return name;
	}

	
	

	/**
	 * Retrieves the price of the ingredient.
	 *
	 * @return the price of the ingredient
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the price of the ingredient.
	 *
	 * @param price the price to be set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
}
