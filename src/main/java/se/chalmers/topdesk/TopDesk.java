package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import se.chalmers.topdesk.model.GetKnowledgeItemRequest;
import se.chalmers.topdesk.model.KnowledgeItem;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TopDesk
{
    public List<KnowledgeItem> getAllKnowledgeItems(String endpoint, String user, String password) throws IOException
    {
        //TODO Need to consider start, page_size and fields...
        List<KnowledgeItem> knowledgeItems = new ArrayList<>();
        HttpResponse response = getRequestWithBasicAuth(endpoint, user, password);
        InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
        Gson gson = new GsonBuilder().create();
        GetKnowledgeItemRequest result = gson.fromJson(reader, GetKnowledgeItemRequest.class);

        return result.item;
    }

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
