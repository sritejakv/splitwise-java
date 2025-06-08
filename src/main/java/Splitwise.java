import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import constants.Strings;
import constants.URL;
import utils.OAuthUtil;
import utils.Splitwise10Api;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Java SDK for Splitwise. Authentication is done using OAuth1.0
 */
public class Splitwise {
    private String consumerKey, consumerSecret;
    protected OAuthUtil util;

    /**
     * Constructor.
     * @param consumerKey consumerKey provided by splitwise
     * @param consumerSecret consumerSecret provided by splitwise
     */
    public Splitwise(String consumerKey, String consumerSecret){
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.util = new OAuthUtil().setApiKey(this.consumerKey)
                .setApiSecret(this.consumerSecret)
                .build(Splitwise10Api.instance());
    }

    /**
     * Returns authorization url using which user can get token verifier.
     * @return authorization url
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public String getAuthorizationUrl() throws InterruptedException, ExecutionException, IOException {
        return this.util.getAuthorizationUrl();
    }

    /**
     * Authenticates with splitwise with the verifier passed and returns the token details.
     * @param verifier
     * @return Token details provided by OAuth
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public Token getAccessToken(String verifier) throws InterruptedException, ExecutionException, IOException {
        this.util.setAccessToken(verifier);
        return this.util.getAccessToken();
    }

    /**
     * Returns the JSON string of current user based on the consumerKey and consumerSecret.
     * @return JSON string of user
     * @throws Exception
     */
    public String getCurrentUser() throws Exception {
        Response response = this.util.makeRequest(String.format(URL.GET_CURRENT_USER, Strings.SPLITWISE_API_VERSION)
                , Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Returns the JSON string of user based on the userId passed.
     * @param userId Splitwise id of the user
     * @return JSON string containing user details
     * @throws Exception
     */
    public String getUser(String userId) throws Exception {
        String url = String.format(URL.GET_USER_WITH_ID, userId);
        Response response = this.util.makeRequest(url, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Update the parameters of the current user.
     * @param id splitwise id of the user
     * @param details details to be updated
     * @return JSON response string from splitwise
     * @throws Exception
     * @see <a href="https://dev.splitwise.com/#tag/users/paths/~1update_user~1{id}/post">Splitwise API
     * documentation</a> for required and optional parameters
     */
    public String updateUser(String id, Map<String, String> details) throws Exception {
        String url = String.format(URL.UPDATE_USER_WITH_ID, id);
        Response response = this.util.makeRequest(url, Verb.POST, details);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Returns the JSON string of the splitwise groups of the current user.
     * @return JSON string containing the groups
     * @throws Exception
     */
    public String getGroups() throws Exception {
        Response response = this.util.makeRequest(URL.GET_GROUPS, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Returns the JSON string of the group based on the group id passed.
     * @param id splitwise group id
     * @return JSON string containing group details
     * @throws Exception
     */
    public String getGroup(String id) throws Exception {
        String url = String.format(URL.GET_GROUP_WITH_ID, id);
        Response response = this.util.makeRequest(url, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Creates a splitwise group.
     * @param details details of the group
     * @return JSON response from splitwise
     * @throws Exception
     * @see <a href="https://dev.splitwise.com/#tag/groups/paths/~1create_group/post">Splitwise API
     * documentation</a> for required and optional parameters
     */
    public String createGroup(Map<String, String> details) throws Exception {
        Response response = this.util.makeRequest(URL.CREATE_GROUP_URL, Verb.POST, details);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Delete splitwise group based on the id.
     * @param id splitwise group id
     * @return JSON response from splitwise
     * @throws Exception
     */
    public String deleteGroup(String id) throws Exception {
        String url = String.format(URL.DELETE_GROUP_WITH_ID, id);
        Response response = this.util.makeRequest(url, Verb.POST);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Add user to a splitwise group.
     * @param userDetails details of the user to be added
     * @return JSON response from splitwise
     * @throws Exception
     * @see <a href="https://dev.splitwise.com/#tag/groups/paths/~1add_user_to_group/post">Splitwise API
     * documentation</a> for required and optional parameters
     */
    public String addUserToGroup(Map<String, String> userDetails) throws Exception {
        Response response = this.util.makeRequest(URL.ADD_USER_TO_GROUP, Verb.POST, userDetails);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Remove user from a splitwise group.
     * @param groupId splitwise group id
     * @param userId splitwise user id
     * @return JSON response from splitwise
     * @throws Exception
     */
    public String removeUserFromGroup(final String groupId, final String userId) throws Exception {
        Map<String, String> details = new HashMap<String, String>(){{
            put(Strings.USER_ID, userId);
            put(Strings.GROUP_ID, groupId);
        }};
        Response response = this.util.makeRequest(URL.REMOVE_USER_FROM_GROUP, Verb.POST, details);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Returns splitiwse friends of the current user.
     * @return JSON string containing friends
     * @throws Exception
     */
    public String getFriends() throws Exception {
        Response response = this.util.makeRequest(URL.GET_FRIENDS, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Returns splitwise friend details based on the id passed.
     * @param id splitwise user id of the friend
     * @return JSON string containing friend details
     * @throws Exception
     */
    public String getFriend(String id) throws Exception {
        String url = String.format(URL.GET_FRIEND, id);
        Response response = this.util.makeRequest(url, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Create a splitwise friend
     * @param firstName first name of the friend
     * @param lastName last name of the friend
     * @param email email of the friend
     * @return JSON response from splitwise
     * @throws Exception
     */
    public String createFriend(final String firstName, final String lastName, final String email) throws Exception {
        Map<String, String> friendDetails = new HashMap<String, String>(){{
            put(Strings.USER_FIRST_NAME, firstName);
            put(Strings.USER_LAST_NAME, lastName);
            put(Strings.USER_EMAIL, email);
        }};
        Response response = this.util.makeRequest(URL.CREATE_FRIEND, Verb.POST, friendDetails);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Delete a friend from splitwise.
     * @param friendId splitwise user id
     * @return JSON response string from splitwise
     * @throws Exception
     */
    public String deleteFriend(String friendId) throws Exception {
        String url = String.format(URL.DELETE_FRIEND_WITH_ID, friendId);
        Response response = this.util.makeRequest(url, Verb.POST);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Get splitwise comments on an expense.
     * @param expenseId splitwise expense id
     * @return JSON string of comments on the expense
     * @throws Exception
     */
    public String getComments(String expenseId) throws Exception {
        String url = String.format(URL.GET_COMMENTS_FOR_EXPENSE, expenseId);
        Response response = this.util.makeRequest(url, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Creates a splitwise expense.
     * @param details details of the expense
     * @return JSON response from splitwise
     * @throws Exception
     * @see <a href="https://dev.splitwise.com/#tag/expenses/paths/~1create_expense/post">Splitwise API
     * documentation</a> for required and optional parameters
     */
    public String createExpense(Map<String, String> details) throws Exception {
        Response response = this.util.makeRequest(URL.CREATE_EXPENSE, Verb.POST, details);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Get all expenses for the current user.
     * @return JSON string containing user expenses
     * @throws Exception
     */
    public String getExpenses() throws Exception {
        Response response = this.util.makeRequest(URL.GET_EXPENSES, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Get user expense for an expense id.
     * @param expenseId splitwise expense id
     * @return JSON string containing expense details
     * @throws Exception
     */
    public String getExpense(String expenseId) throws Exception {
        String url = String.format(URL.GET_EXPENSE_WITH_ID, expenseId);
        Response response = this.util.makeRequest(url, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Delete splitwise expense of current user
     * @param expenseId splitwise expense id
     * @return JSON string response from splitwise
     * @throws Exception
     */
    public String deleteExpense(String expenseId) throws Exception {
        String url = String.format(URL.DELETE_EXPENSE_WITH_ID, expenseId);
        Response response = this.util.makeRequest(url, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Get unseen notifications of the current user.
     * @return JSON string response containing user notifications
     * @throws Exception
     */
    public String getNotifications() throws Exception {
        Response response = this.util.makeRequest(URL.GET_NOTIFICATIONS, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Get currencies supported by splitwise
     * @return JSON string containing splitwise currencies
     * @throws Exception
     */
    public String getCurrencies() throws Exception {
        Response response = this.util.makeRequest(URL.GET_CURRENCIES, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    /**
     * Get categories supported by splitwise
     * @return JSON string containing splitwise categories
     * @throws Exception
     */
    public String getCategories() throws Exception {
        Response response = this.util.makeRequest(URL.GET_CATEGORIES, Verb.GET);
        if (response.getCode() == 200)
            return response.getBody();
        return null;
    }

    public static void main(String... args) throws Exception {
        Splitwise splitwise = new Splitwise("<consumerKey>",
                "<consumerSecret>");
        String authorizationURL = splitwise.getAuthorizationUrl();
        splitwise.util.setAccessToken("<verifier_from_authorizationURL>");
        OAuth1AccessToken accessToken = (OAuth1AccessToken) splitwise.util.getAccessToken();
        splitwise.util.setAccessToken(accessToken.getToken(),
                accessToken.getTokenSecret(),
                accessToken.getRawResponse()
        );
        System.out.println("Response \n" + splitwise.getCurrentUser());
    }
}
