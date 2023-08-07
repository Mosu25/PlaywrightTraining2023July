package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import userdata.UserDataUsingRecord;

import java.io.IOException;

public class DeleteRequest {

    public static void main(String[] args) throws IOException {

        UserDataUsingRecord userDataUsingRecord =
                new UserDataUsingRecord(62,"mosu","m","mosu@gmail.com");

        //Post Request:  create resource
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequestContext = playwright.request().newContext();
        APIResponse apiPostResponse = apiRequestContext.post("http://localhost:3000/employees",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(userDataUsingRecord));

        System.out.println("apiPostResponse.status() = " + apiPostResponse.status());
        System.out.println("apiPostResponse.url() = " + apiPostResponse.url());
        Assert.assertEquals(apiPostResponse.status(),201);

        ObjectMapper objectMapper = new ObjectMapper();
        UserDataUsingRecord actualUser = objectMapper.readValue(apiPostResponse.body(), UserDataUsingRecord.class);
        Assert.assertEquals(actualUser.id(),userDataUsingRecord.id());
        Assert.assertEquals(actualUser.first_name(),userDataUsingRecord.first_name());
        Assert.assertEquals(actualUser.last_name(),userDataUsingRecord.last_name());
        Assert.assertEquals(actualUser.email(),userDataUsingRecord.email());

//        DeleteRequest:

        APIResponse deleteApiResponse = apiRequestContext.delete("http://localhost:3000/employees/" + actualUser.id());
        System.out.println("deleteApiResponse.status() = " + deleteApiResponse.status());
        System.out.println("deleteApiResponse.url() = " + deleteApiResponse.url());
        Assert.assertEquals(deleteApiResponse.status(),200);

//        GetRequest:

        APIResponse apiGetResponse = apiRequestContext.get("http://localhost:3000/employees/"+ actualUser.id());
        System.out.println("apiGetResponse.url() = " + apiGetResponse.url());
        System.out.println("apiGetResponse.status() = " + apiGetResponse.status());

        Assert.assertEquals(apiGetResponse.status(),404);

        playwright.close();

    }
}
