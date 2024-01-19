# MessageApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addReaction**](MessageApi.md#addReaction) | **POST** /message/{messageId}/reactions/{reaction} | Add reaction to a message |
| [**deleteMessage**](MessageApi.md#deleteMessage) | **DELETE** /message/{messageId} | Delete a message |
| [**getAllMessagesOfConversation**](MessageApi.md#getAllMessagesOfConversation) | **POST** /message/getAllMessagesOfConversation | Get all messages of a conversation |
| [**getLastMessageOfConversation**](MessageApi.md#getLastMessageOfConversation) | **POST** /message/getLastMessageOfConversation | Get the last messages of a conversation |
| [**getReaction**](MessageApi.md#getReaction) | **GET** /message/{messageId}/reactions | Get reactions of a message |
| [**messagePost**](MessageApi.md#messagePost) | **POST** /message | Create a new message |
| [**removeReaction**](MessageApi.md#removeReaction) | **DELETE** /message/{messageId}/reactions | Remove reaction from a message |


<a id="addReaction"></a>
# **addReaction**
> ResponseEntity addReaction(messageId, reaction)

Add reaction to a message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UUID messageId = UUID.randomUUID(); // UUID | 
    String reaction = "reaction_example"; // String | 
    try {
      ResponseEntity result = apiInstance.addReaction(messageId, reaction);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#addReaction");
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
| **messageId** | **UUID**|  | |
| **reaction** | **String**|  | |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="deleteMessage"></a>
# **deleteMessage**
> ResponseEntity deleteMessage(messageId)

Delete a message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UUID messageId = UUID.randomUUID(); // UUID | 
    try {
      ResponseEntity result = apiInstance.deleteMessage(messageId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#deleteMessage");
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
| **messageId** | **UUID**|  | |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="getAllMessagesOfConversation"></a>
# **getAllMessagesOfConversation**
> ResponseEntity getAllMessagesOfConversation(userProfileDTO)

Get all messages of a conversation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UserProfileDTO userProfileDTO = new UserProfileDTO(); // UserProfileDTO | 
    try {
      ResponseEntity result = apiInstance.getAllMessagesOfConversation(userProfileDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#getAllMessagesOfConversation");
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

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="getLastMessageOfConversation"></a>
# **getLastMessageOfConversation**
> ResponseEntity getLastMessageOfConversation(userProfileDTO)

Get the last messages of a conversation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UserProfileDTO userProfileDTO = new UserProfileDTO(); // UserProfileDTO | 
    try {
      ResponseEntity result = apiInstance.getLastMessageOfConversation(userProfileDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#getLastMessageOfConversation");
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

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="getReaction"></a>
# **getReaction**
> ResponseEntity getReaction(messageId)

Get reactions of a message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UUID messageId = UUID.randomUUID(); // UUID | 
    try {
      ResponseEntity result = apiInstance.getReaction(messageId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#getReaction");
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
| **messageId** | **UUID**|  | |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="messagePost"></a>
# **messagePost**
> MessageDTO messagePost(newMessageDTO)

Create a new message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    NewMessageDTO newMessageDTO = new NewMessageDTO(); // NewMessageDTO | 
    try {
      MessageDTO result = apiInstance.messagePost(newMessageDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#messagePost");
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
| **newMessageDTO** | [**NewMessageDTO**](NewMessageDTO.md)|  | |

### Return type

[**MessageDTO**](MessageDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="removeReaction"></a>
# **removeReaction**
> ResponseEntity removeReaction(messageId)

Remove reaction from a message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    UUID messageId = UUID.randomUUID(); // UUID | 
    try {
      ResponseEntity result = apiInstance.removeReaction(messageId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#removeReaction");
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
| **messageId** | **UUID**|  | |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

