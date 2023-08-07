package api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import userdata.UserDataUsingLombok;

import java.io.IOException;

public class PostRequestUsingLombok {

    public static void main(String[] args) throws IOException {

        UserDataUsingLombok userData = UserDataUsingLombok.builder()
                .id(67)
                .first_name("Mosu")
                .last_name("M")
                .email("mosu@gmail.com")
                .build();

        //Post Call:

        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse postApiResponse = apiRequestContext.post("http://localhost:3000/employees", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(userData));

        Assert.assertEquals(postApiResponse.status(),201);
        System.out.println("postApiResponse.status() = " + postApiResponse.status());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPostResponse = objectMapper.readTree(postApiResponse.body());
        String userID = jsonPostResponse.get("id").asText();

        UserDataUsingLombok jsonResponseBody = objectMapper.readValue(postApiResponse.body(), UserDataUsingLombok.class);
        Assert.assertEquals(jsonResponseBody.getId(), userData.getId());
        Assert.assertEquals(jsonResponseBody.getFirst_name(), userData.getFirst_name());
        Assert.assertEquals(jsonResponseBody.getLast_name(), userData.getLast_name());
        Assert.assertEquals(jsonResponseBody.getEmail(), userData.getEmail());


        //get call:

        APIResponse getApiResponse = apiRequestContext.get("http://localhost:3000/employees/" + userID);
        Assert.assertEquals(getApiResponse.status(),200);
        System.out.println("getApiResponse.status() = " + getApiResponse.status());

        playwright.close();

    }
}
