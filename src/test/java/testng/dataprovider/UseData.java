package testng.dataprovider;

import org.testng.annotations.Test;

public class UseData
{
    @Test(dataProvider = "logins",dataProviderClass = DataProvider.class)
    public void loginTest(String email, String pwd)
    {
        System.out.println(email+" "+ pwd);
    }

}
