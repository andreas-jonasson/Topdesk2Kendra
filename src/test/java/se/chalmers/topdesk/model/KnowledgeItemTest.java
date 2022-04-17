package se.chalmers.topdesk.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    /*
        Mappings from TopDesk to Kendra metadata:
        translation.creator.name 	->  _authors (String list) *
        hmmm... Depends on domain?      _category (String) **
        translation.creationDate 	->  _created_at (ISO 8601 encoded string) ***
        "TopDesk" 			        ->  _data_source_id (String) ***
        translation.content.content	->  _document_body (String) – The content of the document. ****
        id || number 			    ->  _document_id (String) – A unique identifier for the document. ****
        translation.content.title	->  _document_title (String) – The title of the document. ***
        hmm... Maybe useful, or not.    _faq_id (String) – If this is an FAQ question and answer, a unique identifier for them. *
        "html" 				        ->  _file_type (String) – The file type of the document, such as pdf or doc. ****
        translation.modificationDate->  _last_updated_at (ISO 8601 encoded string) – The date and time that the document was last updated. ***
        translation.language		->  _language_code (String) ****
     */

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
        assertEquals("html", knowledgeItem.getDocumentType());
    }

    @Test
    public void getLanguageCode_returnString()
    {
        assertNotNull((knowledgeItem.getLanguageCode()));
        assertInstanceOf(String.class, knowledgeItem.getLanguageCode());
    }
}