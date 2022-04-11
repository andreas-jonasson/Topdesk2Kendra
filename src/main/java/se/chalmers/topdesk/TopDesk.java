package se.chalmers.topdesk;

import org.apache.http.Header;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TopDesk
{
    private final Configuration configuration = Configuration.getInstance();

    void getRequest() throws IOException
    {
        final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(configuration.topdesk_domain, 80),
                new UsernamePasswordCredentials(configuration.topdesk_password, configuration.topdesk_username));
        try (final CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build()) {
            final HttpGet httpget = new HttpGet(configuration.topdesk_endpoint_knowledgeItems);

            System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getURI());
            for (Header header: httpget.getAllHeaders())
                System.out.println("\t" + header);

            try (final CloseableHttpResponse response = httpclient.execute(httpget)) {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }
}
