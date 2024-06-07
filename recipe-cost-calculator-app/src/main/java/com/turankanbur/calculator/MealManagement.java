/**
 * Package containing classes related to meal and recipe management.
 */
package com.turankanbur.calculator;

/**
 * Provides functionality for handling input/output (I/O) operations.
 */
import java.io.*;

/**
 * Provides the framework for writing programs that manipulate collections of objects.
 */
import java.util.*;

/**
 * Manages meals, including adding, saving, loading, and listing meals. This
 * class provides functionality to create meals from user input and interact
 * with meal data stored in files.
 */
public class MealManagement implements Serializable ,IMainMeal {

	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/** List of meals managed by this MealManagement instance. */
	public List<Meal> meals;

	/** Constructs a MealManagement object with an empty list of meals. */
	public MealManagement() {
		this.meals = new ArrayList<>();
	}

	/**
	 * Adds a meal to the list of managed meals and saves it to a file.
	 *
	 * @param meal     the meal to add
	 * @param fileName the name of the file to save the meals to
	 */
	@Override
	public void addMeal(Meal meal, String fileName) {
		// Load existing meals from file
		List<Meal> existingMeals = loadMealsFromFile(fileName);

		// Add the new meal to the existing list
		existingMeals.add(meal);

		// Save all meals to file
		saveMealsToFile(existingMeals, fileName);
	}

	/**
	 * Retrieves the list of managed meals.
	 *
	 * @return the list of managed meals
	 */
	public List<Meal> getMeals() {
		return this.meals;
	}

	/**
	 * Saves a list of meals to a file.
	 *
	 * @param meals    the list of meals to save
	 * @param fileName the name of the file to save to
	 */
	@Override
	public void saveMealsToFile(List<Meal> meals, String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			for (Meal meal : meals) {
				oos.writeObject(meal);
				oos.writeUTF("#END_MEAL#");
			}

		} catch (IOException e) {
			System.err.println("Error saving meals to file: " + e.getMessage());
		}
	}

	/**
	 * Loads meals from a file and returns them as a list.
	 *
	 * @param fileName the name of the file to load from
	 * @return the list of loaded meals
	 */
	@Override
	public List<Meal> loadMealsFromFile(String fileName) {
		List<Meal> loadedMeals = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {

			while (true) {
				try {
					Meal loadedMeal = (Meal) ois.readObject();
					loadedMeals.add(loadedMeal);

					ois.readUTF();

				} catch (EOFException e) {
					break; // Reached end of file
				}
			}

		} catch (IOException | ClassNotFoundException e) {

		}
		return loadedMeals;
	}

	/**
	 * Creates a meal from user input and adds it to the list of managed meals.
	 *
	 * @param mealName        the name of the meal
	 * @param selectedRecipes the list of recipes included in the meal
	 * @param recipeManager   the RecipeManagement object to interact with recipe
	 *                        data
	 */
	@Override
	public void createMealFromUserInput(String mealName, List<Recipe> selectedRecipes, RecipeManagement recipeManager) {

		Meal meal = new Meal(mealName, selectedRecipes);
		addMeal(meal, "meals.bin");
		System.out.println("Meal Name: " + mealName);
		System.out.println("Total Cost: " + meal.getTotalCost()+"$");
		System.out.println("Selected Recipes:");
		for (Recipe selectedRecipe : selectedRecipes) {
			System.out.println("- " + selectedRecipe.getName());
		}
	}

	/**
	 * Lists all managed meals along with their names and total costs.
	 *
	 * @param filename the name of the file to load meals from
	 */
	@Override
	public void listMeals(String filename) {
		// Load recipes from file
		meals = loadMealsFromFile(filename);

		if (meals.isEmpty()) {
			System.out.println("No recipes found to list.");
			return;
		}

		for (int i = 0; i < meals.size(); i++) {
			Meal meal = meals.get(i);
			System.out.println("\nMeal " + (i + 1) + ":");
			System.out.println("    Name: " + meal.getName());
			System.out.println("    Total Cost: " + meal.getTotalCost()+ "$");

		}
		System.out.println("-------------------------\n");
	}
}