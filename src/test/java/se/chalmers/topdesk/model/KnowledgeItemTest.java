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
}