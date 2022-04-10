package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration
{
    public String topdesk_username;
    public String topdesk_password;
    public String topdesk_domain;
    public String topdesk_endpoint;

    public static Configuration getInstance()
    {
        Gson gson = new GsonBuilder().create();
        FileReader fileReader = null;
        Configuration configuration;

        try
        {
            fileReader = new FileReader("config.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        assert fileReader != null;
        configuration =  gson.fromJson(fileReader, Configuration.class);

        if (configuration.topdesk_endpoint == null)
            configuration.topdesk_endpoint = configuration.topdesk_domain + "/tas/api/";

        return configuration;
    }

    @Override
    public String toString()
    {
        return "Configuration{" +
                "topdesk_username='" + topdesk_username + '\'' +
                ", topdesk_password='" + topdesk_password + '\'' +
                ", topdesk_domain='" + topdesk_domain + '\'' +
                ", topdesk_endpoint='" + topdesk_endpoint + '\'' +
                '}';
    }
}
