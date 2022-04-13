package se.chalmers.topdesk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    public void configuration_toStringReturnsString()
    {
        assertInstanceOf(String.class, configuration.toString(), "Configuration.toString() did not return a String");
    }
}