package step_definitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ApacheAndRestAssuredSteps {

    private final static WireMockServer wireMockServer = new WireMockServer(Options.DEFAULT_PORT);
    private final static String url = "http://localhost:8080/api/1/process/stage";

    @Before("@Wiremock")
    public void beforeWiremock() {
        wireMockServer.start();
    }

    @After("@Wiremock")
    public void teardownWiremock() {
        wireMockServer.stop();
    }
    
    @Before("@Docker")
    public void beforeDocker() {
        
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
       
    }
    
    @Before("@Docker")
    public void afterDocker() {
        
    }

    @Given("^I use Httpclient and verify the response$")
    public void  httpClientPoc() throws Throwable {
        
        HttpRequest mainRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("content-type", "application/json")
                .POST(BodyPublishers.ofFile(Paths.get("src/test/resources/__files/testresponse.json")))
                .build();
        
       
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(mainRequest, BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertThat((response.body()), containsString("\"name\":\"value\""));
    }

    @Then("^I use restassured and verify the response$")
    public void restAssuredPoc() throws Throwable {
        given().header("content-type", "application/json").body("{\"name\":\"value\"}")
        .when().post(url)
        .then().statusCode(200).body("name", equalTo("value"));

    }

}
