package se.chalmers.topdesk;

import org.apache.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
}