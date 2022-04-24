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

        File outputDir = new File("./topdesk/");
        if (!outputDir.exists())
            //noinspection ResultOfMethodCallIgnored
            outputDir.mkdirs();

        File metadataDir = new File("./topdesk/metadata/");
        if (!metadataDir.exists())
            //noinspection ResultOfMethodCallIgnored
            metadataDir.mkdirs();

        try
        {
            List<KnowledgeItem> knowledgeItems = TopDesk.getAllKnowledgeItems(configuration);

            for (KnowledgeItem item : knowledgeItems)
            {
                File htmlFile = new File("./topdesk/" + item.getHtmlFileName());
                File jsonFile = new File("./topdesk/metadata/" + item.getMetadataFileName());

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
