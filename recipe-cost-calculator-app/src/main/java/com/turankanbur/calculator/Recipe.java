/**
 * Package containing classes related to recipe management and calculation.
 */
package com.turankanbur.calculator;

/**
 * Provides functionality for serialization, allowing objects to be converted into byte streams.
 */
import java.io.Serializable;

/**
 * Provides functionality for storing and manipulating collections of objects.
 */
import java.util.List;



/**
 * Represents a recipe consisting of a name, ingredients, quantity, and total
 * cost. Provides methods to access and modify recipe information.
 */
public class Recipe implements Serializable  {

	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/** The name of the recipe. */
	private String name;

	/** The quantity of the recipe. */
	private int quantity;

	/** The list of ingredients required for the recipe. */
	private List<Ingredient> ingredients;

	/** The total cost of the recipe. */
	private double totalCost;

	/**
	 * Constructs a Recipe object with the specified name, ingredients, and total
	 * cost.
	 *
	 * @param name        the name of the recipe
	 * @param ingredients the list of ingredients required for the recipe
	 * @param totalCost   the total cost of the recipe
	 */
	public Recipe(String name, List<Ingredient> ingredients, double totalCost) {
		this.name = name;
		this.ingredients = ingredients;
		this.totalCost = totalCost;
	}

	/**
	 * Retrieves the name of the recipe.
	 *
	 * @return the name of the recipe
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the list of ingredients required for the recipe.
	 *
	 * @return the list of ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Retrieves the total cost of the recipe.
	 *
	 * @return the total cost of the recipe
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Retrieves the quantity of the recipe.
	 *
	 * @return the quantity of the recipe
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the recipe.
	 *
	 * @param quantity the quantity of the recipe to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Sets the total cost of the recipe.
	 *
	 * @param totalCost the total cost of the recipe to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}