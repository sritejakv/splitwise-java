package utils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Utility class that performs OAuth authentication.
 */
public class OAuthUtil {
    private String apiKey;
    private String apiSecret;
    private OAuth10aService service;
    private OAuth1RequestToken requestToken;
    private OAuth1AccessToken accessToken;
    private List<Integer> httpErrorCodes = new ArrayList<Integer>() {{
        add(400);
        add(401);
        add(403);
        add(404);
        add(500);
        add(503);
    }};

    /**
     * Set the consumer key of the API.
     * @param apiKey consumer key
     * @return OAuthUtil instance
     */
    public OAuthUtil setApiKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Set the consumer secret of the API.
     * @param apiSecret consumer secret
     * @return OAuthUtil instance
     */
    public OAuthUtil setApiSecret(@NotNull String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }

    /**
     * Returns the authorization url from apiKey and apiSecret.
     * @return authorization url
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public String getAuthorizationUrl() throws InterruptedException, ExecutionException, IOException {
        this.requestToken = service.getRequestToken();
        String authUrl = service.getAuthorizationUrl(requestToken);
        return authUrl;
    }

    /**
     * Sets the access token by using the token verifier received from authorization url.
     * @param verifier token verifier
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public void setAccessToken(@NotNull String verifier) throws InterruptedException, ExecutionException, IOException {
        this.accessToken = this.service.getAccessToken(requestToken, verifier);
    }

    /**
     * Set the access token details.
     * @param token access token
     * @param tokenSecret access token secret
     * @param rawResponse raw response of the access token
     */
    public void setAccessToken(@NotNull String token, @NotNull String tokenSecret, String rawResponse){
        this.accessToken = new OAuth1AccessToken(token, tokenSecret, rawResponse);
    }

    /**
     * Returns the access token details received.
     * @return access token details received
     */
    public Token getAccessToken(){
        return this.accessToken;
    }

    /**
     * Generates the API request, communicates to the API and returns the response.
     * @param url URL to communicate
     * @param httpMethod GET or POST
     * @param data data to be sent if the httpMethod is POST
     * @return response from the API
     * @throws Exception
     */
    public Response makeRequest(String url, Verb httpMethod, Object... data) throws Exception {
        OAuthRequest request = new OAuthRequest(httpMethod, url);
        if (data.length > 0 && data[0] instanceof Map){
            Map<String, String> details = (Map<String, String>) data[0];
            for (Map.Entry<String, String> entry: details.entrySet()){
                request.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        this.service.signRequest(this.accessToken, request);

        Response response = null;
        try {
            response = this.service.execute(request);
        } catch (InterruptedException e) {
            throw new Exception(String.format(
                    "Invalid response received - %s. " +
                            "Please check your client id and secret.", e.getMessage()));
        } catch (ExecutionException e) {
            throw new Exception(String.format(
                    "Invalid response received - %s. " +
                            "Please check your client id and secret.", e.getMessage()));
        } catch (IOException e) {
            throw new Exception(String.format(
                    "Invalid response received - %s. " +
                            "Please check your client id and secret.", e.getMessage()));
        } catch (Exception e) {
            throw new Exception(String.format(
                    "Invalid response received - %s. " +
                            "Please check your client id and secret.", e.getMessage()));
        }
        return parseResponse(response);
    }

    /**
     * Parse the response from the API
     * @param response response from the API
     * @return response if the response code is 200
     * @throws Exception throws exception for other response codes
     */
    private Response parseResponse(@NotNull Response response) throws Exception {
        if (httpErrorCodes.contains(response.getCode())){
            throw new Exception(String.format(
                    "Invalid response received with body - %s, message - %s. " +
                            "Please check your client id and secret. Response code - %s", response.getBody(),
                    response.getMessage(),
                    response.getCode()));
        }
        return response;
    }

    /**
     * Utility builder for OAuth authentication
     * @param instance API instance containing OAuth details
     * @return OAuthUtil instance
     */
    public OAuthUtil build(DefaultApi10a instance){
        this.service = new ServiceBuilder(this.apiKey)
                .apiSecret(this.apiSecret)
                .build(instance);
        return this;
    }

    /**
     * Utility builder for OAuth authentication
     * @param apiKey consumerKey
     * @param apiSecret consuerSecret
     * @param instance API instance containing OAuth details
     * @return OAuthUtil instance
     */
    public OAuthUtil build(@NotNull String apiKey, @NotNull String apiSecret, DefaultApi10a instance){
        setApiKey(apiKey);
        setApiSecret(apiSecret);
        this.service = new ServiceBuilder(this.apiKey)
                .apiSecret(this.apiSecret)
                .build(instance);
        return this;
    }
}
