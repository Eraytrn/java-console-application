/**
 * 
@file LibraryFunctionsTests.java
@brief This file contains the test cases for the Library Functions.
@details This file includes test methods to validate the functionality of the Library Functions. It uses JUnit for unit testing.
*/
package com.turankanbur.calculator;


/**
 * Assertion methods for comparing expected and actual values.
 */
import static org.junit.Assert.*;

/**
 * Provides resizable-array and implements the List interface.
 */
import java.util.ArrayList;

/**
 * Provides utility methods for arrays, such as sorting and searching.
 */
import java.util.Arrays;

/**
 * Provides resizable-array implementation of the List interface.
 */
import java.util.List;

/**
 * Used to specify methods which should be run before each test case.
 */
import org.junit.Before;

/**
 * Used to specify test methods.
 */
import org.junit.Test;

/**
 * Provides an input stream backed by a byte array.
 */
import java.io.ByteArrayInputStream;

/**
 * Provides an output stream that can be written to a byte array.
 */
import java.io.ByteArrayOutputStream;

/**
 * Provides an abstraction for file and directory pathnames.
 */
import java.io.File;

/**
 * Signals that an I/O exception of some sort has occurred.
 */
import java.io.IOException;

/**
 * Used to deserialize objects from an InputStream.
 */
import java.io.ObjectInputStream;

/**
 * Used to print formatted representations of objects to a text-output stream.
 */
import java.io.PrintStream;


/**
 * 
 * @class CalculatorTest
 * @brief This class represents the test class for the Calculator class.
 * @details The CalculatorTest class provides test methods to verify the
 *          behavior of the Calculator class. It includes test methods for
 *          addition, subtraction, multiplication, and division operations.
 * @author ugur.coruh
 */
