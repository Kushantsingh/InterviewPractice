import io.restassured.response.Response;

public class RestDriver
{

    private RestRequest restRequest;

    public RestDriver() {
        this.restRequest = new RestRequest();
    }

    public RestRequest getRestrequest()
    {
        return this.restRequest;

    }

    public Response execute()
    {
        return this.restRequest.restResponse();
    }

}
