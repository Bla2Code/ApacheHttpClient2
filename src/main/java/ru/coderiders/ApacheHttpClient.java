package ru.coderiders;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApacheHttpClient {

    final static String API_KEY = "C8Wqy2FysAowMwqUrjPHyJ11caLEpYzwSdLPKLaJ";

    public static void main2(String[] args) throws IOException {
        System.out.println("HI IT'S APACHE HTTP CLIENT GET");

        final var closeableHttpClient = HttpClients.createDefault();

        final HttpUriRequest httpGet = new HttpGet("https://api.challonge.com/v1/tournaments/10634350.json?api_key=" + API_KEY);
        try (var response1 = closeableHttpClient.execute(httpGet)) {
            final HttpEntity entity1 = response1.getEntity();
            System.out.println(EntityUtils.toString(entity1));
        }

    }

    public static void main(String[] args) throws IOException {
        System.out.println("HI IT'S APACHE HTTP CLIENT POST");

        final var closeableHttpClient = HttpClients.createDefault();

        final var httpPost = new HttpPost("https://api.challonge.com/v1/tournaments.json?api_key=" + API_KEY);

        var jsonInputString = "{\n" +
                "  \"tournament\": {\n" +
                "    \"name\": \"Tournament #6\"\n" +
                "  }\n" +
                "}";

        httpPost.setEntity(new StringEntity(jsonInputString));
        httpPost.setHeader("Content-Type", "application/json; utf-8");

        try (var response = closeableHttpClient.execute(httpPost)) {
            final var httpEntity = response.getEntity();
            System.out.println(EntityUtils.toString(httpEntity));
        }

        closeableHttpClient.close();
    }
}
