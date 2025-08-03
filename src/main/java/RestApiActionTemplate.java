import io.restassured.response.Response;

public class RestApiActionTemplate
{
    public RestDriver restDriver;


    public RestApiActionTemplate()
    {
        this.restDriver=new RestDriver();
    }
    public void setRestApiDetails(String requestType,String baseUri,String basePath)
    {
        this.restDriver.getRestrequest().setRestType(requestType);
        this.restDriver.getRestrequest().setBasePath(basePath);
        this.restDriver.getRestrequest().setBaseUri(baseUri);
    }

    public void setPathParameter(String key,String value)
    {
        this.restDriver.getRestrequest().setPathParameter(key, value);
    }

    public void setBody(String body)
    {
        this.restDriver.getRestrequest().setBody(body);

    }
    public Response executeAndGetResponse()
    {
        return this.restDriver.execute();
    }

}
