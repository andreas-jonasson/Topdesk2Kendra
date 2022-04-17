package se.chalmers.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration
{
    public static final String DEFAULT_CREDENTIALS = "./default-credentials.json";
    public Credentials topdesk_credentials;
    public final String topdesk_credentials_file;
    public final String topdesk_endpoint_knowledgeItems;
    public final String topdesk_default_language;

    public Configuration(Credentials topdesk_credentials, String topdesk_credentials_file, String topdesk_endpoint_knowledgeItems, String topdesk_default_language)
    {
        this.topdesk_credentials = topdesk_credentials;
        this.topdesk_credentials_file = topdesk_credentials_file;
        this.topdesk_endpoint_knowledgeItems = topdesk_endpoint_knowledgeItems;
        this.topdesk_default_language = topdesk_default_language;
    }


    public static Configuration getInstance()
    {
        Gson gson = new GsonBuilder().create();
        FileReader fileReader;
        Configuration configuration = null;

        try
        {
            fileReader = new FileReader("./config.json");
            configuration = gson.fromJson(fileReader, Configuration.class);

            if (configuration.topdesk_credentials_file == null || !(new File(configuration.topdesk_credentials_file).isFile()))
                fileReader = new FileReader(DEFAULT_CREDENTIALS);
            else
                fileReader = new FileReader(configuration.topdesk_credentials_file);

            configuration.topdesk_credentials = gson.fromJson(fileReader, Credentials.class);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return configuration;
    }

    @Override
    public String toString() {
        return "Configuration = {" +
                "topdesk_credentials=" + topdesk_credentials +
                ", topdesk_credentials_file='" + topdesk_credentials_file + '\'' +
                ", topdesk_endpoint_knowledgeItems='" + topdesk_endpoint_knowledgeItems + '\'' +
                '}';
    }
}
