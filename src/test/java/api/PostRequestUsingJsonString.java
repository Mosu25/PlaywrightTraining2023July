package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostRequestUsingJsonString {

    @Test
    public void postCallWithJsonString() throws IOException {

        String jsonBody =
                """
                                {
                                  "id": 83,
                                  "first_name": "Sebastian",
                                  "last_name": "Eschweiler",
                                  "email": "sebastian@codingthesmartway.com"
                                }
                                
                        """;

        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse postResponse = apiRequestContext.post("http://localhost:3000/employees",
                                                RequestOptions.create()
                                                    .setHeader("Content-Type", "application/json")
//                                                    .setHeader("Authorization", "Bearer f4bd")
                                                    .setData(jsonBody));

        System.out.println("postResponse.status() = " + postResponse.status());
        System.out.println("postResponse.statusText() = " + postResponse.statusText());
        System.out.println("postResponse.url() = " + postResponse.url());
        System.out.println("postResponse.text() = " + postResponse.text());

        Assert.assertEquals(postResponse.status(),201);
        Assert.assertEquals(postResponse.statusText(),"Created");

        //Using GSON inbuilt Playwright (transient dependency)
        byte[] body = postResponse.body();
        String jsonResponse = new String(body);
        System.out.println("GSON Response Body = " + new Gson().fromJson(jsonResponse, JsonObject.class)); //complete Json String
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        String userID = jsonObject.get("id").getAsString();
        System.out.println("userID = " + userID);



//        Using Jackson databind
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postJsonResponse = objectMapper.readTree(postResponse.body());
        System.out.println(postJsonResponse.toPrettyString());
        String userID1 = postJsonResponse.get("id").asText();
        System.out.println("userID = " + userID1);

        //GetCall

        APIResponse getApiResponse = apiRequestContext.get("http://localhost:3000/employees/" + userID);
//                RequestOptions.create()
//                        .setHeader("Authorization", "Bearer f4b"));
        System.out.println("Get ApiResponse Text = " + getApiResponse.text());
        Assert.assertEquals(getApiResponse.status(),200);
        Assert.assertEquals(getApiResponse.statusText(),"OK");
        Assert.assertTrue(getApiResponse.text().contains(userID));

        playwright.close();
    }


    
    
}
