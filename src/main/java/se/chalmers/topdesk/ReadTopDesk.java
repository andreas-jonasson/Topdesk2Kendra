package se.chalmers.topdesk;

import se.chalmers.topdesk.model.topdesk.KnowledgeItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReadTopDesk
{
    public static void main(final String[] args)
    {
        Configuration configuration = Configuration.getInstance();

        try
        {
            List<KnowledgeItem> knowledgeItems = TopDesk.getAllKnowledgeItems(configuration.topdesk_endpoint_knowledgeItems,
                    configuration.topdesk_credentials.topdesk_username,
                    configuration.topdesk_credentials.topdesk_password);

            for (KnowledgeItem item : knowledgeItems)
            {
                File htmlFile = new File("./topdesk/" + item.getHtmlFileName());
                File jsonFile = new File("./topdesk/" + item.getMetadataFileName());

                if (htmlFile.isFile())
                    htmlFile.delete();

                if (jsonFile.exists())
                    jsonFile.delete();

                FileWriter fileWriter = new FileWriter(htmlFile);
                fileWriter.write(item.getAsHtml());
                fileWriter.flush();
                fileWriter.close();

                fileWriter = new FileWriter(jsonFile);
                fileWriter.write(item.getMetadataJson());
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
