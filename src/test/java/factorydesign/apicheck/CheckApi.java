package factorydesign.apicheck;

import factorydesign.apifactory.ApiFactory;
import factorydesign.apiinterface.ApiTest;

public class CheckApi
{
    public static void main(String[] args)
    {
        ApiFactory factory = new ApiFactory();

        ApiTest apiTest=  factory.Request("Basic");
        apiTest.Response();


    }
}
