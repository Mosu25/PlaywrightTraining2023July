package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostRequestUsingMap {

    public static void main(String[] args) throws IOException {


        Map<String,Object> userData = new HashMap<>();
        userData.put("id",78);
        userData.put("first_name", "Mosu");
        userData.put("last_name","M");
        userData.put("email","mosu@gmail.com");

        // Post call to create the resources
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse postApiResponse = apiRequestContext.post("http://localhost:3000/employees", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(userData));

        System.out.println("postApiResponse.status() = " + postApiResponse.status());
        Assert.assertEquals(postApiResponse.status(),201);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(postApiResponse.body());
        String userID = jsonResponse.get("id").asText();
        System.out.println("userID = " + userID);

        //get call: to validate

        APIResponse getApiResponse = apiRequestContext.get("http://localhost:3000/employees/" + userID);
        System.out.println("getApiResponse.status() = " + getApiResponse.status());
        Assert.assertEquals(getApiResponse.status(),200);

        playwright.close();

    }
}
