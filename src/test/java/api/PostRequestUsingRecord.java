package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import userdata.UserDataUsingRecord;

import java.io.IOException;


public class PostRequestUsingRecord {

    public static void main(String[] args) throws IOException {

        UserDataUsingRecord userData =
                new UserDataUsingRecord(66, "mosu","m", "mosu@gmail.com");

//        Post call:
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse postResponse = apiRequestContext.post("http://localhost:3000/employees", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(userData));

        System.out.println("postResponse.status() = " + postResponse.status());
        Assert.assertEquals(postResponse.status(), 201);

//        System.out.println("------------Jackson Databind Serialization-----------------");
//        UserData userData2 = new UserData(77, "mosu","m", "mosu@gmail.com");
//        ObjectMapper objectMapper2 = new ObjectMapper();
//        String userData4 = objectMapper.writeValueAsString(userData2);

        System.out.println("------------Jackson Databind  De-Serialization-----------------");
        ObjectMapper objectMapper = new ObjectMapper();
        UserDataUsingRecord jsonResponseBody = objectMapper.readValue(postResponse.body(), UserDataUsingRecord.class);
        Assert.assertEquals(jsonResponseBody.id(),userData.id());
        Assert.assertEquals(jsonResponseBody.first_name(),userData.first_name());
        Assert.assertEquals(jsonResponseBody.last_name(),userData.last_name());
        Assert.assertEquals(jsonResponseBody.email(),userData.email());


//    Get call:

        APIResponse apiGetResponse = apiRequestContext.get("http://localhost:3000/employees/" + jsonResponseBody.id());
        System.out.println("apiGetResponse.status() = " + apiGetResponse.status());
        Assert.assertEquals(apiGetResponse.status(),200);
        System.out.println("Get Request Response: " + apiGetResponse.text());

        playwright.close();

    }
}
