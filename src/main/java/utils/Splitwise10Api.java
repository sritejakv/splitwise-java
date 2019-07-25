package utils;

import com.github.scribejava.core.builder.api.DefaultApi10a;

/**
 * Class containing OAuth1.0 details of splitwise API.
 */
public class Splitwise10Api extends DefaultApi10a {

    private static final Splitwise10Api INSTANCE = new Splitwise10Api();

    public String getRequestTokenEndpoint() {
        return "https://secure.splitwise.com/oauth/request_token";
    }

    public String getAccessTokenEndpoint() {
        return "https://secure.splitwise.com/oauth/access_token";
    }

    protected String getAuthorizationBaseUrl() {
        return "https://secure.splitwise.com/oauth/authorize";
    }

    public static Splitwise10Api instance() {
        return Splitwise10Api.INSTANCE;
    }
}
