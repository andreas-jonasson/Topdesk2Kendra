package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration
{
    public final String topdesk_username;
    public final String topdesk_password;
    public final String topdesk_domain;
    public final String topdesk_endpoint_knowledgeItems;

    public Configuration(String topdesk_username, String topdesk_password, String topdesk_domain, String topdesk_endpoint_knowledgeItems) {
        this.topdesk_username = topdesk_username;
        this.topdesk_password = topdesk_password;
        this.topdesk_domain = topdesk_domain;
        this.topdesk_endpoint_knowledgeItems = topdesk_endpoint_knowledgeItems;
    }

    public static Configuration getInstance()
    {
        Gson gson = new GsonBuilder().create();
        FileReader fileReader = null;

        try
        {
            fileReader = new FileReader("./config.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        assert fileReader != null;
        return gson.fromJson(fileReader, Configuration.class);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "topdesk_username='" + topdesk_username + '\'' +
                ", topdesk_password='" + topdesk_password + '\'' +
                ", topdesk_domain='" + topdesk_domain + '\'' +
                ", topdesk_endpoint_knowledgeItems='" + topdesk_endpoint_knowledgeItems + '\'' +
                '}';
    }
}
