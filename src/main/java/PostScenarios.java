import Util.DataProviderForUsernamePassword;
import Util.JsonPathUtilityClass;
import Util.UtilityClass;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PostScenarios extends BaseTest{




    @BeforeClass
    public void beforeClass()
    {
        System.out.println("FromBeforeClass");
    }

    @BeforeMethod()
    public void beforeMethod()
    {
        System.out.println("inside before Method");
    }



    @Test (dataProvider = "getUsernamePassword",dataProviderClass = DataProviderForUsernamePassword.class)
    public void createPost(String name,String password)
    {
        System.out.println(System.getProperty("testing"));

        System.out.println(name+" " +password);


//        TestPostDummyApi testPostDummyApi=new TestPostDummyApi("POST","https://jsonplaceholder.typicode.com","/posts");
//        testPostDummyApi.setBody("{\n" + "  \"title\": \"foo\",\n" + "  \"body\": \"bar\",\n" + "  \"userId\": 1\n" + "}");
//        Response response= testPostDummyApi.executeAndGetResponse();
//        System.out.println(response.getBody().asString());


    }
        @Test(dependsOnMethods = "createPost")
    public void getPostWithId()
    {
        TestPostDummyApi testPostDummyApi=new TestPostDummyApi("GET","https://jsonplaceholder.typicode.com/","posts/{id}");
        testPostDummyApi.setId("1");
        Response response=testPostDummyApi.executeAndGetResponse();
        System.out.println(response.getBody().asString());

        System.out.println(JsonPathUtilityClass.getValueByPathJayway(response.getBody().asString(),"id"));


    }
}
