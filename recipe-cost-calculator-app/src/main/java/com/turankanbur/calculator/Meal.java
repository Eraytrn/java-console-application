/**
 * Package containing classes related to meal management and calculation.
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
 * Interface representing main ingredients in a recipe or dish.
 */

interface IMainMeal{

	/**
	 * Adds a meal to the list of managed meals and saves it to a file.
	 *
	 * @param meal     the meal to add
	 * @param fileName the name of the file to save the meals to
	 */
	void addMeal(Meal meal, String fileName);

	/**
	 * Saves a list of meals to a file.
	 *
	 * @param meals    the list of meals to save
	 * @param fileName the name of the file to save to
	 */
	void saveMealsToFile(List<Meal> meals, String fileName);

    /**
	 * Loads meals from a file and returns them as a list.
	 *
	 * @param fileName the name of the file to load from
	 * @return the list of loaded meals
	 */
	List<Meal> loadMealsFromFile(String fileName);


	/**
	 * Creates a meal from user input and adds it to the list of managed meals.
	 *
	 * @param mealName        the name of the meal
	 * @param selectedRecipes the list of recipes included in the meal
	 * @param recipeManager   the RecipeManagement object to interact with recipe
	 *                        data
	 */
	void createMealFromUserInput(String mealName, List<Recipe> selectedRecipes, RecipeManagement recipeManager) ;

	/**
	 * Lists all managed meals along with their names and total costs.
	 *
	 * @param filename the name of the file to load meals from
	 */
	void listMeals(String filename);

	/**
	 * Retrieves the list of managed meals.
	 *
	 * @return the list of managed meals
	 */
	List<Meal> getMeals() ;
	
}
/**
 * Represents a meal consisting of one or more recipes. Provides functionality
 * to calculate the total cost of the meal.
 */
public class Meal implements Serializable {

	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the meal.
	 */
	private String name;

	/**
	 * The list of recipes included in the meal.
	 */
	private List<Recipe> recipes;

	/**
	 * The total cost of the meal.
	 */
	private double totalCost;

	/**
	 * Constructs a Meal object with the specified name and list of recipes.
	 * Calculates the total cost of the meal.
	 *
	 * @param name    the name of the meal
	 * @param recipes the list of recipes included in the meal
	 */
	public Meal(String name, List<Recipe> recipes) {
		this.name = name;
		this.recipes = recipes;
		calculateTotalCost();
	}

	/**
	 * Retrieves the name of the meal.
	 *
	 * @return the name of the meal
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the list of recipes included in the meal.
	 *
	 * @return the list of recipes
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * Retrieves the total cost of the meal.
	 *
	 * @return the total cost of the meal
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Calculates the total cost of the meal based on the cost of its recipes.
	 */
	private void calculateTotalCost() {
		totalCost = 0.0;
		for (Recipe recipe : recipes) {
			totalCost += recipe.getTotalCost();
		}
	}
}