public class LibraryFunctionsTests {

	
	/**
	 * @brief ByteArrayOutputStream to capture standard error output during testing.
	 */
	private ByteArrayOutputStream errContent;

	
	/**
	 * @brief Object for managing ingredients.
	 */
	private IngredientManagement ingredientManagement;

	
	/**
	 * @brief ByteArrayOutputStream to capture standard output during testing.
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	
	/**
	 * @brief Getter method to retrieve simulated console output.
	 * @return Simulated console output.
	 */
	private String getConsoleOutput() {

		return "simulated console output";
	}

	
	/**
	
 * @brief Object for managing ingredients.
	 */
	private IngredientManagement management;

	
	/**
	 * @brief Object representing a recipe.
	 */
	private Recipe recipe;

	
	/**
	 * @brief List of recipes.
	 */
	private List<Recipe> recipes;

	
	/**
	 * @brief Object representing a user.
	 */
	private User user;

	
	/**
	 * @brief Sets up the test environment before each test method is executed.
	 * 
	 * This method initializes necessary objects and redirects standard output
	 * and error streams to capture them for testing purposes.
	 * 
	 * @details
	 * - Redirects standard output and error streams to capture them.
	 * - Initializes test objects such as User, Recipe, IngredientManagement, and a list of Recipes.
	 * 
	 * @note This method is automatically called before each test method.
	 */
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		user = new User("testUser", "testPassword");
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Ingredient1", 10));
		ingredients.add(new Ingredient("Ingredient2", 20));
		recipe = new Recipe("Test Recipe", ingredients, 50.0);
		errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));
		management = new IngredientManagement(null, 0);
		ingredientManagement = new IngredientManagement("Flour", 2);
		recipes = new ArrayList<>();
	}

	
	/**
     * @brief Tests the save and load functionality of ingredients to/from a file.
     */
	@Test
	public void testSaveAndLoadToFile() {
		Ingredient ingredient1 = new Ingredient("Salt", 2);
		Ingredient ingredient2 = new Ingredient("Sugar", 3);

		management.addIngredient(ingredient1, "testingredient.bin");
		management.addIngredient(ingredient2, "testingredient.bin");

		management.saveToFile("testIngredients.bin");

		IngredientManagement loadedManagement = new IngredientManagement(null, 0);
		loadedManagement.loadFromFile("testIngredients.bin");

		assertEquals(management.getIngredients().size(), loadedManagement.getIngredients().size());
		for (int i = 0; i < management.getIngredients().size(); i++) {
			assertEquals(management.getIngredients().get(i).getName(),
					loadedManagement.getIngredients().get(i).getName());
			assertEquals(management.getIngredients().get(i).getPrice(),
					loadedManagement.getIngredients().get(i).getPrice(), 0.001);
		}
	}

	
	/**
     * @brief Tests finding an ingredient by its name.
     */
	@Test
	public void testFindIngredientByName() {
		Ingredient ingredient1 = new Ingredient("Salt", 2);
		management.addIngredient(ingredient1, "testingredient.bin");

		assertEquals(ingredient1, management.findIngredientByName("Salt"));
		assertNull(management.findIngredientByName("Sugar")); 
	}

	
	 /**
     * @brief Tests listing ingredients.
     */
	@Test
	public void testListIngredients() {
		
		Ingredient flour = new Ingredient("Flour", 2);
		Ingredient sugar = new Ingredient("Sugar", 1);
		Ingredient salt = new Ingredient("Salt", 5);
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(flour);
		ingredients.add(sugar);
		ingredients.add(salt);
		
		ingredientManagement.setIngredient(ingredients);
	
		ingredientManagement.listIngredients();	

		String consoleOutput = getConsoleOutput();
		assertNotNull(consoleOutput, "Console output is not null");
	}

	
	/**
     * @brief Tests listing ingredients when the list is empty.
     */
	@Test
	public void testEmptyListIngredients() {
		
		ingredientManagement.listIngredients();

		String consoleOutput = getConsoleOutput();
		
		assertNotNull(consoleOutput, "No ingredients found to list.");
	}

	
	 /**
     * @brief Tests loading from a non-existent file, expecting an IOException.
     */
	@Test
	public void testLoadFromFileIOException() {
		IngredientManagement instance = new IngredientManagement(null, 0); 

		// Provide a non-existent file name to provoke an IOException
		String fileName = "nonexistentsfile.txt";

		// Redirect System.err to capture the error message
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));

		// Call the method and expect IOException
		instance.loadFromFile(fileName);

		// Reset System.err
		System.setErr(System.err);

		// Check if the error message contains the expected substring
		assertTrue(errContent.toString().contains("Error loading from file"));
	}

	
	/**
     * @brief Tests saving to a file in a non-existent directory, expecting an IOException.
     */
	@Test
	public void testSaveToFileIOException() {
		IngredientManagement instance = new IngredientManagement(null, 0); 

		// Provide a file name for which writing will cause IOException
		String fileName = "nonexistentdirectory/nonexistentfile.txt";

		// Call the method and expect IOException
		instance.saveToFile(fileName);

		// Check if the error message contains the expected substring
		assertTrue(errContent.toString().contains("Error saving to file:"));
	}

	
	/**
     * @brief Tests getting the name of the recipe.
     */
	@Test
	public void testGetName() {
		assertEquals("Test Recipe", recipe.getName());
	}

	
    /**
     * @brief Tests getting the ingredients of the recipe.
     */
	@Test
	public void testGetIngredients() {
		assertNotNull(recipe.getIngredients());
		assertEquals(2, recipe.getIngredients().size());
	}

	
	/**
     * @brief Tests getting the total cost of the recipe.
     */
	@Test
	public void testGetTotalCost() {
		assertEquals(50.0, recipe.getTotalCost(), 0.0);
	}

	
	/**
     * @brief Tests getting the quantity of the recipe.
     */
	@Test
	public void testGetQuantity() {
		assertEquals(0, recipe.getQuantity());
	}

	
	 /**
     * @brief Tests setting the quantity of the recipe.
     */
	@Test
	public void testSetQuantity() {
		recipe.setQuantity(5);
		assertEquals(5, recipe.getQuantity());
	}

	
	/**
     * @brief Tests setting the total cost of the recipe.
     */
	@Test
	public void testSetTotalCost() {
		recipe.setTotalCost(100.0);
		assertEquals(100.0, recipe.getTotalCost(), 0.0);
	}

	
	/**
     * @brief Tests getting the username of the user.
     */
	@Test
	public void testGetUsername() {
		assertEquals("testUser", user.getUsername());
	}

	 /**
     * @brief Tests getting the password of the user.
     */
	@Test
	public void testGetPassword() {

		assertEquals("testPassword", user.getPassword());
	}

	/**
     * @brief Tests creating a meal and checking its constructor and getters.
     */
	@Test
	public void testMealConstructorAndGetters() {
		// Create a list of recipes
		List<Recipe> recipes = new ArrayList<>();
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10.0);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15.0);

		recipes.add(recipe1);
		recipes.add(recipe2);

		// Create a meal
		Meal meal = new Meal("Meal Name", recipes);

		// Test getters
		assertEquals("Meal Name", meal.getName());
		assertEquals(recipes, meal.getRecipes());
		assertEquals(25.0, meal.getTotalCost(), 0.01); // Using delta for double comparison
	}

	
	/**
     * @brief Tests calculating the total cost of a meal.
     */
	@Test
	public void testMealTotalCostCalculation() {
		// Create a list of recipes
		List<Recipe> recipes = new ArrayList<>();
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10.0);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15.0);

		recipes.add(recipe1);
		recipes.add(recipe2);

		// Create a meal
		Meal meal = new Meal("Meal Name", recipes);

		// Test total cost calculation
		assertEquals(25.0, meal.getTotalCost(), 0.01); // Using delta for double comparison
	}

	/**
     * @brief Tests adding a recipe to the recipe management system.
     */
	@Test
	public void testAddRecipe() {
		// Create a recipe management instance
		RecipeManagement recipeManager = new RecipeManagement();

		// Create a sample recipe
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Ingredient 1", 1));
		ingredients.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe = new Recipe("Recipe", ingredients, 10.0);

		// Add the recipe
		recipeManager.addRecipe(recipe, "test_recipesforadd.bin");

		// Load recipes from file
		List<Recipe> loadedRecipes = RecipeManagement.loadFromFile("test_recipesforadd.bin");

		// Check if the added recipe is present
		assertTrue(loadedRecipes.toString().contains("Recipe"));
	}

	
	/**
     * @brief Tests listing recipes in the recipe management system.
     */
	@Test
	public void testListRecipes() {
		// Create a recipe management instance
		RecipeManagement recipeManager = new RecipeManagement();

		// Create some sample recipes
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15);

		// Add the recipes
		recipeManager.addRecipe(recipe1, "test_recipeslist.bin");
		recipeManager.addRecipe(recipe2, "test_recipeslist.bin");
		RecipeManagement.loadFromFile("test_recipeslist.bin");
		// Redirect console output to check the output of listRecipes() method
		recipeManager.listRecipes("test_recipeslist.bin");

		// Check if the output contains the names of the added recipes
		assertTrue(outContent.toString().contains("Recipe 1"));
		assertTrue(outContent.toString().contains("Recipe 2"));
	}

	
	 /**
     * @brief Tests listing recipes when the recipe list is empty.
     */
	@Test
	public void testEmptyListRecipes() {
		// Create a recipe management instance
		RecipeManagement recipeManager = new RecipeManagement();

		// Create some sample recipes
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15);

		// Add the recipes
		recipeManager.addRecipe(recipe1, "test_recipeslist.bin");
		recipeManager.addRecipe(recipe2, "test_recipeslist.bin");
		RecipeManagement.loadFromFile("test_recipeslist.bin");

		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));

		// Redirect console output to check the output of listRecipes() method
		recipeManager.listRecipes("");
		assertTrue(errContent.toString().contains("Error loading from file"));
	}

	
	/**
     * @brief Tests saving recipes to and loading recipes from a file.
     */
	@Test
	public void testSaveAndLoadFromFile() {
		String fileName = "test_recipes.bin";

		// Create some sample recipes
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10.0);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15.0);

		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe1);
		recipes.add(recipe2);

		// Save recipes to file

		RecipeManagement.saveToFile(recipes, fileName);

		// Load recipes from file
		List<Recipe> loadedRecipes = RecipeManagement.loadFromFile(fileName);

		// Check if loaded recipes match original recipes
		assertEquals(recipes.size(), loadedRecipes.size());
		assertFalse(loadedRecipes.contains(recipe1));
		assertFalse(loadedRecipes.contains(recipe2));

		// Delete the test file after testing
		File file = new File(fileName);
		file.delete();
	}

	
	 /**
     * @brief Tests saving recipes to a file when an IOException occurs.
     */
	@Test
	public void testSaveRecipeToFileIOException() {

		// Provide a file name for which writing will cause IOException
		String fileName = "nonexistentdirectory/nonexistentfile.txt";

		// Call the method and expect IOException
		RecipeManagement.saveToFile(recipes, fileName);

		// Check if the error message contains the expected substring
		assertTrue(errContent.toString().contains("Error saving to file:"));
	}

	
	/**
     * @brief Tests loading recipes from a file when an IOException occurs.
     */
	@Test
	public void testLoadRecipeFromFileIOException() {
		new RecipeManagement();

		// Provide a non-existent file name to provoke an IOException
		String fileName = "nonexistentsfile.txt";

		// Redirect System.err to capture the error message
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errContent));

		// Call the method and expect IOException
		RecipeManagement.loadFromFile(fileName);

		// Reset System.err
		System.setErr(System.err);

		// Check if the error message contains the expected substring
		assertTrue(errContent.toString().contains("Error loading from file"));
	}

	
	/**
     * @brief Tests creating a recipe from user input.
     */
	@Test
	public void testCreateRecipeFromUserInput() {
		// Prepare test data
		List<Ingredient> availableIngredients = new ArrayList<>();
		availableIngredients.add(new Ingredient("Ingredient 1", 10));
		availableIngredients.add(new Ingredient("Ingredient 2", 20));

		RecipeManagement recipeManager = new RecipeManagement();
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Ingredient1", 10));
		ingredients.add(new Ingredient("Ingredient2", 20));

		String recipeName = "Recipe 2";
		double totalCost = 30.0;

		// Call the method
		RecipeManagement.createRecipeFromUserInput(availableIngredients, recipeManager, recipeName, totalCost,	ingredients);

		List<Recipe> loadedRecipes = RecipeManagement.loadFromFile("recipes.bin");

		assertNotNull(loadedRecipes);
		assertFalse(loadedRecipes.isEmpty());

	}
	
	 /**
     * @brief Tests getting recipes from the recipe management system.
     */
	@Test
	public void testGetRecipes() {
		// Create a recipe management instance
		RecipeManagement recipeManager = new RecipeManagement();

		// Create some sample recipes
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10.0);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15.0);

		// Add the recipes to the recipe manager
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe1);
		recipes.add(recipe2);
		recipeManager.setRecipes(recipes);

		// Test the getRecipes method
		assertEquals(recipes, recipeManager.getRecipes());
	}

	/**
     * @brief Tests setting recipes in the recipe management system.
     */
	@Test
	public void testSetRecipes() {
		// Create a recipe management instance
		RecipeManagement recipeManager = new RecipeManagement();

		// Create some sample recipes
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient("Ingredient 1", 1));
		ingredients1.add(new Ingredient("Ingredient 2", 2));
		Recipe recipe1 = new Recipe("Recipe 1", ingredients1, 10.0);

		List<Ingredient> ingredients2 = new ArrayList<>();
		ingredients2.add(new Ingredient("Ingredient 3", 1));
		ingredients2.add(new Ingredient("Ingredient 4", 1));
		Recipe recipe2 = new Recipe("Recipe 2", ingredients2, 15.0);

		// Add the recipes to the recipe manager
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe1);
		recipes.add(recipe2);

		// Set the recipes using setRecipes method
		recipeManager.setRecipes(recipes);

		// Test if the recipes are set properly
		assertEquals(recipes, recipeManager.getRecipes());
	}

	
	/**
     * @brief Tests handling invalid marker in recipe file.
     */
	@Test
	public void testInvalidMarker() {
		// Simulate a file input stream with an invalid marker
		String fileName = "test_recipesforinvalidmarker.bin";
		String serializedRecipe = "invalid serialized recipe data";
		String invalidMarker = "#INVALID_MARKER#";
		String fileContent = serializedRecipe + invalidMarker;

		try (ByteArrayInputStream bais = new ByteArrayInputStream(fileContent.getBytes());
				ObjectInputStream ois = new ObjectInputStream(bais)) {

			RecipeManagement.loadFromFile(fileName);

			// We shouldn't reach this point since an exception should be thrown
			assertTrue(false);
		} catch (IOException e) {
			// Check if the error message contains the expected "Invalid marker" message
			assertFalse(e.getMessage().contains("Invalid marker:"));
		}
	}

	
	/**
     * @brief Tests editing the price of an existing ingredient.
     */
	@Test
	public void testEditIngredientPrice_ExistingIngredient() {
	
		IngredientManagement ingredientManager = new IngredientManagement("Flour", 1);
		String fileName = "test_ingredients.bin";
		ingredientManager.addIngredient(ingredientManager, fileName);

	
		ingredientManager.editIngredientPrice("Flour", 2);

		
		assertEquals(2, ingredientManager.findIngredientByName("Flour").getPrice());
	}

	
	/**
     * @brief Tests editing the price of a non-existing ingredient.
     */
	@Test
	public void testEditIngredientPrice_NonExistingIngredient() {
		
		IngredientManagement ingredientManager = new IngredientManagement("Sugar", 2);
		String fileName = "test_ingredients.bin";
		ingredientManager.addIngredient(ingredientManager, fileName);

		ingredientManager.editIngredientPrice("Flour", 3);

		assertTrue(outContent.toString().contains("The price of specified ingredient could not found."));
		
	}

	
	/**
     * @brief Tests adding a meal to the meal management system.
     */
	@Test
	public void testAddMeal() {
	
		Meal meal = new Meal("Test Meal", new ArrayList<>());
		String fileName = "test_meals.bin";

		
		MealManagement mealManager = new MealManagement();

		mealManager.addMeal(meal, fileName);

		assertFalse(mealManager.getMeals().contains(meal));
	}


	/**
     * @brief Tests handling errors when saving meals to a file.
     */
	@Test
	public void testErrorSaveMealsToFile() {
	
		Meal meal1 = new Meal("Test Meal 1", new ArrayList<>());
		Meal meal2 = new Meal("Test Meal 2", new ArrayList<>());
		List<Meal> meals = Arrays.asList(meal1, meal2);
		String fileName = "";
		
		final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

		
		MealManagement mealManager = new MealManagement();

		
		mealManager.saveMealsToFile(meals, fileName);

		mealManager.loadMealsFromFile(fileName);
		

		assertFalse(errContent.toString().contains("No recipes "));
	}

	
	 /**
     * @brief Tests listing meals in the meal management system.
     */
	@Test
	public void testListMeals() {
		
		Meal meal = new Meal("Test Meal", new ArrayList<>());
		String fileName = "test_meals.bin";
		List<Meal> existingMeals = new ArrayList<>();
		existingMeals.add(meal);
		MealManagement mealManager = new MealManagement();
		mealManager.saveMealsToFile(existingMeals, fileName);

		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		mealManager.listMeals("test_meals.bin");
		System.out.println(outContent.toString());
		assertTrue(outContent.toString().contains("Test Meal"));
	}

	
	 /**
     * @brief Tests listing meals when the meal list is empty.
     */
	@Test
	public void testEmptyListMeals() {
		
		String fileName = "test_mealsemptylist.bin";
		List<Meal> existingMeals = new ArrayList<>();
		MealManagement mealManager = new MealManagement();
		mealManager.saveMealsToFile(existingMeals, fileName);

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		mealManager.listMeals(fileName);

		assertFalse(errContent.toString().contains("No recipes "));
	}

	
	/**
     * @brief Tests saving meals to a file and loading meals from a file.
     */
	@Test
	public void testMealSaveAndLoad() {

		String fileName = "test_mealscreate.bin";
		String mealName = "Test Meal";
		List<Recipe> selectedRecipes = new ArrayList<>();

		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Ingredient1", 10));
		ingredients.add(new Ingredient("Ingredient2", 20));

		selectedRecipes.add(new Recipe("Recipe 1", ingredients, 4));
		selectedRecipes.add(new Recipe("Recipe 2", ingredients, 4));

	
		RecipeManagement recipeManager = new RecipeManagement();

	
		MealManagement mealManagement = new MealManagement();

		
		mealManagement.createMealFromUserInput(mealName, selectedRecipes, recipeManager);


		List<Meal> loadedMeals = mealManagement.loadMealsFromFile(fileName);

	
		assertNotNull(loadedMeals);
		assertTrue(loadedMeals.isEmpty());


	}
}