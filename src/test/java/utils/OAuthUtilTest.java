package utils;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import constants.Strings;
import constants.URL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OAuthUtilTest {

    @InjectMocks
    private OAuthUtil oAuthUtil;

    @Mock
    private OAuth10aService mockService;

    private Response mockResponse = new Response(200, "mockMessage", new HashMap<String, String>(),
            new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    });

    private Response mockInvalidResponse = new Response(400, "mockInvalidMessage",
            new HashMap<String, String>(),
            "Invalid Request.");

    private String mockApiToken = "jdfajiodflslm";
    private String mockApiSecret = "jipmewlfeifmdls";
    private String mockRawResponse = "{key: value}";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        oAuthUtil = new OAuthUtil();
    }

    @Test
    public void makeRequest() throws Exception {
        doAnswer(new Answer<Void>() {

            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                return null;
            }
        }).when(mockService).signRequest(any(OAuth1AccessToken.class), any(OAuthRequest.class));

        when(mockService.execute(any(OAuthRequest.class))).thenReturn(mockResponse);
        FieldSetter.setField(oAuthUtil, oAuthUtil.getClass().getDeclaredField("service"), mockService);
        oAuthUtil.setAccessToken(mockApiToken, mockApiSecret, mockRawResponse);
        Response resp = oAuthUtil.makeRequest(String.format(URL.GET_FRIENDS, Strings.SPLITWISE_API_VERSION), Verb.GET);

        assertThat(resp.getCode(), is(200));
        verify(mockService).signRequest(any(OAuth1AccessToken.class), any(OAuthRequest.class));
        verify(mockService).execute(any(OAuthRequest.class));
    }

    @Test(expected = Exception.class)
    public void makeInvalidRequest() throws Exception {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(mockService).signRequest(any(OAuth1AccessToken.class), any(OAuthRequest.class));

        when(mockService.execute(any(OAuthRequest.class))).thenReturn(mockInvalidResponse);
        FieldSetter.setField(oAuthUtil, oAuthUtil.getClass().getDeclaredField("service"), mockService);
        oAuthUtil.setAccessToken(mockApiToken, mockApiSecret, mockRawResponse);
        Response resp = oAuthUtil.makeRequest(String.format(URL.GET_FRIENDS, Strings.SPLITWISE_API_VERSION), Verb.GET);
    }
}