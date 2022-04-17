package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.chalmers.topdesk.model.KnowledgeItem;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopDeskTest
{
    private static TopDesk topDesk;
    private static String knowledgeItemsEndpoint;
    private static String user;
    private static String password;
    private static List<KnowledgeItem> knowledgeItems;

    @BeforeAll
    static void setUpClass()
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
    public void listAllKnowledgeItems_itemsContainKeywordString()
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                if (item.translation.content.keywords != null)
                {
                    assertInstanceOf(String.class, item.translation.content.keywords);
                }
            }
        }
    }

    @Test void listAllKnowledgeItems_getCreationDateIsISO8601()
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                if (item.getCreationDate() != null)
                {
                    assertDoesNotThrow(() -> { ZonedDateTime.parse(item.getCreationDate());} , "Failed to parse string.");
                    System.out.println(item.getCreationDate());
                }
            }
        }
    }

    @Test void listAllKnowledgeItems_getModificationDateIsISO8601() // I don't get any of these in my production server...
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                //Gson gson = new GsonBuilder().setPrettyPrinting().create();
                //System.out.println(gson.toJson(item));
                if (item.getModificationDate() != null)
                {
                    assertDoesNotThrow(() -> { ZonedDateTime.parse(item.getModificationDate());} , "Failed to parse string.");
                }
            }
        }
    }

    @Test
    public void getDocumentBody_returnsString()
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                if (item.getModificationDate() != null)
                {
                    assertNotNull((item.getDocumentBody()));
                    assertInstanceOf(String.class, item.getDocumentBody());
                }
            }
        }
    }

    @Test
    public void getDocumentTitle_returnsString()
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                if (item.getModificationDate() != null)
                {
                    assertNotNull((item.getDocumentTitle()));
                    assertInstanceOf(String.class, item.getDocumentTitle());
                }
            }
        }
    }

    @Test
    public void getDocumentId_returnsString()
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                if (item.getModificationDate() != null)
                {
                    assertNotNull((item.getDocumentId()));
                    assertInstanceOf(String.class, item.getDocumentId());
                }
            }
        }
    }

    @Test
    public void getLanguageCode_returnsString()
    {
        if (knowledgeItems != null)
        {
            for (KnowledgeItem item: knowledgeItems)
            {
                assertNotNull((item.getLanguageCode()));
                assertInstanceOf(String.class, item.getLanguageCode());
            }
        }
    }
}