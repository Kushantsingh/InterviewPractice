package Util;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class UtilityClass  {

    @BeforeSuite
    public void testing()
    {
        System.out.println("From Before Suite");
    }

    @AfterSuite
    public void testing2()
    {
        System.out.println("From After Suite");
    }
}
