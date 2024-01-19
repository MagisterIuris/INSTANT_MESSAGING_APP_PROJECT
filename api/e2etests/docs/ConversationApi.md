# ConversationApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addConversation**](ConversationApi.md#addConversation) | **POST** /conversation/addConversation | Create a new conversation |
| [**getAllConversationsOfUser**](ConversationApi.md#getAllConversationsOfUser) | **GET** /conversation/getAllConversationsOfUser | Get all conversations of the current user |


<a id="addConversation"></a>
# **addConversation**
> ConversationDisplayDTO addConversation(userProfileDTO)

Create a new conversation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    UserProfileDTO userProfileDTO = new UserProfileDTO(); // UserProfileDTO | 
    try {
      ConversationDisplayDTO result = apiInstance.addConversation(userProfileDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#addConversation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userProfileDTO** | [**UserProfileDTO**](UserProfileDTO.md)|  | |

### Return type

[**ConversationDisplayDTO**](ConversationDisplayDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="getAllConversationsOfUser"></a>
# **getAllConversationsOfUser**
> ResponseEntity getAllConversationsOfUser()

Get all conversations of the current user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ConversationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ConversationApi apiInstance = new ConversationApi(defaultClient);
    try {
      ResponseEntity result = apiInstance.getAllConversationsOfUser();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConversationApi#getAllConversationsOfUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

