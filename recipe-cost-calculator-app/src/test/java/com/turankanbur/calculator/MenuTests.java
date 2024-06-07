/**
*
@file MenuTests.java
@brief This file contains the test cases for the Menu class.
@details This file includes test methods to validate the functionality of the Menu class. It uses JUnit for unit testing.
*/
package com.turankanbur.calculator;


/**
 * Provides methods for asserting equality between expected and actual values.
 */
import static org.junit.Assert.assertEquals;

/**
 * Provides methods for asserting true conditions.
 */
import static org.junit.Assert.assertTrue;

/**
 * Used to indicate that a test case should fail if it is reached.
 */
import static org.junit.Assert.fail;

/**
 * Provides an input stream backed by a byte array.
 */
import java.io.ByteArrayInputStream;

/**
 * Provides an output stream that can be written to a byte array.
 */
import java.io.ByteArrayOutputStream;

/**
 * Represents an input stream of bytes.
 */
import java.io.InputStream;

/**
 * Represents a stream to print formatted representations of objects to an output stream.
 */
import java.io.PrintStream;

/**
 * A simple text scanner which can parse primitive types and strings using regular expressions.
 */
import java.util.Scanner;

/**
 * Used to perform clean-up activities after each test method.
 */
import org.junit.After;

/**
 * Used to specify methods which should be run before each test case.
 */
import org.junit.Before;

/**
 * Used to specify test methods.
 */
import org.junit.Test;



/**

@class CalculatorAppTest
@brief This class represents the test class for the CalculatorApp class.
@details The CalculatorAppTest class provides test methods to verify the behavior of the CalculatorApp class. It includes test methods for successful execution, object creation, and error handling scenarios.
@author ugur.coruh
*/
public class MenuTests {

	/**
	 * Represents the output stream used for testing purposes.
	 */
	private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

	/**
	 * Sets up the test environment before each test case.
	 */
	@Before
	public void setUp() {
		System.setOut(new PrintStream(testOut));
	}

	/**
	 * Restores the test environment after each test case.
	 */
	@After
	public void tearDown() {
		System.setIn(System.in);
		System.setOut(System.out);
	}

	/**
	 * Test case for clearScreen method of Menu class.
	 */
	@Test
	public void testClearScreen() {
		Menu app = new Menu();
		try {
			app.clearScreen();

			assertTrue(true);
		} catch (Exception e) {

			fail("Exception thrown while clearing the screen: " + e.getMessage());
		}

	}

	/**
	 * Represents the original standard input stream.
	 */
	private final InputStream systemIn = System.in;

	/**
	 * Represents the original standard output stream.
	 */
	private final PrintStream systemOut = System.out;

	/**
	 * Sets up the test output stream.
	 */
	@Before
	public void setUpOutput() {

		System.setOut(new PrintStream(testOut));
	}

	/**
	 * Restores the original standard input and output streams.
	 */
	@After
	public void restoreSystemInputOutput() {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}

	/**
	 * Provides input to the scanner from a string.
	 * 
	 * @param input   The input string to provide to the scanner.
	 * @param scanner The scanner to provide input to.
	 */
	private void provideInput(String input, Scanner scanner) {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
	}

	/**
	 * Tests the registration and login option.
	 * 
	 * @throws InterruptedException If the thread is interrupted.
	 */
	@Test
	public void testRegisterandLoginOption() throws InterruptedException {
		String inputForRegister = "1\n53\n53\n7\n2\n53\n53\n5\n";
		Scanner scanner = new Scanner(inputForRegister);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForRegister, scanner);
		//Thread.sleep(100);
		// Call the userMenu() method
		Menu user = new Menu();
		user.userMenu(scanner);

		// Get the actual output
		String actualOutput = testOut.toString();

