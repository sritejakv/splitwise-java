import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import constants.MockResponses;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import utils.OAuthUtil;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SplitwiseTest {
    //Fill in the apiKey and apiSecret before running the test cases.
    private String apiKey = "sdfksdfds";
    private String apiSecret = "jfkdjfkd";

    //Fill token, tokenSecret after authorization.
    private String token = "kjfkdjksk";
    private String tokenSecret = "lkjdfimdskl;";

    @InjectMocks
    private Splitwise splitwise;

    @Before
    public void setUp() {
        splitwise = new Splitwise(apiKey, apiSecret);
        splitwise.util.setAccessToken(token, tokenSecret, "");
    }

    @Test
    public void getAuthorizationUrl() {
        String authUrl = null;
        OAuthUtil mockUtil = mock(OAuthUtil.class);
        splitwise.util = mockUtil;
        try {
            when(mockUtil.getAuthorizationUrl()).thenReturn(MockResponses.MOCK_AUTHURL);
            authUrl = splitwise.getAuthorizationUrl();
            URL auth = new URL(authUrl);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (ExecutionException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getCurrentUser() {
        Response mockResponse = new Response(200, "mockMessage", new HashMap<String, String>(),
                MockResponses.MOCK_USER);
        OAuthUtil mockUtil = mock(OAuthUtil.class);
        splitwise.util = mockUtil;
        try {
            when(mockUtil.makeRequest(any(String.class), any(Verb.class))).thenReturn(mockResponse);
            String userDetails = splitwise.getCurrentUser();
            JSONObject userJson = new JSONObject(userDetails);
            assertTrue(userJson.has("user"));
            verify(mockUtil).makeRequest(any(String.class), any(Verb.class));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getFriends() {
        Response mockResponse = new Response(200, "mockMessage", new HashMap<String, String>(),
                MockResponses.MOCK_FRIEND);
        OAuthUtil mockUtil = mock(OAuthUtil.class);
        splitwise.util = mockUtil;
        try {
            when(mockUtil.makeRequest(any(String.class), any(Verb.class))).thenReturn(mockResponse);
            String friends = splitwise.getFriends();
            JSONObject userJson = new JSONObject(friends);
            assertTrue(userJson.has("friends"));
            verify(mockUtil).makeRequest(any(String.class), any(Verb.class));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getExpenses() {
        Response mockResponse = new Response(200, "mockMessage", new HashMap<String, String>(),
                MockResponses.MOCK_EXPENSES);
        OAuthUtil mockUtil = mock(OAuthUtil.class);
        splitwise.util = mockUtil;
        try {
            when(mockUtil.makeRequest(any(String.class), any(Verb.class))).thenReturn(mockResponse);
            String expenses = splitwise.getExpenses();
            JSONObject userJson = new JSONObject(expenses);
            assertTrue(userJson.has("expenses"));
            verify(mockUtil).makeRequest(any(String.class), any(Verb.class));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getCurrencies() {
        Response mockResponse = new Response(200, "mockMessage", new HashMap<String, String>(),
                MockResponses.MOCK_CURRENCIES);
        OAuthUtil mockUtil = mock(OAuthUtil.class);
        splitwise.util = mockUtil;
        try {
            when(mockUtil.makeRequest(any(String.class), any(Verb.class))).thenReturn(mockResponse);
            String currencies = splitwise.getCurrencies();
            JSONObject userJson = new JSONObject(currencies);
            assertTrue(userJson.has("currencies"));
            verify(mockUtil).makeRequest(any(String.class), any(Verb.class));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getCategories() {
        Response mockResponse = new Response(200, "mockMessage", new HashMap<String, String>(),
                MockResponses.MOCK_CATGORIES);
        OAuthUtil mockUtil = mock(OAuthUtil.class);
        splitwise.util = mockUtil;
        try {
            when(mockUtil.makeRequest(any(String.class), any(Verb.class))).thenReturn(mockResponse);
            String categories = splitwise.getCategories();
            JSONObject userJson = new JSONObject(categories);
            assertTrue(userJson.has("categories"));
            verify(mockUtil).makeRequest(any(String.class), any(Verb.class));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}