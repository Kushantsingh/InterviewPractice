public class TestPostDummyApi extends RestApiActionTemplate
{

    public TestPostDummyApi(String requestType,String basePath,String baseUri)
    {

        this.setRestApiDetails(requestType,basePath,baseUri);
    }

    public void setId(String id)
    {
        this.restDriver.getRestrequest().setPathParameter("id",id);
    }



}
