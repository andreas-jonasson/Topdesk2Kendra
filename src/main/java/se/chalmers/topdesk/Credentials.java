package se.chalmers.topdesk;

public class Credentials
{
    public String topdesk_username;
    public String topdesk_password;

    @Override
    public String toString() {
        return "{" +
                "topdesk_username='" + topdesk_username + '\'' +
                ", topdesk_password='" + topdesk_password + '\'' +
                '}';
    }
}