		// ssert if the actual output matches the expected output
		// Assert if the actual output matches the expected output after trimming
		// whitespace
		assertTrue(actualOutput.contains("registered successfully"));
	}

	/**
	 * Tests the login option with incorrect credentials.
	 * 
	 * @throws InterruptedException If the thread is interrupted.
	 */
	@Test
	public void testFailedBothNotEqualLoginOption() throws InterruptedException {
		String inputForRegister = "2\nasdasdas\ndasdasdas\n2\n53\n52\n1\n\n\n4\n";
		
		Scanner scanner = new Scanner(inputForRegister);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForRegister, scanner);
		//Thread.sleep(100);
		// Call the userMenu() method
		Menu user = new Menu();
		user.userMenu(scanner);

		// Get the actual output
		String actualOutput = testOut.toString();

		// ssert if the actual output matches the expected output
		// Assert if the actual output matches the expected output after trimming
		// whitespace
		assertTrue(actualOutput.contains("Incorrect"));
	}
  
	/**
	 * Tests the exit option in the ingredient menu.
	 */
    @Test
    public void testIngredientMenuExitOption() {
	  String inputForIngredientMenuExit = "1\n5\n5\n";
      Scanner scanner = new Scanner(inputForIngredientMenuExit);
      // Simulate user input: "5" for exit, then "y" for "Yes"
	
	  provideInput(inputForIngredientMenuExit,scanner);
      // Call the selectMenu() method
      Menu menu = new Menu();
      menu.selectMenu(scanner);
    
      // Define expected output after choosing to exit
      String expectedOutput = "Press First Character for further Operations\n"+ "----------------\n" +
              "Ingredient Management Menu\n" +
              "----------------\n" +
              "1-Add Ingredient\n" +
              "2-View Ingredient\n" +
              "3-Edit Ingredient\n" +
              "4-Remove Ingredient\n" +
              "5-Main Menu\n" +
              "Press First Character for further Operation \n" +
              "Welcome To Recipe Cost Calculator App\n" +
              "1-Ingredient Management\n" +
              "2-Recipe Costing\n" +
              "3-Plan Meals\n" +
              "4-About\n" +
              "5-Exit\n" +
              "Press First Character for further Operations\n";
      expectedOutput = expectedOutput.replace("\n", System.lineSeparator());

      // Get the actual output
      String actualOutput = testOut.toString();

      // Assert if the actual output matches the expected output
      assertEquals(expectedOutput, actualOutput);
    }
  
	/**
	 * Tests the exit option in the guest select menu.
	 */
	@Test
	public void testGuestSelectMenuExitOption() {
		String inputForGuestSelectMenu = "3\n1\n1\nc\n1\n2\n2\n1\nc\n2\n2\n3\n1\nc\n3\n2\n4\n1\n4\n2\n1\n5\n";
		Scanner scanner = new Scanner(inputForGuestSelectMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForGuestSelectMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit
		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Welcome"));
	}
  
	/**
	 * Tests the view option in the guest select menu with an invalid keyword.
	 */
	@Test
	public void testGuestSelectMenuViewOptionWithInvalidKeyword() {
		String inputForGuestSelectMenu = "3\n3\n1\na\n5\n";
		Scanner scanner = new Scanner(inputForGuestSelectMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForGuestSelectMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit
		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Invalid"));
	}
	
	/**
	 * Tests the guest select menu with an invalid keyword.
	 */
	@Test
	public void testSelectMenuForGuestWithInvalidKeyword() {
		String testSelectMenuForGuestInvalidKeyword = "3\n4\n1\n6\n5\n";
		Scanner scanner = new Scanner(testSelectMenuForGuestInvalidKeyword);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testSelectMenuForGuestInvalidKeyword, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit
		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}

    /**
     * Tests the exit option in the recipe menu.
     */
    @Test
    public void testRecipeMenuExitOption() {
	  String inputforRecipeMenuExit = "2\n4\n5\n";
      Scanner scanner = new Scanner(inputforRecipeMenuExit);
      // Simulate user input: "5" for exit, then "y" for "Yes"
	
	  provideInput(inputforRecipeMenuExit,scanner);
      // Call the selectMenu() method
      Menu menu = new Menu();
      menu.selectMenu(scanner);
    
      // Define expected output after choosing to exit
      String expectedOutput = "Press First Character for further Operations\n"+ "----------------\n" +
              "Recipe Costing Menu\n" +
              "----------------\n" +
              "1-Create Recipe\n" +
              "2-View Recipes\n" +
              "3-Edit Recipes\n" +
              "4-Main Menu\n" +
              "Press First Character for further Operation \n" +
              "Welcome To Recipe Cost Calculator App\n" +
              "1-Ingredient Management\n" +
              "2-Recipe Costing\n" +
              "3-Plan Meals\n" +
              "4-About\n" +
              "5-Exit\n" +
              "Press First Character for further Operations\n";
      expectedOutput = expectedOutput.replace("\n", System.lineSeparator());

      // Get the actual output
      String actualOutput = testOut.toString();

      // Assert if the actual output matches the expected output
      assertEquals(expectedOutput, actualOutput);
    }
  
    /**
	 * Tests the  plan meal menu with an invalid keyword.
	 */
    @Test
	public void testPlanMealMenuInvalidOption() {
		String testPlanMealMenuInvalidOption = "1\n53\n53\n2\n53\n53\n3\n3\n3\n2\nc\n3\n9\n3\n5\n";
		Scanner scanner = new Scanner(testPlanMealMenuInvalidOption);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testPlanMealMenuInvalidOption, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}
    
    /**
	 * Tests the  plan meal guest menu with an invalid keyword.
	 */
    @Test
	public void testPlanMealGuestMenuInvalidOption() {
		String testPlanMealGuestMenuInvalidOption = "3\n3\n9\n2\n5\n";
		Scanner scanner = new Scanner(testPlanMealGuestMenuInvalidOption);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testPlanMealGuestMenuInvalidOption, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}
        
    /**
     * Tests the exit option in the plan meal menu.
     */
    @Test
    public void testPlanMealmenuExitOption() {
	  String inputForMealMenuExit = "3\n3\n5\n";
      Scanner scanner = new Scanner(inputForMealMenuExit);
      // Simulate user input: "5" for exit, then "y" for "Yes"
	
	  provideInput(inputForMealMenuExit,scanner);
      // Call the selectMenu() method
      Menu menu = new Menu();
      menu.selectMenu(scanner);
    
      // Define expected output after choosing to exit
      String expectedOutput = "Press First Character for further Operations\n"+ "----------------\n" +
              "Plan Meal Menu\n" +
              "----------------\n" +
              "1-Create Meal\n" +
              "2-View Meals\n" +
              "3-Main Menu\n" +
              "Press First Character for further Operation \n" +
              "Welcome To Recipe Cost Calculator App\n" +
              "1-Ingredient Management\n" +
              "2-Recipe Costing\n" +
              "3-Plan Meals\n" +
              "4-About\n" +
              "5-Exit\n" +
              "Press First Character for further Operations\n";
      expectedOutput = expectedOutput.replace("\n", System.lineSeparator());

      // Get the actual output
      String actualOutput = testOut.toString();

      // Assert if the actual output matches the expected output
      assertEquals(expectedOutput, actualOutput);
    }
  
    /**
     * Tests the exit option in the About menu.
     */
    @Test
    public void testAboutExitOption() {
	  String inputForAbout = "4\n1\n5\n";
      Scanner scanner = new Scanner(inputForAbout);
      // Simulate user input: "5" for inputforAbout, then "y" for "Yes"
	
	  provideInput(inputForAbout,scanner);
      // Call the selectMenu() method
      Menu menu = new Menu();
      menu.selectMenu(scanner);
    
      // Define expected output after choosing to exit
      String expectedOutput = "Press First Character for further Operations\n"+
              "***** RECIPE COST CALCULATOR APP *****\n"
              + "=> This Project Is About Recipe Cost \n"
              + "=> In This Project We Can Add , View, Edit And Remove Ingredient\n"
              + "=> In This Project We Can Create and View Recipes\n"
              + "=> In This Project We Can Create and View Meals\n"
              + "<<<<-Press 1 for Main Menu->>>>\n"+
              "Welcome To Recipe Cost Calculator App\n" +
              "1-Ingredient Management\n" +
              "2-Recipe Costing\n" +
              "3-Plan Meals\n" +
              "4-About\n" +
              "5-Exit\n" +
              "Press First Character for further Operations\n";
      expectedOutput = expectedOutput.replace("\n", System.lineSeparator());

      // Get the actual output
      String actualOutput = testOut.toString();

      // Assert if the actual output matches the expected output
      assertEquals(expectedOutput, actualOutput);
    }	
  
	/**
	 * Tests the exit option in the guest menu.
	 */
	@Test
	public void testGuestMenuExitOption() {
		String testGuestMenuExitOption = "3\n1\n1\na\n1\n1\nc\n1\n2\n2\n1\nc\n2\n2\n3\n1\nc\n3\n2\n4\n1\n5\n";
		Scanner scanner = new Scanner(testGuestMenuExitOption);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testGuestMenuExitOption, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Welcome"));
	}
     
	/**
	 * Tests the guest recipe menu option in the guest menu with an invalid keyword.
	 */
	@Test
	public void testRecipeGuestMenuOptionWithInvalidKey() {
		String testGuestMenuExitOptionInvalidKey = "3\n2\n1\nc\n2\n8\n2\n5\n";
		Scanner scanner = new Scanner(testGuestMenuExitOptionInvalidKey);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testGuestMenuExitOptionInvalidKey, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}
	
	/**
	 * Tests the view guest recipe menu option in the guest menu with an invalid keyword.
	 */
	@Test
	public void testRecipeGuestMenuViewOptionWithInvalidKey() {
		String testViewGuestMenuInvalidKey = "3\n2\n1\na\n5\n";
		Scanner scanner = new Scanner(testViewGuestMenuInvalidKey);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testViewGuestMenuInvalidKey, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Welcome"));
	}
  
	/**
	 * Tests the add option in the ingredient menu.
	 */
	@Test
	public void testIngredientMenuAddOption() {
		String inputForIngredientAddMenu = "1\n1\n\n\n25\n5\n";
		Scanner scanner = new Scanner(inputForIngredientAddMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientAddMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Welcome"));
	}
  
	/**
	 * Tests the view option in the ingredient menu.
	 */
	@Test
	public void testIngredientMenuViewOption() {
		String inputForIngredientViewMenu = "1\n2\nc\n5\n\n";
		Scanner scanner = new Scanner(inputForIngredientViewMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientViewMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Press 'c'"));
	}
  
	/**
	 * Tests the edit option in the ingredient menu with cancel.
	 */
	@Test
	public void testIngredientMenuEditOptionWithCancel() {
		String inputForIngredientEditMenu = "1\n1\nyumurta\n10\n1\n3\nsalca\n25\nc\n5\n";
		Scanner scanner = new Scanner(inputForIngredientEditMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientEditMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Cancel"));
	}
   
	/**
	 * Tests the edit option in the ingredient menu with save.
	 */
	@Test
	public void testIngredientMenuEditOptionWithSave() {
		String inputForIngredientEditMenu = "1\n1\nsalca\n10\n1\n3\nsalca\n25\ns\n5\n";
		Scanner scanner = new Scanner(inputForIngredientEditMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientEditMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Save"));
	}
     
	/**
	 * Tests the remove option in the ingredient menu.
	 */
	@Test
	public void testIngredientMenuRemoveOption() {
		String inputForIngredientRemoveMenu = "1\n4\n1\ns\n5\n";
		Scanner scanner = new Scanner(inputForIngredientRemoveMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientRemoveMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Save"));
	}

	/**
	 * Tests the remove option in the ingredient menu with save.
	 */
	@Test
	public void testIngredientMenuRemoveOptionWithSave() {
		String inputForIngredientRemoveMenu = "1\n1\ntuz\n30\n1\n4\ntuz\ns\n5\n";
		Scanner scanner = new Scanner(inputForIngredientRemoveMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientRemoveMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Save"));
	}

	/**
	 * Tests the remove option in the ingredient menu with cancel.
	 */
	@Test
	public void testIngredientMenuRemoveOptionWithCancel() {
		String inputForIngredientRemoveMenu = "1\n1\ntuz\n30\n1\n4\ntuz\nc\n5\n";
		Scanner scanner = new Scanner(inputForIngredientRemoveMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForIngredientRemoveMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Cancel"));
	}
	
	/**
	 * Tests the ingredient guest menu option in the guest menu with an invalid keyword.
	 */
	@Test
	public void testIngredientGuestMenuWithInvalidOption() {
		String testViewGuestMenuInvalidKey = "3\n1\n1\nc\n1\n7\n2\n5\n";
		Scanner scanner = new Scanner(testViewGuestMenuInvalidKey);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testViewGuestMenuInvalidKey, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}
	
	/**
	 * Tests the ingredient menu option with an invalid keyword.
	 */
	@Test
	public void testIngredientMenuWithInvalidOption() {
		String testIngredientMenuInvalidKey = "1\n2\nc\n1\n8\n5\n5\n";
		Scanner scanner = new Scanner(testIngredientMenuInvalidKey);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testIngredientMenuInvalidKey, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}

	/**
	 * Tests the create recipe menu option.
	 */
	@Test
	public void testCreateRecipeMenuOption() {

		String inputForCreateRecipeMenu = "2\n1\n-1\n1\n20\nq\n1\n5\n";
		Scanner scanner = new Scanner(inputForCreateRecipeMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForCreateRecipeMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Welcome"));
	}

	/**
	 * Tests the create recipe menu option with valid and invalid keywords.
	 */
	@Test
	public void testCreateRecipeMenuOptionWithValidAndInvalidKeywords() {
		String inputForCreateRecipeMenu = "2\n1\n600\nabc\n4\n20\nq\nyemektarifi\n5\n";
		Scanner scanner = new Scanner(inputForCreateRecipeMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForCreateRecipeMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Welcome"));
	}
  
	/**
	 * Tests the view recipe menu option with an invalid keyword.
	 */
	@Test
	public void testViewRecipeMenuOptionWithInvalidKeyword() {
		String inputForViewRecipeMenu = "2\n2\na\n5\n";
		Scanner scanner = new Scanner(inputForViewRecipeMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForViewRecipeMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Invalid"));
	}

	/**
	 * Tests the edit recipe menu option.
	 */
	@Test
	public void testEditRecipeMenuOption() {

		String inputForEditEecipeMenu = "2\n3\ns\n5\ns\n5\n";
		Scanner scanner = new Scanner(inputForEditEecipeMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForEditEecipeMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Recipe"));
	}

	/**
	 * Tests the recipe menu option with an invalid keyword.
	 */
	@Test
	public void testRecipeMenuWithInvalidOption() {

		String testRecipeMenuInvalidKey = "2\n53\n53\n2\n2\nc\n2\n8\n4\n5\n";
		Scanner scanner = new Scanner(testRecipeMenuInvalidKey);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(testRecipeMenuInvalidKey, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}
	
	/**
	 * Tests the create meal menu option.
	 */
	@Test
	public void testCreateMealMenuOption() {
		String inputForCreateMealMenu = "3\n1\n1\nq\ns\nc\n5\n";
		Scanner scanner = new Scanner(inputForCreateMealMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForCreateMealMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Meal"));
	}

	/**
	 * Tests the create meal menu option with an invalid recipe.
	 */
	@Test
	public void testCreateMealInvalidRecipeMenuOption() {
		String inputForCreateMealMenu = "3\n1\n1\n1000\na\nq\ns\nc\n5\n";
		Scanner scanner = new Scanner(inputForCreateMealMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForCreateMealMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Meal"));
	}

	/**
	 * Tests the view meal menu option.
	 */
	@Test
	public void testViewMealMenuOption() {
		String inputForViewMealMenu = "3\n2\nc\n5\n";
		Scanner scanner = new Scanner(inputForViewMealMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForViewMealMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Meal"));
	}
  
	/**
	 * Tests the view meal menu option with an invalid keyword.
	 */
	@Test
	public void testViewMealMenuOptionWithInvalidKeyword() {
		String inputForViewMealMenu = "3\n2\na\n5\n";
		Scanner scanner = new Scanner(inputForViewMealMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForViewMealMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("Invalid"));
	}

	/**
	 * Tests the about option.
	 */
	@Test
	public void testAboutOption() {
		String inputForAboutMenu = "4\n1\n5\n";
		Scanner scanner = new Scanner(inputForAboutMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForAboutMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("About"));
	}

	/**
	 * Tests the about menu option with an invalid keyword.
	 */
	@Test
	public void testAboutOptionWithInvalidKey() {
		String inputForAboutOptionInvalidKey= "4\n1\n4\n8\n1\n5\n";
		Scanner scanner = new Scanner(inputForAboutOptionInvalidKey);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForAboutOptionInvalidKey, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.selectMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("p"));
	}
	
	/**
	 * Tests the about guest option.
	 */
	@Test
	public void testAboutGuestModeOption() {
		String inputForAboutGuestMenu = "3\n4\n1\n5\n";
		Scanner scanner = new Scanner(inputForAboutGuestMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForAboutGuestMenu, scanner);
		// Call the selectMenu() method
		Menu menu = new Menu();
		menu.userMenu(scanner);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("About"));
	}

	/**
	 * Tests the main method.
	 */
	@Test
	public void TestMain() {
		String inputForAboutMenu = "4\n";
		Scanner scanner = new Scanner(inputForAboutMenu);
		// Simulate user input: "5" for exit, then "y" for "Yes"

		provideInput(inputForAboutMenu, scanner);
		// Call the selectMenu() method
		Menu.main(null);

		// Define expected output after choosing to exit

		// Get the actual output
		String actualOutput = testOut.toString();

		// Assert if the actual output matches the expected output
		assertTrue(actualOutput.contains("User Menu"));
	}
 
}