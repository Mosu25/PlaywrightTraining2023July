package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import userdata.UserDataUsingPojo;

import java.io.IOException;

public class PostRequestUsingPojo {
    public static void main(String[] args) throws IOException {

        UserDataUsingPojo userData = new UserDataUsingPojo();
        userData.setId(65);
        userData.setFirst_name("mosu");
        userData.setLast_name("m");
        userData.setEmail("mosu@gmail.com");

        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse apiPostResponse = apiRequestContext.post("http://localhost:3000/employees", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(userData));

        System.out.println("apiPostResponse.status() = " + apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);

        ObjectMapper objectMapper = new ObjectMapper();
        UserDataUsingPojo jsonPostResponse = objectMapper.readValue(apiPostResponse.body(), UserDataUsingPojo.class);
        Assert.assertEquals(jsonPostResponse.getId(), userData.getId());
        Assert.assertEquals(jsonPostResponse.getFirst_name(), userData.getFirst_name());
        Assert.assertEquals(jsonPostResponse.getLast_name(), userData.getLast_name());
        Assert.assertEquals(jsonPostResponse.getEmail(), userData.getEmail());

        System.out.println("jsonPostResponse.getId() = " + jsonPostResponse.getId());


//        getCall:
        APIResponse apiGetResponse = apiRequestContext.get("http://localhost:3000/employees/" + jsonPostResponse.getId());
        Assert.assertEquals(apiGetResponse.status(), 200);
        System.out.println("Get Request Response: " + apiGetResponse.text());

        playwright.close();

    }
}
