package se.chalmers.topdesk.model.kendra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.chalmers.topdesk.model.topdesk.KnowledgeItem;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class MetadataTest
{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Metadata metadata = null;

    @BeforeEach
    void setup()
    {
        try
        {
            FileReader fileReader = new FileReader("./src/test/resources/KendraMetadata.json");
            metadata = gson.fromJson(fileReader, Metadata.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canParseExampleJson()
    {
        assertNotNull(metadata, "Failed to parse KendraMetadata.json");
        assertInstanceOf(String.class, metadata.DocumentId);
        assertInstanceOf(String.class, metadata.Attributes._category);
        assertInstanceOf(Integer.class, metadata.Attributes._view_count);
        assertInstanceOf(String.class, metadata.AccessControlList[0].Name);
        assertInstanceOf(String.class, metadata.Title);
        assertInstanceOf(String.class, metadata.ContentType);
    }
}