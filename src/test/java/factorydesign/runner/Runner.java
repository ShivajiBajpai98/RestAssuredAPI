package factorydesign;

import factorydesign.apiinterface.ApiTest;
import factorydesign.apifactory.ApiFactory;

public class Runner {
    public static void main(String[] args) {
        ApiFactory apiFactory = new ApiFactory();

        ApiTest getTest = apiFactory.createApiTest("GET");
        getTest.execute();

        ApiTest postTest = apiFactory.createApiTest("POST");
        postTest.execute();

        ApiTest putTest = apiFactory.createApiTest("PUT");
        putTest.execute();

        ApiTest patchTest = apiFactory.createApiTest("PATCH");
        patchTest.execute();

        ApiTest deleteTest = apiFactory.createApiTest("DELETE");
        deleteTest.execute();
    }
}
