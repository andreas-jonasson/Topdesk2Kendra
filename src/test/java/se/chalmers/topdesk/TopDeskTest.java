package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.chalmers.topdesk.model.GetKnowledgeItemRequest;
import se.chalmers.topdesk.model.KnowledgeItem;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopDeskTest
{
    private TopDesk topDesk;
    private String knowledgeItemsEndpoint;
    private String user;
    private String password;

    @BeforeEach
    void setUp()
    {
        Configuration configuration = Configuration.getInstance();
        topDesk = new TopDesk();
        knowledgeItemsEndpoint = configuration.topdesk_endpoint_knowledgeItems;
        user = configuration.topdesk_credentials.topdesk_username;
        password = configuration.topdesk_credentials.topdesk_password;
    }

    @Test
    public void getRequestWithBasicAuth_returnsHttpResponse()
    {
        try {
            assertInstanceOf(HttpResponse.class, topDesk.getRequestWithBasicAuth(knowledgeItemsEndpoint, user, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listAllKnowledgeItems_returnsAListOfKnowledgeItem()
    {
        List<KnowledgeItem> items = null;

        try {
            items = topDesk.getAllKnowledgeItems(knowledgeItemsEndpoint, user, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!password.equals("password")) // Only run this test when you have valid credentials.
        {
            assertNotNull(items, "Received null.");
            assertFalse(items.isEmpty(), "Received en empty List, might be ok if there are no knowledge items.");
            assertNotNull(items.get(0), "Received a list with null items.");
            assertInstanceOf(KnowledgeItem.class, items.get(0), "Received a list of the wrong type.");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(items));
        }
    }

}