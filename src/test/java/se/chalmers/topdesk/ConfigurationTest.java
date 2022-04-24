package se.chalmers.topdesk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


class ConfigurationTest
{
    private Configuration configuration;

    @BeforeEach
    void setUp()
    {
         configuration = Configuration.getInstance();
    }

    @Test
    @DisplayName("Configuration.getInstance() returns a Configuration")
    public void getReminder_getExist()
    {
        assertNotNull(configuration, "Configuration.getInstance() did not return a valid instance.");
        assertInstanceOf(Configuration.class, configuration, "Configuration.getInstance() did not return an instance of Configuration.");
    }

    @Test
    @DisplayName("Configuration.toString() returns a string")
    public void toString_ReturnsString()
    {
        assertInstanceOf(String.class, configuration.toString(), "Configuration.toString() did not return a String");
    }

    @Test
    public void topdesk_languages_to_export_IsArrayWithAtLeastOneElement()
    {
        assertNotNull(configuration.topdesk_languages_to_export);
        assertTrue(configuration.topdesk_languages_to_export.length >= 1);
    }

    @Test
    public void topdesk_credentials_isNotNull()
    {
        assertNotNull(configuration.topdesk_credentials);
    }

    @Test
    public void topdesk_credentials_file_isNotNull()
    {
        assertNotNull(configuration.topdesk_credentials_file);
    }

    @Test
    public void topdesk_credentials_file_pointsAtFile()
    {
        assertTrue(new File(configuration.topdesk_credentials_file).exists());
    }

    @Test
    public void topdesk_endpoint_knowledgeItems_isNotNull()
    {
        assertNotNull(configuration.topdesk_endpoint_knowledgeItems);
    }

    @Test
    public void topdesk_default_language_isNotNull()
    {
        assertNotNull(configuration.topdesk_default_language);
    }

    @Test
    public void topdesk_base_url_isNotNull()
    {
        assertNotNull(configuration.topdesk_base_url);
    }

    @Test
    public void topdesk_output_dir_isNotNull()
    {
        assertNotNull(configuration.topdesk_output_dir);
    }

    @Test
    public void topdesk_output_dir_pointsAtFile()
    {
        assertTrue(new File(configuration.topdesk_output_dir).isDirectory());
    }
}