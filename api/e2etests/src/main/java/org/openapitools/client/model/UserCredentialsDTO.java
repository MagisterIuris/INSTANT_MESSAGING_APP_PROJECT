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
 * UserCredentialsDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class UserCredentialsDTO {
  public static final String SERIALIZED_NAME_LOGIN = "login";
  @SerializedName(SERIALIZED_NAME_LOGIN)
  private String login;

  public static final String SERIALIZED_NAME_PASSWORD = "password";
  @SerializedName(SERIALIZED_NAME_PASSWORD)
  private String password;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_NOM = "nom";
  @SerializedName(SERIALIZED_NAME_NOM)
  private String nom;

  public static final String SERIALIZED_NAME_PRENOM = "prenom";
  @SerializedName(SERIALIZED_NAME_PRENOM)
  private String prenom;

  public static final String SERIALIZED_NAME_DATE_DE_NAISSANCE = "dateDeNaissance";
  @SerializedName(SERIALIZED_NAME_DATE_DE_NAISSANCE)
  private String dateDeNaissance;

  public static final String SERIALIZED_NAME_PHOTO = "photo";
  @SerializedName(SERIALIZED_NAME_PHOTO)
  private String photo;

  public UserCredentialsDTO() {
  }

  public UserCredentialsDTO login(String login) {

    this.login = login;
    return this;
  }

  /**
   * Get login
   *
   * @return login
   **/
  @javax.annotation.Nullable
  public String getLogin() {
    return login;
  }


  public void setLogin(String login) {
    this.login = login;
  }


  public UserCredentialsDTO password(String password) {

    this.password = password;
    return this;
  }

  /**
   * Get password
   *
   * @return password
   **/
  @javax.annotation.Nullable
  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public UserCredentialsDTO email(String email) {

    this.email = email;
    return this;
  }

  /**
   * Get email
   *
   * @return email
   **/
  @javax.annotation.Nullable
  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public UserCredentialsDTO nom(String nom) {

    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   *
   * @return nom
   **/
  @javax.annotation.Nullable
  public String getNom() {
    return nom;
  }


  public void setNom(String nom) {
    this.nom = nom;
  }


  public UserCredentialsDTO prenom(String prenom) {

    this.prenom = prenom;
    return this;
  }

  /**
   * Get prenom
   *
   * @return prenom
   **/
  @javax.annotation.Nullable
  public String getPrenom() {
    return prenom;
  }


  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }


  public UserCredentialsDTO dateDeNaissance(String dateDeNaissance) {

    this.dateDeNaissance = dateDeNaissance;
    return this;
  }

  /**
   * Get dateDeNaissance
   *
   * @return dateDeNaissance
   **/
  @javax.annotation.Nullable
  public String getDateDeNaissance() {
    return dateDeNaissance;
  }


  public void setDateDeNaissance(String dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
  }


  public UserCredentialsDTO photo(String photo) {

    this.photo = photo;
    return this;
  }

  /**
   * Get photo
   *
   * @return photo
   **/
  @javax.annotation.Nullable
  public String getPhoto() {
    return photo;
  }


  public void setPhoto(String photo) {
    this.photo = photo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCredentialsDTO userCredentialsDTO = (UserCredentialsDTO) o;
    return Objects.equals(this.login, userCredentialsDTO.login) &&
      Objects.equals(this.password, userCredentialsDTO.password) &&
      Objects.equals(this.email, userCredentialsDTO.email) &&
      Objects.equals(this.nom, userCredentialsDTO.nom) &&
      Objects.equals(this.prenom, userCredentialsDTO.prenom) &&
      Objects.equals(this.dateDeNaissance, userCredentialsDTO.dateDeNaissance) &&
      Objects.equals(this.photo, userCredentialsDTO.photo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password, email, nom, prenom, dateDeNaissance, photo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCredentialsDTO {\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    dateDeNaissance: ").append(toIndentedString(dateDeNaissance)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
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
    openapiFields.add("login");
    openapiFields.add("password");
    openapiFields.add("email");
    openapiFields.add("nom");
    openapiFields.add("prenom");
    openapiFields.add("dateDeNaissance");
    openapiFields.add("photo");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to UserCredentialsDTO
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
    if (jsonElement == null) {
      if (!UserCredentialsDTO.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
        throw new IllegalArgumentException(String.format("The required field(s) %s in UserCredentialsDTO is not found in the empty JSON string", UserCredentialsDTO.openapiRequiredFields.toString()));
      }
    }

    Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
    // check to see if the JSON string contains additional fields
    for (Map.Entry<String, JsonElement> entry : entries) {
      if (!UserCredentialsDTO.openapiFields.contains(entry.getKey())) {
        throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `UserCredentialsDTO` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
      }
    }
    JsonObject jsonObj = jsonElement.getAsJsonObject();
    if ((jsonObj.get("login") != null && !jsonObj.get("login").isJsonNull()) && !jsonObj.get("login").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `login` to be a primitive type in the JSON string but got `%s`", jsonObj.get("login").toString()));
    }
    if ((jsonObj.get("password") != null && !jsonObj.get("password").isJsonNull()) && !jsonObj.get("password").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `password` to be a primitive type in the JSON string but got `%s`", jsonObj.get("password").toString()));
    }
    if ((jsonObj.get("email") != null && !jsonObj.get("email").isJsonNull()) && !jsonObj.get("email").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
    }
    if ((jsonObj.get("nom") != null && !jsonObj.get("nom").isJsonNull()) && !jsonObj.get("nom").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `nom` to be a primitive type in the JSON string but got `%s`", jsonObj.get("nom").toString()));
    }
    if ((jsonObj.get("prenom") != null && !jsonObj.get("prenom").isJsonNull()) && !jsonObj.get("prenom").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `prenom` to be a primitive type in the JSON string but got `%s`", jsonObj.get("prenom").toString()));
    }
    if ((jsonObj.get("dateDeNaissance") != null && !jsonObj.get("dateDeNaissance").isJsonNull()) && !jsonObj.get("dateDeNaissance").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `dateDeNaissance` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dateDeNaissance").toString()));
    }
    if ((jsonObj.get("photo") != null && !jsonObj.get("photo").isJsonNull()) && !jsonObj.get("photo").isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `photo` to be a primitive type in the JSON string but got `%s`", jsonObj.get("photo").toString()));
    }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (!UserCredentialsDTO.class.isAssignableFrom(type.getRawType())) {
        return null; // this class only serializes 'UserCredentialsDTO' and its subtypes
      }
      final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
      final TypeAdapter<UserCredentialsDTO> thisAdapter
        = gson.getDelegateAdapter(this, TypeToken.get(UserCredentialsDTO.class));

      return (TypeAdapter<T>) new TypeAdapter<UserCredentialsDTO>() {
        @Override
        public void write(JsonWriter out, UserCredentialsDTO value) throws IOException {
          JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
          elementAdapter.write(out, obj);
        }

        @Override
        public UserCredentialsDTO read(JsonReader in) throws IOException {
          JsonElement jsonElement = elementAdapter.read(in);
          validateJsonElement(jsonElement);
          return thisAdapter.fromJsonTree(jsonElement);
        }

      }.nullSafe();
    }
  }

  /**
   * Create an instance of UserCredentialsDTO given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of UserCredentialsDTO
   * @throws IOException if the JSON string is invalid with respect to UserCredentialsDTO
   */
  public static UserCredentialsDTO fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, UserCredentialsDTO.class);
  }

  /**
   * Convert an instance of UserCredentialsDTO to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
