package se.chalmers.topdesk.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class KnowledgeItemTest
{
    @Test
    public void canParseExampleJson()
    {
        Gson gson = new GsonBuilder().create();
        KnowledgeItem knowledgeItem = null;

        try
        {
            FileReader fileReader = new FileReader("./src/test/resources/KnowledgeItem.json");
            knowledgeItem = gson.fromJson(fileReader, KnowledgeItem.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertNotNull(knowledgeItem, "Failed to parse KnowledgeItem.json");
        assertInstanceOf(String.class, knowledgeItem.translation.content.content);
    }
}