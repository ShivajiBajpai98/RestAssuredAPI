package factorydesign.apifactory;

import factorydesign.apiinterface.ApiTest;
import factorydesign.apiclasses.*;

public class ApiFactory {
    public ApiTest createApiTest(String testType) {
        try {
            // Create and return an instance of the appropriate API test based on the test type
            if (testType.equalsIgnoreCase("GET")) {
                return new Get();
            } else if (testType.equalsIgnoreCase("POST")) {
                return new Post();
            } else if (testType.equalsIgnoreCase("PUT")) {
                return new Put();
            } else if (testType.equalsIgnoreCase("PATCH")) {
                return new Patch();
            } else if (testType.equalsIgnoreCase("DELETE")) {
                return new Delete();
            }

            // If an invalid test type is provided, handle the exception
            throw new IllegalArgumentException("Invalid test type: " + testType);
        } catch (IllegalArgumentException e) {
            // Handle the exception and provide appropriate error handling or fallback behavior
            System.err.println("Error creating API test: " + e.getMessage());
            // Return a default or fallback implementation if needed
            return null;
        }
    }
}
