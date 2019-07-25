package constants;

public class MockResponses {

    public static final String MOCK_AUTHURL = "https://secure.splitwise.com/oauth/authorize?" +
            "oauth_token=jfsjflkdsjlfdsj";

    public static final String MOCK_USER = "{\n" +
            "  \"user\": {\n" +
            "    \"id\": 1,\n" +
            "    \"first_name\": \"Ada\",\n" +
            "    \"last_name\": \"Lovelace\",\n" +
            "    \"picture\": {\n" +
            "      \"small\": \"image_url\",\n" +
            "      \"medium\": \"image_url\",\n" +
            "      \"large\": \"image_url\"\n" +
            "    },\n" +
            "    \"email\": \"ada@example.com\",\n" +
            "    \"registration_status\": \"confirmed\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static final String MOCK_FRIEND = "{\n" +
            "  \"friends\":[\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"first_name\": \"Ada\",\n" +
            "      \"last_name\": \"Lovelace\",\n" +
            "      \"picture\": {\n" +
            "        \"small\": \"image_url\",\n" +
            "        \"medium\": \"image_url\",\n" +
            "        \"large\": \"image_url\"\n" +
            "      },\n" +
            "      \"balance\":[\n" +
            "        {\n" +
            "          \"currency_code\":\"USD\",\n" +
            "          \"amount\":\"-1794.5\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"currency_code\":\"AED\",\n" +
            "          \"amount\":\"7.5\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"groups\":[\n" +
            "        {\n" +
            "          \"group_id\":3018312,\n" +
            "          \"balance\":[\n" +
            "            {\n" +
            "              \"currency_code\":\"USD\",\n" +
            "              \"amount\":\"414.5\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"group_id\":2830896,\n" +
            "          \"balance\":[\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"group_id\":0,\n" +
            "          \"balance\":[\n" +
            "            {\n" +
            "              \"currency_code\":\"USD\",\n" +
            "              \"amount\":\"-2209.0\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"currency_code\":\"AED\",\n" +
            "              \"amount\":\"7.5\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"updated_at\":\"2017-11-30T09:41:09Z\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String MOCK_EXPENSES = "{ \"expenses\" : [" +
            "]}";

    public static final String MOCK_CURRENCIES = "{\n" +
            "  \"currencies\":[\n" +
            "    { \"currency_code\":\"USD\", \"unit\":\"$\" },\n" +
            "    { \"currency_code\":\"ARS\", \"unit\":\"$\" },\n" +
            "    { \"currency_code\":\"AUD\", \"unit\":\"$\" },\n" +
            "    { \"currency_code\":\"EUR\", \"unit\":\"€\" },\n" +
            "    { \"currency_code\":\"BRL\", \"unit\":\"R$\" },\n" +
            "    { \"currency_code\":\"CAD\", \"unit\":\"$\" },\n" +
            "    { \"currency_code\":\"CNY\", \"unit\":\"¥\" },\n" +
            "    { \"currency_code\":\"DKK\", \"unit\":\"kr\" },\n" +
            "    { \"currency_code\":\"GBP\", \"unit\":\"£\" },\n" +
            "    { \"currency_code\":\"INR\", \"unit\":\"₹\" },\n" +
            "    { \"currency_code\":\"ILS\", \"unit\":\"₪\" },\n" +
            "    { \"currency_code\":\"JPY\", \"unit\":\"¥\" }]}";

    public static final String MOCK_CATGORIES = "{ \"categories\" : [" +
            "]}";
}
