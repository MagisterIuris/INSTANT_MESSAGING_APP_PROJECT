/*
 * Buzzchat server API
 * Instant Messaging App server API
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: aymane.menfaa@insa-rennes.fr
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import com.google.gson.reflect.TypeToken;
import org.openapitools.client.*;
import org.openapitools.client.model.MessageDTO;
import org.openapitools.client.model.NewMessageDTO;
import org.openapitools.client.model.ResponseEntity;
import org.openapitools.client.model.UserProfileDTO;

import java.lang.reflect.Type;
import java.util.*;

public class MessageApi {
  private ApiClient localVarApiClient;
  private int localHostIndex;
  private String localCustomBaseUrl;

  public MessageApi() {
    this(Configuration.getDefaultApiClient());
  }

  public MessageApi(ApiClient apiClient) {
    this.localVarApiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return localVarApiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.localVarApiClient = apiClient;
  }

  public int getHostIndex() {
    return localHostIndex;
  }

  public void setHostIndex(int hostIndex) {
    this.localHostIndex = hostIndex;
  }

  public String getCustomBaseUrl() {
    return localCustomBaseUrl;
  }

  public void setCustomBaseUrl(String customBaseUrl) {
    this.localCustomBaseUrl = customBaseUrl;
  }

  /**
   * Build call for addReaction
   *
   * @param messageId (required)
   * @param reaction  (required)
   * @param _callback Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call addReactionCall(UUID messageId, String reaction, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath = "/message/{messageId}/reactions/{reaction}"
      .replace("{" + "messageId" + "}", localVarApiClient.escapeString(messageId.toString()))
      .replace("{" + "reaction" + "}", localVarApiClient.escapeString(reaction.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call addReactionValidateBeforeCall(UUID messageId, String reaction, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'messageId' is set
    if (messageId == null) {
      throw new ApiException("Missing the required parameter 'messageId' when calling addReaction(Async)");
    }

    // verify the required parameter 'reaction' is set
    if (reaction == null) {
      throw new ApiException("Missing the required parameter 'reaction' when calling addReaction(Async)");
    }

    return addReactionCall(messageId, reaction, _callback);

  }

  /**
   * Add reaction to a message
   *
   * @param messageId (required)
   * @param reaction  (required)
   * @return ResponseEntity
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ResponseEntity addReaction(UUID messageId, String reaction) throws ApiException {
    ApiResponse<ResponseEntity> localVarResp = addReactionWithHttpInfo(messageId, reaction);
    return localVarResp.getData();
  }

  /**
   * Add reaction to a message
   *
   * @param messageId (required)
   * @param reaction  (required)
   * @return ApiResponse&lt;ResponseEntity&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<ResponseEntity> addReactionWithHttpInfo(UUID messageId, String reaction) throws ApiException {
    okhttp3.Call localVarCall = addReactionValidateBeforeCall(messageId, reaction, null);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Add reaction to a message (asynchronously)
   *
   * @param messageId (required)
   * @param reaction  (required)
   * @param _callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call addReactionAsync(UUID messageId, String reaction, final ApiCallback<ResponseEntity> _callback) throws ApiException {

    okhttp3.Call localVarCall = addReactionValidateBeforeCall(messageId, reaction, _callback);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }

  /**
   * Build call for deleteMessage
   *
   * @param messageId (required)
   * @param _callback Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call deleteMessageCall(UUID messageId, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath = "/message/{messageId}"
      .replace("{" + "messageId" + "}", localVarApiClient.escapeString(messageId.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call deleteMessageValidateBeforeCall(UUID messageId, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'messageId' is set
    if (messageId == null) {
      throw new ApiException("Missing the required parameter 'messageId' when calling deleteMessage(Async)");
    }

    return deleteMessageCall(messageId, _callback);

  }

  /**
   * Delete a message
   *
   * @param messageId (required)
   * @return ResponseEntity
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ResponseEntity deleteMessage(UUID messageId) throws ApiException {
    ApiResponse<ResponseEntity> localVarResp = deleteMessageWithHttpInfo(messageId);
    return localVarResp.getData();
  }

  /**
   * Delete a message
   *
   * @param messageId (required)
   * @return ApiResponse&lt;ResponseEntity&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<ResponseEntity> deleteMessageWithHttpInfo(UUID messageId) throws ApiException {
    okhttp3.Call localVarCall = deleteMessageValidateBeforeCall(messageId, null);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Delete a message (asynchronously)
   *
   * @param messageId (required)
   * @param _callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call deleteMessageAsync(UUID messageId, final ApiCallback<ResponseEntity> _callback) throws ApiException {

    okhttp3.Call localVarCall = deleteMessageValidateBeforeCall(messageId, _callback);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }

  /**
   * Build call for getAllMessagesOfConversation
   *
   * @param userProfileDTO (required)
   * @param _callback      Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call getAllMessagesOfConversationCall(UserProfileDTO userProfileDTO, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = userProfileDTO;

    // create path and map variables
    String localVarPath = "/message/getAllMessagesOfConversation";

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call getAllMessagesOfConversationValidateBeforeCall(UserProfileDTO userProfileDTO, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'userProfileDTO' is set
    if (userProfileDTO == null) {
      throw new ApiException("Missing the required parameter 'userProfileDTO' when calling getAllMessagesOfConversation(Async)");
    }

    return getAllMessagesOfConversationCall(userProfileDTO, _callback);

  }

  /**
   * Get all messages of a conversation
   *
   * @param userProfileDTO (required)
   * @return ResponseEntity
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ResponseEntity getAllMessagesOfConversation(UserProfileDTO userProfileDTO) throws ApiException {
    ApiResponse<ResponseEntity> localVarResp = getAllMessagesOfConversationWithHttpInfo(userProfileDTO);
    return localVarResp.getData();
  }

  /**
   * Get all messages of a conversation
   *
   * @param userProfileDTO (required)
   * @return ApiResponse&lt;ResponseEntity&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<ResponseEntity> getAllMessagesOfConversationWithHttpInfo(UserProfileDTO userProfileDTO) throws ApiException {
    okhttp3.Call localVarCall = getAllMessagesOfConversationValidateBeforeCall(userProfileDTO, null);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Get all messages of a conversation (asynchronously)
   *
   * @param userProfileDTO (required)
   * @param _callback      The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call getAllMessagesOfConversationAsync(UserProfileDTO userProfileDTO, final ApiCallback<ResponseEntity> _callback) throws ApiException {

    okhttp3.Call localVarCall = getAllMessagesOfConversationValidateBeforeCall(userProfileDTO, _callback);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }

  /**
   * Build call for getLastMessageOfConversation
   *
   * @param userProfileDTO (required)
   * @param _callback      Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call getLastMessageOfConversationCall(UserProfileDTO userProfileDTO, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = userProfileDTO;

    // create path and map variables
    String localVarPath = "/message/getLastMessageOfConversation";

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call getLastMessageOfConversationValidateBeforeCall(UserProfileDTO userProfileDTO, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'userProfileDTO' is set
    if (userProfileDTO == null) {
      throw new ApiException("Missing the required parameter 'userProfileDTO' when calling getLastMessageOfConversation(Async)");
    }

    return getLastMessageOfConversationCall(userProfileDTO, _callback);

  }

  /**
   * Get the last messages of a conversation
   *
   * @param userProfileDTO (required)
   * @return ResponseEntity
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ResponseEntity getLastMessageOfConversation(UserProfileDTO userProfileDTO) throws ApiException {
    ApiResponse<ResponseEntity> localVarResp = getLastMessageOfConversationWithHttpInfo(userProfileDTO);
    return localVarResp.getData();
  }

  /**
   * Get the last messages of a conversation
   *
   * @param userProfileDTO (required)
   * @return ApiResponse&lt;ResponseEntity&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<ResponseEntity> getLastMessageOfConversationWithHttpInfo(UserProfileDTO userProfileDTO) throws ApiException {
    okhttp3.Call localVarCall = getLastMessageOfConversationValidateBeforeCall(userProfileDTO, null);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Get the last messages of a conversation (asynchronously)
   *
   * @param userProfileDTO (required)
   * @param _callback      The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call getLastMessageOfConversationAsync(UserProfileDTO userProfileDTO, final ApiCallback<ResponseEntity> _callback) throws ApiException {

    okhttp3.Call localVarCall = getLastMessageOfConversationValidateBeforeCall(userProfileDTO, _callback);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }

  /**
   * Build call for getReaction
   *
   * @param messageId (required)
   * @param _callback Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call getReactionCall(UUID messageId, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath = "/message/{messageId}/reactions"
      .replace("{" + "messageId" + "}", localVarApiClient.escapeString(messageId.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call getReactionValidateBeforeCall(UUID messageId, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'messageId' is set
    if (messageId == null) {
      throw new ApiException("Missing the required parameter 'messageId' when calling getReaction(Async)");
    }

    return getReactionCall(messageId, _callback);

  }

  /**
   * Get reactions of a message
   *
   * @param messageId (required)
   * @return ResponseEntity
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ResponseEntity getReaction(UUID messageId) throws ApiException {
    ApiResponse<ResponseEntity> localVarResp = getReactionWithHttpInfo(messageId);
    return localVarResp.getData();
  }

  /**
   * Get reactions of a message
   *
   * @param messageId (required)
   * @return ApiResponse&lt;ResponseEntity&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<ResponseEntity> getReactionWithHttpInfo(UUID messageId) throws ApiException {
    okhttp3.Call localVarCall = getReactionValidateBeforeCall(messageId, null);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Get reactions of a message (asynchronously)
   *
   * @param messageId (required)
   * @param _callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call getReactionAsync(UUID messageId, final ApiCallback<ResponseEntity> _callback) throws ApiException {

    okhttp3.Call localVarCall = getReactionValidateBeforeCall(messageId, _callback);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }

  /**
   * Build call for messagePost
   *
   * @param newMessageDTO (required)
   * @param _callback     Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call messagePostCall(NewMessageDTO newMessageDTO, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = newMessageDTO;

    // create path and map variables
    String localVarPath = "/message";

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call messagePostValidateBeforeCall(NewMessageDTO newMessageDTO, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'newMessageDTO' is set
    if (newMessageDTO == null) {
      throw new ApiException("Missing the required parameter 'newMessageDTO' when calling messagePost(Async)");
    }

    return messagePostCall(newMessageDTO, _callback);

  }

  /**
   * Create a new message
   *
   * @param newMessageDTO (required)
   * @return MessageDTO
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public MessageDTO messagePost(NewMessageDTO newMessageDTO) throws ApiException {
    ApiResponse<MessageDTO> localVarResp = messagePostWithHttpInfo(newMessageDTO);
    return localVarResp.getData();
  }

  /**
   * Create a new message
   *
   * @param newMessageDTO (required)
   * @return ApiResponse&lt;MessageDTO&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<MessageDTO> messagePostWithHttpInfo(NewMessageDTO newMessageDTO) throws ApiException {
    okhttp3.Call localVarCall = messagePostValidateBeforeCall(newMessageDTO, null);
    Type localVarReturnType = new TypeToken<MessageDTO>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Create a new message (asynchronously)
   *
   * @param newMessageDTO (required)
   * @param _callback     The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call messagePostAsync(NewMessageDTO newMessageDTO, final ApiCallback<MessageDTO> _callback) throws ApiException {

    okhttp3.Call localVarCall = messagePostValidateBeforeCall(newMessageDTO, _callback);
    Type localVarReturnType = new TypeToken<MessageDTO>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }

  /**
   * Build call for removeReaction
   *
   * @param messageId (required)
   * @param _callback Callback for upload/download progress
   * @return Call to execute
   * @throws ApiException If fail to serialize the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call removeReactionCall(UUID messageId, final ApiCallback _callback) throws ApiException {
    String basePath = null;
    // Operation Servers
    String[] localBasePaths = new String[]{};

    // Determine Base Path to Use
    if (localCustomBaseUrl != null) {
      basePath = localCustomBaseUrl;
    } else if (localBasePaths.length > 0) {
      basePath = localBasePaths[localHostIndex];
    } else {
      basePath = null;
    }

    Object localVarPostBody = null;

    // create path and map variables
    String localVarPath = "/message/{messageId}/reactions"
      .replace("{" + "messageId" + "}", localVarApiClient.escapeString(messageId.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) {
      localVarHeaderParams.put("Accept", localVarAccept);
    }

    final String[] localVarContentTypes = {
    };
    final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
    if (localVarContentType != null) {
      localVarHeaderParams.put("Content-Type", localVarContentType);
    }

    String[] localVarAuthNames = new String[]{"CookieAuth"};
    return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
  }

  @SuppressWarnings("rawtypes")
  private okhttp3.Call removeReactionValidateBeforeCall(UUID messageId, final ApiCallback _callback) throws ApiException {
    // verify the required parameter 'messageId' is set
    if (messageId == null) {
      throw new ApiException("Missing the required parameter 'messageId' when calling removeReaction(Async)");
    }

    return removeReactionCall(messageId, _callback);

  }

  /**
   * Remove reaction from a message
   *
   * @param messageId (required)
   * @return ResponseEntity
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ResponseEntity removeReaction(UUID messageId) throws ApiException {
    ApiResponse<ResponseEntity> localVarResp = removeReactionWithHttpInfo(messageId);
    return localVarResp.getData();
  }

  /**
   * Remove reaction from a message
   *
   * @param messageId (required)
   * @return ApiResponse&lt;ResponseEntity&gt;
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public ApiResponse<ResponseEntity> removeReactionWithHttpInfo(UUID messageId) throws ApiException {
    okhttp3.Call localVarCall = removeReactionValidateBeforeCall(messageId, null);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    return localVarApiClient.execute(localVarCall, localVarReturnType);
  }

  /**
   * Remove reaction from a message (asynchronously)
   *
   * @param messageId (required)
   * @param _callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   * @http.response.details <table summary="Response Details" border="1">
   * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
   * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
   * </table>
   */
  public okhttp3.Call removeReactionAsync(UUID messageId, final ApiCallback<ResponseEntity> _callback) throws ApiException {

    okhttp3.Call localVarCall = removeReactionValidateBeforeCall(messageId, _callback);
    Type localVarReturnType = new TypeToken<ResponseEntity>() {
    }.getType();
    localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
    return localVarCall;
  }
}
