package factorydesign.runner;

import factorydesign.apiinterface.ApiTest;
import factorydesign.apifactory.ApiFactory;

public class Runner {
    public static void main(String[] args) {
        // Create an instance of the ApiFactory
        ApiFactory apiFactory = new ApiFactory();

        // Create and execute a GET test
        ApiTest getTest = apiFactory.createApiTest("GET");
        getTest.execute();

        // Create and execute a POST test
        ApiTest postTest = apiFactory.createApiTest("POST");
        postTest.execute();

        // Create and execute a PUT test
        ApiTest putTest = apiFactory.createApiTest("PUT");
        putTest.execute();

        // Create and execute a PATCH test
        ApiTest patchTest = apiFactory.createApiTest("PATCH");
        patchTest.execute();

        // Create and execute a DELETE test
        ApiTest deleteTest = apiFactory.createApiTest("DELETE");
        deleteTest.execute();
    }
}
