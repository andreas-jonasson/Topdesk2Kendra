package se.chalmers.topdesk.model.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.chalmers.topdesk.model.kendra.Metadata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.ZonedDateTime;

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

    @Test
    public void getCreationDate_returnsString()
    {
        assertNotNull((knowledgeItem.getCreationDate()));
        assertInstanceOf(String.class, knowledgeItem.getCreationDate());
    }

    @Test
    public void getCreationDate_returnsISO8601Date()
    {
        assertDoesNotThrow(() -> { ZonedDateTime.parse(knowledgeItem.getCreationDate());} , "Failed to parse string.");
    }

    @Test
    public void getModificationDate_returnsString()
    {
        assertNotNull((knowledgeItem.getModificationDate()));
        assertInstanceOf(String.class, knowledgeItem.getModificationDate());
    }

    @Test
    public void getModificationDate_returnsISO8601Date()
    {
        assertDoesNotThrow(() -> { ZonedDateTime.parse(knowledgeItem.getModificationDate());} , "Failed to parse string.");
    }

    @Test
    public void getDataSource_returnsTopDeskString()
    {
        assertEquals("TopDesk", knowledgeItem.getDataSource());
    }

    @Test
    public void getDocumentBody_returnsString()
    {
        assertNotNull((knowledgeItem.getDocumentBody()));
        assertInstanceOf(String.class, knowledgeItem.getDocumentBody());
    }

    @Test
    public void getDocumentTitle_returnsString()
    {
        assertNotNull((knowledgeItem.getDocumentTitle()));
        assertInstanceOf(String.class, knowledgeItem.getDocumentTitle());
    }

    @Test
    public void getDocumentId_returnsString()
    {
        assertNotNull((knowledgeItem.getDocumentId()));
        assertInstanceOf(String.class, knowledgeItem.getDocumentId());
    }

    @Test
    public void getDocumentType_returnStringHtml()
    {
        assertInstanceOf(String.class, knowledgeItem.getDocumentType());
        assertEquals("HTML", knowledgeItem.getDocumentType());
    }

    @Test
    public void getLanguageCode_returnString()
    {
        assertNotNull((knowledgeItem.getLanguageCode()));
        assertInstanceOf(String.class, knowledgeItem.getLanguageCode());
    }

    @Test
    public void getAsHtml_returnString()
    {
        assertNotNull((knowledgeItem.getAsHtml()));
        assertInstanceOf(String.class, knowledgeItem.getAsHtml());
    }

    @Test
    public void getAsHtml_looksLikeHtml()
    {
        assertTrue(knowledgeItem.getAsHtml().startsWith("<!DOCTYPE html>"));
        assertTrue(knowledgeItem.getAsHtml().contains("<body>"));
    }

    @Test
    public void getMetadataJson_returnString()
    {
        assertNotNull((knowledgeItem.getMetadataJson()));
        assertInstanceOf(String.class, knowledgeItem.getMetadataJson());
    }

    @Test
    public void getMetadataJson_canBeTurnedIntoMetadataObject()
    {
        Metadata metadata = gson.fromJson(knowledgeItem.getMetadataJson(), Metadata.class);
        assertNotNull(metadata);
    }

    @Test
    public void equals_returnsTrueWhenEqual()
    {
        try
        {
            FileReader fileReader = new FileReader("./src/test/resources/KnowledgeItem.json");
            KnowledgeItem knowledgeItem2 = gson.fromJson(fileReader, KnowledgeItem.class);
            assertEquals(knowledgeItem2, knowledgeItem);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void equals_returnFalseWhenNotEqual()
    {
        try
        {
            FileReader fileReader = new FileReader("./src/test/resources/KnowledgeItem.json");
            KnowledgeItem knowledgeItem2 = gson.fromJson(fileReader, KnowledgeItem.class);
            knowledgeItem2.translation.language = "Something else";
            assertNotEquals(knowledgeItem2, knowledgeItem);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}