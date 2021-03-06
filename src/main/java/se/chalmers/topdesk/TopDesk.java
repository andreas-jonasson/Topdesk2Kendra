package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import se.chalmers.topdesk.model.topdesk.GetKnowledgeItemRequest;
import se.chalmers.topdesk.model.topdesk.KnowledgeItem;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TopDesk
{
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String ONLY_ACTIVE_PARAMETER = "query=visibility.sspVisibility==VISIBLE";
    private static final String FIELDS_PARAMETER = "fields=parent,visibility,status,language,title,content,keywords,creationDate,modificationDate";

    public static List<KnowledgeItem> getAllKnowledgeItems(Configuration configuration) throws IOException
    {
        String endpoint = configuration.topdesk_endpoint_knowledgeItems;
        String user = configuration.topdesk_credentials.topdesk_username;
        String password = configuration.topdesk_credentials.topdesk_password;
        String[] languages = configuration.topdesk_languages_to_export;
        ArrayList<KnowledgeItem> knowledgeItems = new ArrayList<>();

        for (String currentLanguage: languages)
        {
            String languageParameter = "language=" + currentLanguage;
            int start = 0;
            int page_size = 100;

            while (true) {
                String pageParameter = "start=" + start + "&page_size=" + page_size;
                String requestUrl = concatenateGetParameters(endpoint, pageParameter, ONLY_ACTIVE_PARAMETER, FIELDS_PARAMETER, languageParameter);
                HttpResponse response = getRequestWithBasicAuth(requestUrl, user, password);
                GetKnowledgeItemRequest responseKnowledgeItems = parseKnowledgeItemRequest(response);

                knowledgeItems.addAll(responseKnowledgeItems.item);

                if (responseKnowledgeItems.item.size() < page_size)
                    break;

                start += page_size;

                if (start > 5000) // Don't go on forever... throw exception later.
                    break;
            }
        }

        return knowledgeItems;
    }

    private static String concatenateGetParameters(String endpointUrl, String... parameters)
    {
        if (parameters.length == 0)
            return endpointUrl;

        StringBuilder result = new StringBuilder();
        result.append(endpointUrl);
        result.append("?");

        boolean first = true;
        for (String p : parameters)
        {
            if (first)
            {
                result.append(p);
                first = false;
            }
            else
            {
                result.append("&");
                result.append(p);
            }
        }

        return result.toString();
    }

    private static GetKnowledgeItemRequest parseKnowledgeItemRequest(HttpResponse response) throws IOException
    {
        InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
        return gson.fromJson(reader, GetKnowledgeItemRequest.class);
    }

    static HttpResponse getRequestWithBasicAuth(String requestURL, String user, String password) throws IOException
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
