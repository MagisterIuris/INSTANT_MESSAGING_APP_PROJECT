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


package org.openapitools.client.model;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.openapitools.client.JSON;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * ResponseEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ResponseEntity {
  public static final String SERIALIZED_NAME_HEADERS = "headers";
  @SerializedName(SERIALIZED_NAME_HEADERS)
  private Object headers;

  public static final String SERIALIZED_NAME_MESSAGE = "message";
  @SerializedName(SERIALIZED_NAME_MESSAGE)
  private String message;

  public ResponseEntity() {
  }

  public ResponseEntity headers(Object headers) {

    this.headers = headers;
    return this;
  }

  /**
   * Get headers
   *
   * @return headers
   **/
  @javax.annotation.Nullable
  public Object getHeaders() {
    return headers;
  }


  public void setHeaders(Object headers) {
    this.headers = headers;
  }


  public ResponseEntity message(String message) {

    this.message = message;
    return this;
  }

  /**
   * Get message
   *
   * @return message
   **/
  @javax.annotation.Nullable
  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseEntity responseEntity = (ResponseEntity) o;
    return Objects.equals(this.headers, responseEntity.headers) &&
      Objects.equals(this.message, responseEntity.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(headers, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseEntity {\n");
    sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("headers");
    openapiFields.add("message");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ResponseEntity
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
    if (jsonElement == null) {
      if (!ResponseEntity.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
        throw new IllegalArgumentException(String.format("The required field(s) %s in ResponseEntity is not found in the empty JSON string", ResponseEntity.openapiRequiredFields.toString()));
      }
    }

    Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
    // check to see if the JSON string contains additional fields
    for (Map.Entry<String, JsonElement> entry : entries) {
      if (!ResponseEntity.openapiFields.contains(entry.getKey())) {
        throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ResponseEntity` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
      }
    }
    JsonObject jsonObj = jsonElement.getAsJsonObject();
    if ((jsonObj.get("message") != null && !jsonObj.get("message").isJsonNull()) && !jsonObj.get("message").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `message` to be a primitive type in the JSON string but got `%s`", jsonObj.get("message").toString()));
    }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (!ResponseEntity.class.isAssignableFrom(type.getRawType())) {
        return null; // this class only serializes 'ResponseEntity' and its subtypes
      }
      final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
      final TypeAdapter<ResponseEntity> thisAdapter
        = gson.getDelegateAdapter(this, TypeToken.get(ResponseEntity.class));

      return (TypeAdapter<T>) new TypeAdapter<ResponseEntity>() {
        @Override
        public void write(JsonWriter out, ResponseEntity value) throws IOException {
          JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
          elementAdapter.write(out, obj);
        }

        @Override
        public ResponseEntity read(JsonReader in) throws IOException {
          JsonElement jsonElement = elementAdapter.read(in);
          validateJsonElement(jsonElement);
          return thisAdapter.fromJsonTree(jsonElement);
        }

      }.nullSafe();
    }
  }

  /**
   * Create an instance of ResponseEntity given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ResponseEntity
   * @throws IOException if the JSON string is invalid with respect to ResponseEntity
   */
  public static ResponseEntity fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ResponseEntity.class);
  }

  /**
   * Convert an instance of ResponseEntity to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

