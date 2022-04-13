package se.chalmers.topdesk;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TopDesk
{
    HttpResponse getRequestWithBasicAuth(String requestURL, String user, String password) throws IOException
    {
        HttpGet request = new HttpGet(requestURL);
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpClient client = HttpClientBuilder.create().build();

        return client.execute(request);
    }
}
