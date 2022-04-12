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
    private final Configuration configuration = Configuration.getInstance();
    private final String endpoint = configuration.topdesk_endpoint_knowledgeItems;
    private final String user = configuration.topdesk_credentials.topdesk_username;
    private final String password = configuration.topdesk_credentials.topdesk_password;

    void getRequest() throws IOException
    {
        HttpGet request = new HttpGet(endpoint);
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        System.out.println(response.getStatusLine());
    }
}
