# splitwise-java
**Java SDK for Splitwise**

[![Maven Central](https://img.shields.io/maven-central/v/com.github.sritejakv/splitwise.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.sritejakv%22%20AND%20a:%22splitwise%22)

***
This library contains implementation of Splitwise SDK in Java. Currently the following [splitwise API](http://dev.splitwise.com/#introduction) calls 
are supported,

`get_current_user`
`get_user/:id`
`update_user/:id`
`get_groups`
`get_group/:id`
`create_group`
`delete_group/:id`
`add_user_to_group`
`remove_user_from_group`
`get_friends`
`get_friend/:id`
`create_friend`
`delete_friend/:id`
`get_expenses`
`get_expense/:id`
`delete_expense/:id`
`get_comments?expense_id=`
`get_notifications`
`get_currencies`
`get_categories`

Other API implementations are expected to follow as future updates.
Pull requests and bugs are welcomed.

**Maven**
***
Add the following lines in **pom.xml** of the maven project to use the library,

```xml
<dependency>
  <groupId>com.github.sritejakv</groupId>
  <artifactId>splitwise</artifactId>
  <version>1.0</version>
</dependency>
```

**Gradle**
***
Add the following line in Gradle projects to use the library,

```$xslt
compile("com.github.sritejakv:splitwise:1.0")
```

**Authentication**
***
Currently the authentication is performed using [ScribeJava](https://github.com/scribejava/scribejava)
library which supports both OAuth1.0 and OAuth2.0. In this library, **OAuth1.0** is included at this
point of time. Authentication using OAuth2.0 is expected in the future updates.

**Registration**
***
The library expects consumer key and consumer secret of Splitwise and can be fetched after registering
your application [here](https://secure.splitwise.com/oauth_clients).

**Using the library**
***

**Authorization**

As already mentioned, the library expects consumer key and consumer secret from Splitwise. As splitwise
uses OAuth authentication for access, the user should also fetch token verifier from the 
authorization url. This is done in two steps,

1. Initialize splitwise with consumer key and consumer secret.

```$xslt
Splitwise splitwise = new Splitwise("<consumerKey>", "<consumerSecret>");
String authorizationURL = splitwise.getAuthorizationUrl();
```

2. Open the authorizationURL in the browser. It is a simple process of logging into Splitwise and
giving the access. After giving the access, token verifier is shown in the url itself. Set the 
access token using the verifier.

```$xslt
splitwise.util.setAccessToken("<verifier_from_authorizationURL>");
```

To skip the process of authenticating every time, the token details fetched above can be used to
set the token without actually going into authorizationURL.

```$xslt
OAuth1AccessToken accessToken = (OAuth1AccessToken) splitwise.util.getAccessToken();
splitwise.util.setAccessToken(accessToken.getToken(),
                accessToken.getTokenSecret(),
                accessToken.getRawResponse()
        );
```

However, the user must authorize at least once to get the token verifier.

**Access data from splitwise**

Once the authorization if performed and token details are fetched, they can be used to instantiate
Splitwise class and set the access token as shown above.

```$xslt
Splitwise splitwise = new Splitwise("<consumerKey>", "<consumerSecret>");
splitwise.util.setAccessToken(@NotNull <token>, @NotNull <tokenSecret>, <tokenRawResponse>);
```

The complete list of methods the library supports are as follows,
 
**Get Current User**

`getCurrentUser()` can be used to fetch current user. It returns the user details in a JSON string.

```$xslt
String userDetailsJson = splitwise.getCurrentUser();
```

**Get User**

`getUser(userId)` can be used to fetch user details. It returns user details in a JSON String.

```$xslt
String userId = "324343";
String userDetailsJson = splitwise.getUser(userId);
```

**Update User**

`updateUser(userId, userDetails)` can be used to update the details of the splitwise user. The 
user fields that can be updated can be seen [here](http://dev.splitwise.com/#update_user-id).

```$xslt
Map<String, String> userDetails = new HashMap<String, String>(){{
    put("first_name", "Lorem");
    put("last_name", "Ipsum");
}}
String userId = 324343
String response = splitwise.updateUser(userId, userDetails);
```

**Get Groups**

`getGroups()` can be used to fetch all the splitwise groups of the current user. It returns a
JSON string of the group details.

```$xslt
String groupDetails = splitwise.getGroups();
```

***Get Group***

`getGroup(groupId)` can be used to fetch details of a specific splitwise group. It returns a
JSON string of the group details.

```$xslt
String groupId = "123";
String groupDetails = splitwise.getGroup(groupId);
```

***Create Group***

`createGroup(groupDetails)` can be used to create splitwise group. `groupDetails` can contain 
all the item shown [here](http://dev.splitwise.com/#create_group).

```$xslt
Map<String, String> groupDetails = new HashMap<String, String>(){{
    put("name", "Lorem");
    put("group_type", "Apartment");
}}
String response = splitwise.createGroup(groupDetails);
```

**Delete Group**

`deleteGroup(groupId)` can be used to delete a splitwise group.

```$xslt
String groupId = "123";
String response = splitwise.deleteGroup(groupId);
```

***Add User to Group***

`addUserToGroup(userDetails)` can be used to add a user to a group.

```$xslt
Map<String, String> userDetails = new HashMap<String, String>(){{
    put("first_name", "Lorem");
    put("last_name", "Ipsum");
    put("email", "hello@world.com");
    put("user_id", 324343);
    put("group_id", 123);
}}
String response = splitwise.addUserToGroup(userDetails);
```

***Remove User from Group***

`removeUserFromGroup(groupId, userId)` can be used to remove a splitwise user from a splitwise group.

```$xslt
int userId = 324343;
int groupId = 123;
String response = splitwise.removeUserFromGroup(groupId, userId);
```

***Get Friends***

`getFriends()` can be used to fetch all the splitwise friends of the current user. It returns a
JSON string containing all the friend details.

```$xslt
String friends = splitwise.getFriends();
```

***Get Friend***

`getFriend(userId)` can be used to fetch details of a splitwise friend. It returns a
JSON string containing all the friend details.

```$xslt
String userId = "324343"
String friendDetails = splitwise.getFriend(userId);
```

***Create a friend***

`createFriend(firstName, lastName, email)` can be used to add a friend into Splitwise.

```$xslt
String response = splitwise.createFriend("lorem", "ipsum", "hello@world.com");
```

***Delete a friend***

`deleteFriend(friendId)` can be used to delete a friend from Splitwise.

```$xslt
String friendId = 345;
String response = splitwise.deleteFriend(friendId);
```

***Get Expenses***

`getExpenses()` can be used to get all the expenses of the current user.  It returns a
JSON string containing all the expenses.

```$xslt
String expenses = splitwise.getExpenses();
```

***Get Expense***

`getExpense(expenseId)` can be used to get details of a splitwise expense. It returns a
JSON string containing the expense details.

```$xslt
String expenseId = "765";
String expenseDetails = splitwise.getExpense(expenseId);
```

***Delete Expense***

`deleteExpense(expenseId)` can be used to delete a splitwise expense.

```$xslt
String expenseId = "765";
String response = splitwise.deleteExpense(expenseId);
```

***Get Comments on an Expense***

`getComments(expenseId)` can be used to get comments on a splitwise expense. It returns a
JSON string containing the expense details.

```$xslt
String expenseId = "987";
String comments = splitwise.getComments(expenseId);
```

***Get Notifications***

`getNotifications()` can be used to get unseen notifications of the current user. It returns a
JSON string containing all the unseen notifications.

```$xslt
String notifications = splitwise.getNotifications();
```

***Get Currencies***

`getCurrencies()` can be used to fetch the currencies supported by Splitwise. It returns a
JSON string containing all the currencies.

```$xslt
String currencies = splitwise.getCurrencies();
```

***Get Categories***

`getCategories()` can be used to fetch the categories in Splitwise. It returns a
JSON string containing all the categories.

```$xslt
String categories = splitwise.getCategories();
```

***Testing***
***

The library includes test cases for some of the methods. It uses [Mockito](https://site.mockito.org/)
framework to mock the methods needed in unit test cases.

Test cases for two vital classes `Splitwise` and `OAuthUtl` are included in the library, 
`SplitwiseTest` and `OAuthUtilTest`.

**License**
***
[MIT](http://www.opensource.org/licenses/mit-license.php)