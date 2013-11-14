package de.uni_potsdam.hpi.metanome.input.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_potsdam.hpi.metanome.algorithm_integration.input.InputGenerationException;
import de.uni_potsdam.hpi.metanome.algorithm_integration.input.InputIterationException;

public class CsvFileTest {

	CsvFileOneLineFixture fixture;
	CsvFile csvFile;
	
	@Before
	public void setUp() throws Exception {
		this.fixture = new CsvFileOneLineFixture();
		this.csvFile = this.fixture.getTestData();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Has next should be true once and false after calling next once (one csv line).
	 * 
	 * @throws InputIterationException 
	 */
	@Test
	public void testHasNext() throws InputIterationException {
		// Check result
		assertTrue(this.csvFile.hasNext());
		this.csvFile.next();
		assertFalse(this.csvFile.hasNext());
	}
	
	/**
	 * A one line csv should be parsed correctly. And all the values in the line should be equal.
	 * 
	 * @throws InputIterationException 
	 */
	@Test
	public void testNext() throws InputIterationException {
		// Check result
		assertEquals(this.fixture.getExpectedStrings(), this.csvFile.next());
	}
	
	/**
	 * A one line csv with differing should be parsed correctly. And all the values in the line should be equal.
	 * 
	 * @throws InputIterationException 
	 * @throws InputGenerationException 
	 */
	@Test
	public void testNextSeparator() throws InputIterationException, InputGenerationException {
		// Setup
		CsvFileOneLineFixture fixtureSeparator = new CsvFileOneLineFixture(';');
		CsvFile csvFileSeparator = fixtureSeparator.getTestData();
		
		// Check result 
		assertEquals(fixtureSeparator.getExpectedStrings(), csvFileSeparator.next());
	}
	
	/**
	 * The remove method should always throw an {@link UnsupportedOperationException}.
	 */
	@Test
	public void testRemove() {
		// Check result
		try {
			this.csvFile.remove();
			fail("Expected an UnsupportedOperationException to be thrown.");
		}
		catch (UnsupportedOperationException actualException) {
			// Intentionally left blank
		}
	}
	
	/**
	 * When iterating over a csv file with alternating line length an exception should be thrown.
	 * 
	 * @throws InputIterationException 
	 * @throws InputGenerationException 
	 */
	@Test
	public void testShort() throws InputIterationException, InputGenerationException {
		// Setup
		CsvFile shortCsvFile = new CsvFileShortLineFixture().getTestData();
		
		// Check result
		try {
			shortCsvFile.next();
			shortCsvFile.next();
			fail("Expected an InputIterationException to be thrown.");
		} catch (InputIterationException actualException) {
			// Intentionally left blank
		}
	}
	
	/**
	 * Test method for {@link CsvFile#numberOfColumns()}
	 * 
	 * A {@link CsvFile} should return the correct number of columns of the file.
	 * 
	 * @throws InputIterationException
	 * @throws InputGenerationException 
	 */
	@Test
	public void testNumberOfColumns() throws InputIterationException, InputGenerationException {
		// Setup 
		CsvFileOneLineFixture fixtureSeparator = new CsvFileOneLineFixture(';');
		CsvFile csvFile = fixtureSeparator.getTestData();
		
		// Check result
		assertEquals(fixture.getExpectedNumberOfColumns(), csvFile.numberOfColumns());
	}
	
	/**
	 * Test method for {@link CsvFile#columnNames()}
	 * 
	 * A {@link CsvFile} should return the correct column names.
	 * 
	 * @throws InputIterationException
	 * @throws InputGenerationException 
	 */
	@Test
	public void testColumnNames() throws InputIterationException, InputGenerationException {
		// Setup
		CsvFileOneLineFixture fixtureSeparator = new CsvFileOneLineFixture(';');
		CsvFile csvFile = fixtureSeparator.getTestData();
		
		// Check result
		assertEquals(fixture.getExpectedColumnNames(), csvFile.columnNames());
	}
}