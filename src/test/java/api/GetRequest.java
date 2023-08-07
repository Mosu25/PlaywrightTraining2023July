package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetRequest {

    public static void main(String[] args) throws IOException {

//        Playwright playwright = Playwright.create();
//        APIRequest request = playwright.request();
//        APIRequestContext apiRequestContext = request.newContext();
//        APIResponse apiResponse = apiRequestContext.get("http://localhost:3000/employees");
//        System.out.println("Status: " +apiResponse.status());
//        System.out.println("Description: "+ apiResponse.statusText());
//        System.out.println("Body: "+apiResponse.text());
//        System.out.println("URL: " + apiResponse.url());
//
//        playwright.close();


//        Playwright playwright = Playwright.create();
//        APIResponse apiResponse = playwright.request().newContext().get("http://localhost:3000/employees");
//        System.out.println("apiResponse.status() = " + apiResponse.status());
//        System.out.println("apiResponse.statusText() = " + apiResponse.statusText());
//        System.out.println("apiResponse.url() = " + apiResponse.url());


//        Playwright playwright = Playwright.create();
//        APIResponse apiResponse = playwright.request().newContext().get("http://localhost:3000/employees/1");
//        System.out.println("apiResponse.status() = " + apiResponse.status());
//        System.out.println("apiResponse.statusText() = " + apiResponse.statusText());
//        System.out.println("apiResponse.url() = " + apiResponse.url());
//        System.out.println("apiResponse.text() = " + apiResponse.text());
//        System.out.println(apiResponse.headers());

        Playwright playwright = Playwright.create();
        APIResponse apiResponse = playwright.request().newContext().get("http://localhost:3000/employees",
                RequestOptions.create()
                                .setQueryParam("id", "2")
                                .setQueryParam("first_name", "Steve"));

//        System.out.println("apiResponse.status() = " + apiResponse.status());
//        System.out.println("apiResponse.statusText() = " + apiResponse.statusText());
//        System.out.println("apiResponse.text() = " + apiResponse.text());

        byte[] body = apiResponse.body();

//        Serialization Object to JsonObject

//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonResponse = objectMapper.readTree(body);
//        System.out.println("jsonResponse = " + jsonResponse.toPrettyString());


//        Headers:  Map
//        Map<String, String> headers = apiResponse.headers();
//        headers.forEach((k,v)-> System.out.println("Keys: " +k+" Values: " +v));
//        System.out.println("Header size = " + headers.size());
//        Assert.assertEquals(headers.get("content-type"),"application/json; charset=utf-8");

//        Headers: List

        List<HttpHeader> headerList = apiResponse.headersArray();
        for (HttpHeader header:headerList ) {
            System.out.println(header.name +":" + header.value);
        }

        playwright.close();
    }









}
