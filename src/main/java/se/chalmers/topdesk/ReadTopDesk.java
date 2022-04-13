package se.chalmers.topdesk;

import java.io.IOException;

public class ReadTopDesk
{
    public static void main(final String[] args)
    {
        System.out.println("Reading configuration...");
        System.out.println(Configuration.getInstance());
        System.out.println("Accessing KnowledegeItems endpoint...");
        //new TopDesk().getRequest();
    }
}
