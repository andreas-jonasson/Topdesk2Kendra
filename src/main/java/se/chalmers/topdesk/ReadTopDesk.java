package se.chalmers.topdesk;

import se.chalmers.topdesk.model.topdesk.KnowledgeItem;

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
                FileWriter fileWriter = new FileWriter("./output/" + item.getDocumentId() + ".html");
                fileWriter.write(item.getAsHtml());
                fileWriter.flush();
                fileWriter.close();

                fileWriter = new FileWriter("./output/" + item.getDocumentId() + "metadata.json");
                fileWriter.write(item.getMetadataJson());
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
