import java.io.*;
import java.util.logging.*;

public class ExceptionHandlingDemo {

	// Logger for proper logging
	private static final Logger logger = Logger.getLogger(ExceptionHandlingDemo.class.getName());

	public static void main(String[] args) {

		BufferedReader reader = null;

		try {
			// Runtime Exception (NumberFormatException)
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter age: ");
			int age = Integer.parseInt(reader.readLine());

			// Manually throwing custom checked exception
			validateAge(age);

			// Unchecked exception example
			int result = 10 / age; // ArithmeticException if age = 0
			System.out.println("Calculation result: " + result);

		}
		catch (InvalidAgeException e) {
			// Handling checked exception
			logger.severe("Validation Error: " + e.getMessage());
			System.out.println("Error: " + e.getMessage());
		}
		catch (NumberFormatException e) {
			// Handling runtime exception
			logger.warning("Invalid number format");
			System.out.println("Please enter a valid number.");
		}
		catch (ArithmeticException e) {
			logger.warning("Division by zero attempted");
			System.out.println("Age cannot be zero.");
		}
		catch (IOException e) {
			// Checked exception
			logger.severe("Input/Output error occurred");
			System.out.println("Input error occurred.");
		}
		finally {
			// Cleanup block
			try {
				if (reader != null) {
					reader.close();
					System.out.println("Resources closed successfully.");
				}
			} catch (IOException e) {
				logger.severe("Failed to close resources");
			}
		}

		System.out.println("Program execution completed.");
	}

	// Method throwing custom checked exception
	public static void validateAge(int age) throws InvalidAgeException {
		if (age < 18) {
			throw new InvalidAgeException("Age must be 18 or above.");
		}
	}
}
