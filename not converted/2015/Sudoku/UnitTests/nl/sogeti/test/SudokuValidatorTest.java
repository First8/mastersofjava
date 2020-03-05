package nl.sogeti.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import nl.equens.tester.SudokuValidator;

import org.apache.log4j.ConsoleAppender;

public class SudokuValidatorTest {
	private static Logger LOGGER = Logger.getLogger(SudokuValidatorTest.class
			.getName());

	/**
	 * 
	 * TESTS
	 * 
	 */

	public void testCorrectAndSimple() {
		Integer[][] field = { //
		{ 6, 1, 7, 8, 2, 9, 4, 3, 5 }, //
				{ 5, 3, 8, 1, 4, 6, 2, 9, 7 }, //
				{ 4, 9, 2, 5, 7, 3, 1, 8, 6 }, //
				{ 3, 8, 5, 6, 9, 4, 7, 1, 2 }, //
				{ 1, 7, 6, 2, 5, 8, 9, 4, 3 }, //
				{ 2, 4, 9, 7, 3, 1, 5, 6, 8 }, //
				{ 8, 2, 3, 4, 1, 7, 6, 5, 9 }, //
				{ 9, 5, 1, 3, 6, 2, 8, 7, 4 }, //
				{ 7, 6, 4, 9, 8, 5, 3, 2, 1 } //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isTrue("Simple field should be valid", validator.isValid());
	}

	
	public void testCorrectButComplex() {
		Integer[][] field = { //
				{9, 2, 4, 6, 3, 7, 8, 1, 5}, //
				{7, 1, 8, 9, 5, 4, 3, 6, 2}, //
				{3, 5, 6, 2, 8, 1, 4, 9, 7}, //
				{8, 6, 3, 1, 7, 5, 9, 2, 4}, //
				{5, 4, 9, 3, 2, 8, 1, 7, 6}, //
				{2, 7, 1, 4, 6, 9, 5, 3, 8}, //
				{4, 3, 2, 5, 1, 6, 7, 8, 9}, //
				{6, 8, 5, 7, 9, 3, 2, 4, 1}, //
				{1, 9, 7, 8, 4, 2, 6, 5, 3} //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isTrue("Complex field should be valid", validator.isValid());
	}

	public void testInvalidateHeight() {
		Integer[][] field = { //
		{ 6, 1, 7, 8, 2, 9, 4, 3, 5 }, //
				{ 5, 3, 8, 1, 4, 6, 2, 9, 7 }, //
				{ 4, 9, 2, 5, 7, 3, 1, 8, 6 }, //
				{ 3, 8, 5, 6, 9, 4, 7, 1, 2 }, //
				{ 1, 7, 6, 2, 5, 8, 9, 4, 3 }, //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isFalse("Simple field should have invalid height",
				validator.isValid());
	}
	

	public void testInvalidateWidth() {
		Integer[][] field = { //
				{ 6, 1, 7, 8, 2, 9, 4  }, //
				{ 5, 3, 8, 1, 4, 6, 2  }, //
				{ 4, 9, 2, 5, 7, 3, 1  }, //
				{ 3, 8, 5, 6, 9, 4, 7  }, //
				{ 1, 7, 6, 2, 5, 8, 9  }, //
				{ 2, 4, 9, 7, 3, 1, 5  }, //
				{ 8, 2, 3, 4, 1, 7, 6  }, //
				{ 9, 5, 1, 3, 6, 2, 8  }, //
				{ 7, 6, 4, 9, 8, 5, 3  } //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isFalse("Simple field should have invalid width",
				validator.isValid());
	}
	
	

	public void testInvalidateExceededMax() {
		Integer[][] field = { //
				{ 6, 1, 7, 8, 2, 9, 4  }, //
				{ 5, 3, 8, 1, 4, 6, 2  }, //
				{ 4, 9, 2, 5, 7, 3, 1  }, //
				{ 3, 8, 10, 6, 9, 4, 7  }, //
				{ 1, 7, 6, 2, 5, 8, 9  }, //
				{ 2, 4, 9, 7, 3, 1, 5  }, //
				{ 8, 2, 3, 4, 1, 7, 6  }, //
				{ 9, 5, 1, 3, 6, 2, 8  }, //
				{ 7, 6, 4, 9, 8, 5, 3  } //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isFalse("Simple field should have exceed maximum",
				validator.isValid());
	}
	
	public void testInvalidateBelowMin() {
		Integer[][] field = { //
				{ 6, 1, 7, 8, 2, 9, 4  }, //
				{ 5, 3, 8, 1, 4, 6, 2  }, //
				{ 4, 9, 2, 5, 7, 3, 1  }, //
				{ 3, 8, -1, 6, 9, 4, 7  }, //
				{ 1, 7, 6, 2, 5, 8, 9  }, //
				{ 2, 4, 9, 7, 3, 1, 5  }, //
				{ 8, 2, 3, 4, 1, 7, 6  }, //
				{ 9, 5, 1, 3, 6, 2, 8  }, //
				{ 7, 6, 4, 9, 8, 5, 3  } //
		};

		SudokuValidator validator = new SudokuValidator(field);
		
		Assert.isFalse("Simple field should have value below minimum",
				validator.isValid());
	}
	
	
	public void testIncorrectRows() {
		Integer[][] field = { //
		        { 6, 1, 7, 8, 4, 9, 4, 3, 5 }, // 4, should be 2
				{ 5, 3, 8, 1, 2, 6, 2, 9, 7 }, // 2, should be 4
				{ 4, 9, 2, 5, 7, 3, 1, 8, 6 }, //
				{ 3, 8, 5, 6, 9, 4, 7, 1, 2 }, //
				{ 1, 7, 6, 2, 5, 8, 9, 4, 3 }, //
				{ 2, 4, 9, 7, 3, 1, 5, 6, 8 }, //
				{ 8, 2, 3, 4, 1, 7, 6, 5, 9 }, //
				{ 9, 5, 1, 3, 6, 2, 8, 7, 4 }, //
				{ 7, 6, 4, 9, 8, 5, 3, 2, 1 } //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isFalse("Simple field should have invalid rows", validator.isValid());
	}
	
	public void testIncorrectColumns() {
		Integer[][] field = { //
		        { 6, 1, 8, 7, 2, 9, 4, 3, 5 }, // 8 <-> 7
				{ 5, 3, 8, 1, 4, 6, 2, 9, 7 }, // 
				{ 4, 9, 2, 5, 7, 3, 1, 8, 6 }, //
				{ 3, 8, 5, 6, 9, 4, 7, 1, 2 }, //
				{ 1, 7, 6, 2, 5, 8, 9, 4, 3 }, //
				{ 2, 4, 9, 7, 3, 1, 5, 6, 8 }, //
				{ 8, 2, 3, 4, 1, 7, 6, 5, 9 }, //
				{ 9, 5, 1, 3, 6, 2, 8, 7, 4 }, //
				{ 7, 6, 4, 9, 8, 5, 3, 2, 1 } //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isFalse("Simple field should have invalid columns", validator.isValid());
	}
	
	public void testIncorrectCell() {
		Integer[][] field = { //
		        { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, // 
				{ 2, 3, 4, 5, 6, 7, 8, 9, 1 }, // 
				{ 3, 4, 5, 6, 7, 8, 9, 1, 2 }, //
				{ 4, 5, 6, 7, 8, 9, 1, 2, 3 }, //
				{ 5, 6, 7, 8, 9, 1, 2, 3, 4 }, //
				{ 6, 7, 8, 9, 1, 2, 3, 4, 5 }, //
				{ 7, 8, 9, 1, 2, 3, 4, 5, 6 }, //
				{ 8, 9, 1, 2, 3, 4, 5, 6, 7 }, //
				{ 9, 1, 2, 3, 4, 5, 6, 7, 8 } //
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isFalse("Simple field should have invalid columns", validator.isValid());
	}
	
	
	public void testResultMatchesInput() {
		
		Integer [][] input = {//
		        		{ null, null, null, null, null, null, null,    1, null }, // 
						{ null,    1,    8,    9, null, null, null,    6, null }, // 
						{ null, null,    6, null,    8,    1,    4, null, null }, //
						{    8, null,    3, null, null, null, null,    2,    4 }, //
						{    5, null, null,    3, null,    8, null, null,    6 }, //
						{    2,    7, null, null, null, null,    5, null,    8 }, //
						{ null, null,    2,    5,    1, null,    7, null, null }, //
						{ null,    8, null, null, null,    3,    2,    4, null }, //
						{ null,    9, null, null, null, null, null, null, null } //
		};
		
		Integer[][] field = { //
				{9, 2, 4, 6, 3, 7, 8, 1, 5}, //
				{7, 1, 8, 9, 5, 4, 3, 6, 2}, //
				{3, 5, 6, 2, 8, 1, 4, 9, 7}, //
				{8, 6, 3, 1, 7, 5, 9, 2, 4}, //
				{5, 4, 9, 3, 2, 8, 1, 7, 6}, //
				{2, 7, 1, 4, 6, 9, 5, 3, 8}, //
				{4, 3, 2, 5, 1, 6, 7, 8, 9}, //
				{6, 8, 5, 7, 9, 3, 2, 4, 1}, //
				{1, 9, 7, 8, 4, 2, 6, 5, 3}//
		};

		SudokuValidator validator = new SudokuValidator(field);


		Assert.isTrue("Result field should match input", validator.matchesSeed(input));
		Assert.isTrue("Result field should be valid", validator.isValid());
	}
	
	
	public void testResultDoesntMatchInput() {
		
		Integer [][] input = {//
		        		{ null, null, null, null, null, null, null,    1, null }, // 
						{ null,    1,    8,    9, null, null, null,    6, null }, // 
						{ null, null,    6, null,    8,    1,    4, null, null }, //
						{    8, null,    5, null, null, null, null,    2,    4 }, // 5, should be 3
						{    5, null, null,    3, null,    8, null, null,    6 }, //
						{    2,    7, null, null, null, null,    5, null,    8 }, //
						{ null, null,    2,    5,    1, null,    7, null, null }, //
						{ null,    8, null, null, null,    3,    2,    4, null }, //
						{ null,    9, null, null, null, null, null, null, null } //
		};
		
		Integer[][] field = { //
				{9, 2, 4, 6, 3, 7, 8, 1, 5}, //
				{7, 1, 8, 9, 5, 4, 3, 6, 2}, //
				{3, 5, 6, 2, 8, 1, 4, 9, 7}, //
				{8, 6, 3, 1, 7, 5, 9, 2, 4}, //
				{5, 4, 9, 3, 2, 8, 1, 7, 6}, //
				{2, 7, 1, 4, 6, 9, 5, 3, 8}, //
				{4, 3, 2, 5, 1, 6, 7, 8, 9}, //
				{6, 8, 5, 7, 9, 3, 2, 4, 1}, //
				{1, 9, 7, 8, 4, 2, 6, 5, 3}//
		};

		SudokuValidator validator = new SudokuValidator(field);

		Assert.isTrue("Result field should be valid", validator.isValid());
		Assert.isFalse("Result field shouldn't match input", validator.matchesSeed(input));
	}
}
