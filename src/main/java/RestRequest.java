import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Reporter;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class RestRequest {
    int connectionTimeout = 24000;
    String restType;
    int closeIdleConnection;
    private String body;
    private String baseUri;
    private String basePath;
    private HashMap<String, String> pathParameters = new HashMap<>();
    private HashMap<String, String> queryParameters = new HashMap<>();
    private HashMap<String, String> formParameters = new HashMap<>();
    private HashMap<String, String> headerParameters = new HashMap<>();

    private RequestSpecification requestSpecification;

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setRestType(String restType) {
        this.restType = restType;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public void setPathParameter(String key, String value) {
        if (!(key == null || value == null))
            this.pathParameters.put(key, value);
    }

    public void setHeaderParameter(String key, String value) {
        if (!(key == null || value == null))
            this.headerParameters.put(key, value);
    }

    public void setQueryParameter(String key, String value) {
        if (!(key == null || value == null))
            this.queryParameters.put(key, value);
    }

    public void setFormParameter(String key, String value) {
        if (!(key == null || value == null))
            this.formParameters.put(key, value);
    }

    public void setPathParameters(HashMap<String, String> pathParameters) {
        if (!(pathParameters == null))
            this.queryParameters.putAll(pathParameters);
    }

    public void setQueryParameters(HashMap<String, String> queryParameters) {
        if (!(queryParameters == null))
            this.queryParameters.putAll(queryParameters);
    }

    public void setFormParameters(HashMap<String, String> formParameters) {
        if (!(formParameters == null))
            this.queryParameters.putAll(formParameters);
    }

    public void setHeaderParameters(HashMap<String, String> headerParameters) {
        if (!(headerParameters == null))
            this.headerParameters.putAll(headerParameters);
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRestType() {
        return restType;
    }

    public int getCloseIdleConnection() {
        return closeIdleConnection;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public HashMap<String, String> getPathParameters() {
        return pathParameters;
    }

    public HashMap<String, String> getQueryParameters() {
        return queryParameters;
    }

    public HashMap<String, String> getFormParameters() {
        return formParameters;
    }

    public HashMap<String, String> getHeaderParameters() {
        return headerParameters;
    }


    public Response restResponse() {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        requestSpecification.basePath(this.basePath);
        requestSpecification.baseUri(this.baseUri);

        if (!(this.queryParameters.isEmpty()))
            requestSpecification.queryParams(queryParameters);
        if (!(pathParameters.isEmpty()))
            requestSpecification.pathParams(pathParameters);
        if (!(headerParameters.isEmpty()))
            requestSpecification.headers(headerParameters);
        if (!(formParameters.isEmpty()))
            requestSpecification.formParams(formParameters);
        if (!(body == null))
            requestSpecification.body(body);


        RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        httpClientConfig.setParam("http.connection.timeout", connectionTimeout);

        restAssuredConfig.httpClient(httpClientConfig);
        requestSpecification.config(restAssuredConfig);

        Response response = requestSpecification.request(restType).then().statusCode(200).body("id", equalTo(1)).extract().response();

        this.report();
        Reporter.log("Body:" + " " + response.getBody().asString());


        return response;


    }

    public void report() {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(this.requestSpecification);

        Reporter.log(queryableRequestSpecification.getMethod() + " " + queryableRequestSpecification.getBaseUri() + " " + queryableRequestSpecification.getBasePath());


    }
}

