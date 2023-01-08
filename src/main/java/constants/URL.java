package constants;

/**
 * Contains the Splitwise REST API URLs from (http://dev.splitwise.com/#introduction).
 */
public class URL {
    public static final String BASE_URL = String.format("https://secure.splitwise.com/api/%s",
            Strings.SPLITWISE_API_VERSION);

    //URLs for Users API
    public static final String GET_CURRENT_USER = BASE_URL +"/get_current_user";
    public static final String GET_USER_WITH_ID = BASE_URL + "/get_user/%s";
    public static final String UPDATE_USER_WITH_ID = BASE_URL + "/update_user/%s";

    //URLs for groups API
    public static final String GET_GROUPS = BASE_URL + "/get_groups";
    public static final String GET_GROUP_WITH_ID = BASE_URL +"/get_group/%s";
    public static final String CREATE_GROUP_URL = BASE_URL +"/create_group";
    public static final String DELETE_GROUP_WITH_ID = BASE_URL +"/delete_group/%s";
    public static final String ADD_USER_TO_GROUP = BASE_URL + "/add_user_to_group";
    public static final String REMOVE_USER_FROM_GROUP = BASE_URL + "/remove_user_from_group";

    //URLs for Friends API
    public static final String GET_FRIENDS = BASE_URL + "/get_friends";
    public static final String GET_FRIEND = BASE_URL + "/get_friend/%s";
    public static final String CREATE_FRIEND = BASE_URL + "/create_friend";
    public static final String DELETE_FRIEND_WITH_ID = BASE_URL + "/delete_friend/%s";

    //URLs for Expenses API
    public static final String CREATE_EXPENSE = BASE_URL + "/create_expense";
    public static final String GET_EXPENSES = BASE_URL + "/get_expenses";
    public static final String GET_EXPENSE_WITH_ID = BASE_URL + "/get_expense/%s";
    public static final String DELETE_EXPENSE_WITH_ID = BASE_URL + "/delete_expense/%s";

    //Others
    public static final String GET_COMMENTS_FOR_EXPENSE = BASE_URL + "/get_comments?expense_id=%s";
    public static final String GET_NOTIFICATIONS = BASE_URL + "/get_notifications";
    public static final String GET_CURRENCIES = BASE_URL + "/get_currencies";
    public static final String GET_CATEGORIES = BASE_URL + "/get_categories";
}
