package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import userdata.UserDataUsingRecord;

import java.io.IOException;

public class PutRequest {

    public static void main(String[] args) throws IOException {

        UserDataUsingRecord userDataUsingRecord =
                new UserDataUsingRecord(63,"mosu","m","mosu@gmail.com");

        //Post Request:  create resource
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse apiPostResponse = apiRequestContext.post("http://localhost:3000/employees",
                RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(userDataUsingRecord));

        System.out.println("apiPostResponse.status() = " + apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(),201);

        ObjectMapper objectMapper = new ObjectMapper();
        UserDataUsingRecord actualUser = objectMapper.readValue(apiPostResponse.body(), UserDataUsingRecord.class);
        Assert.assertEquals(actualUser.id(),userDataUsingRecord.id());
        Assert.assertEquals(actualUser.first_name(),userDataUsingRecord.first_name());
        Assert.assertEquals(actualUser.last_name(),userDataUsingRecord.last_name());
        Assert.assertEquals(actualUser.email(),userDataUsingRecord.email());

        //Put Request: update resource

        UserDataUsingRecord updatedUserData =
                new UserDataUsingRecord(64,"mosu","m","mosuautomation@gmail.com");

        APIResponse putApiResponse = apiRequestContext.put("http://localhost:3000/employees/" + actualUser.id(),
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(updatedUserData));

        UserDataUsingRecord jsonPutResponse = objectMapper.readValue(putApiResponse.body(), UserDataUsingRecord.class);

        System.out.println("putApiResponse.status() = " + putApiResponse.status());
        Assert.assertEquals(putApiResponse.status(),200);
        Assert.assertEquals(jsonPutResponse.email(),updatedUserData.email());


        //Get Call:


        APIResponse apiGetResponse = apiRequestContext.get("http://localhost:3000/employees/" + actualUser.id());
        Assert.assertEquals(apiGetResponse.status(),200);
        System.out.println("apiGetResponse.status() = " + apiGetResponse.status());
        System.out.println("apiGetResponse.text() = " + apiGetResponse.text());


        playwright.close();


    }
}
