package se.chalmers.topdesk.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class KnowledgeItemTest
{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private KnowledgeItem knowledgeItem = null;

    @BeforeEach
    void setup()
    {
        try
        {
            FileReader fileReader = new FileReader("./src/test/resources/KnowledgeItem.json");
            knowledgeItem = gson.fromJson(fileReader, KnowledgeItem.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canParseExampleJson()
    {
        assertNotNull(knowledgeItem, "Failed to parse KnowledgeItem.json");
        assertInstanceOf(String.class, knowledgeItem.translation.content.content);
    }

    @Test
    public void getContent_returnsString()
    {
        assertNotNull(knowledgeItem.getContent());
        assertInstanceOf(String.class, knowledgeItem.getContent());
    }
}