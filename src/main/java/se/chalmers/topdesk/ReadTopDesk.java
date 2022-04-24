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

        File outputDir = new File(configuration.topdesk_output_dir);
        if (!outputDir.exists())
            //noinspection ResultOfMethodCallIgnored
            outputDir.mkdirs();

        File metadataDir = new File(configuration.topdesk_output_dir + "metadata/");
        if (!metadataDir.exists())
            //noinspection ResultOfMethodCallIgnored
            metadataDir.mkdirs();

        try
        {
            List<KnowledgeItem> knowledgeItems = TopDesk.getAllKnowledgeItems(configuration);

            for (KnowledgeItem item : knowledgeItems)
            {
                File htmlFile = new File(configuration.topdesk_output_dir + item.getHtmlFileName());
                File jsonFile = new File(configuration.topdesk_output_dir + "metadata/" + item.getMetadataFileName());

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
