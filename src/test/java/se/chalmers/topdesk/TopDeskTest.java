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
    private List<KnowledgeItem> knowledgeItems;

    @BeforeEach
    void setUp()
    {
        Configuration configuration = Configuration.getInstance();
        topDesk = new TopDesk();
        knowledgeItemsEndpoint = configuration.topdesk_endpoint_knowledgeItems;
        user = configuration.topdesk_credentials.topdesk_username;
        password = configuration.topdesk_credentials.topdesk_password;
        knowledgeItems = null;
        try
        {
            knowledgeItems = topDesk.getAllKnowledgeItems(knowledgeItemsEndpoint, user, password);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestWithBasicAuth_returnsHttpResponse()
    {
        try
        {
            assertInstanceOf(HttpResponse.class, topDesk.getRequestWithBasicAuth(knowledgeItemsEndpoint, user, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listAllKnowledgeItems_returnsAListOfKnowledgeItem()
    {
        if (!password.equals("password")) // Only run this test when you have valid credentials.
        {
            assertNotNull(knowledgeItems, "Received null.");
            assertFalse(knowledgeItems.isEmpty(), "Received en empty List, might be ok if there are no knowledge items.");
            assertNotNull(knowledgeItems.get(0), "Received a list with null items.");
            assertInstanceOf(KnowledgeItem.class, knowledgeItems.get(0), "Received a list of the wrong type.");

            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //System.out.println(gson.toJson(items));
        }
    }

    @Test
    public void listAllKnowledgeItems_itemsContainParent()
    {
        if (knowledgeItems != null)
        {
            assertNotNull(knowledgeItems.get(1).parent.id);
            assertInstanceOf(String.class, knowledgeItems.get(1).parent.id);
        }
    }

    @Test
    public void listAllKnowledgeItems_itemsContainContent()
    {
        if (knowledgeItems != null)
        {
            assertNotNull(knowledgeItems.get(0).translation.content.content);
            assertInstanceOf(String.class, knowledgeItems.get(0).translation.content.content);
        }
    }

    @Test
    public void listAllKnowledgeItems_itemsContainLanguage()
    {
        if (knowledgeItems != null)
        {
            assertNotNull(knowledgeItems.get(0).translation.language);
            assertInstanceOf(String.class, knowledgeItems.get(0).translation.language);
        }
    }

    @Test
    public void listAllKnowledgeItems_itemsContainKeyword()
    {
        if (knowledgeItems != null)
        {
            assertNotNull(knowledgeItems.get(0).translation.content.keywords);
            assertInstanceOf(String.class, knowledgeItems.get(0).translation.content.keywords);
        }
    }

    @Test
    public void getContent_hasNoQuoting()
    {
        for (KnowledgeItem i : knowledgeItems)
            System.out.println(i.getContent());
    }
}