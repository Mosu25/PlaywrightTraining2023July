package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PostRequestUsingJsonFile {


    public static void main(String[] args) throws IOException {


        //read the json file

        File file = new File("./src/test/resources/Data.json.");
        byte[] allBytes = Files.readAllBytes(file.toPath());

        // post call: create user
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse apiPostResponse = apiRequestContext.post("http://localhost:3000/employees", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(allBytes)
        );
        System.out.println("apiPostResponse.status() = " + apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(),201);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiPostResponse.body());
        String userID = jsonResponse.get("id").asText();
        System.out.println("userID = " + userID);

          //get call:

        APIResponse apiResponse = apiRequestContext.get("http://localhost:3000/employees/" + userID);
        System.out.println("Get apiResponse.status() = " + apiResponse.status());
        Assert.assertEquals(apiResponse.status(),200);

    }
}
