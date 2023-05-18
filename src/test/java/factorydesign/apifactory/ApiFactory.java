package factorydesign.apifactory;

import factorydesign.apiinterface.ApiTest;
import factorydesign.apiclasses.*;

public class ApiFactory {
    public ApiTest createApiTest(String testType) {
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

        throw new IllegalArgumentException("Invalid test type: " + testType);
    }
}
