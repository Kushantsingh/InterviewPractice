package Util;

import org.testng.annotations.DataProvider;

public class DataProviderForUsernamePassword {

    @DataProvider(name = "getUsernamePassword")
    public Object[][] getUsernamePassword()
    {

        return new String[][]{{"kushant","password1"},{"sweta","password2"}};
    }
}
