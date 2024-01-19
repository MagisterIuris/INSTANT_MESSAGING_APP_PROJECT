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
import okhttp3.OkHttpClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.ResponseEntity;
import org.openapitools.client.model.UserCredentialsDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * API tests for AuthenticationApi
 */

public class AuthenticationApiTest {
  private final AuthenticationApi api = new AuthenticationApi();
  private final UserApi apiAuth = new UserApi();
  private MonCookieJar MonCookie;

  @BeforeEach
  public void init() throws ApiException {
    MonCookie = new MonCookieJar();
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    OkHttpClient okHttpClient = builder.cookieJar(MonCookie).build();
    ApiClient apiClient = new ApiClient(okHttpClient);
    api.setApiClient(apiClient);
  }

  /**
   * Check if the user is authenticated
   *
   * @throws ApiException if the Api call fails
   */
  @Test
  public void isAuthenticatedTest() throws ApiException {
    UserCredentialsDTO userCredentialsDTOvalide = new UserCredentialsDTO();
    userCredentialsDTOvalide
      .login("aymane")
      .password("pwd")
      .email("aymane@buzzchat.com")
      .dateDeNaissance("26/02/2002")
      .prenom("Aymane")
      .nom("Menfaa");

    api.signup(userCredentialsDTOvalide);
    api.signin(userCredentialsDTOvalide);
    boolean response = api.isAuthenticated();
    assertTrue(response);
    apiAuth.delete("aymane");
  }

  /**
   * Sign in a user
   *
   * @throws ApiException if the Api call fails
   */
  @Test
  public void signinTest() throws ApiException {
    UserCredentialsDTO userCredentialsDTOvalide = new UserCredentialsDTO();
    userCredentialsDTOvalide
      .login("hamza")
      .password("pwd")
      .email("hamza@buzzchat.com")
      .dateDeNaissance("26/02/2002")
      .prenom("Hamza")
      .nom("Azer")
      .photo("assets/photo.png");

    UserCredentialsDTO userCredentialsDTOinvalide = new UserCredentialsDTO();
    userCredentialsDTOinvalide
      .login("noha")
      .password("pwd")
      .email("noha@buzzchat.com")
      .dateDeNaissance("26/02/2002")
      .prenom("Noha")
      .nom("Doaif")
      .photo("assets/photo.png");
    try {
      ResponseEntity response = api.signin(userCredentialsDTOinvalide);
      Assertions.fail();
    }
    catch (ApiException e) {
      assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
    }

    api.signup(userCredentialsDTOvalide);
    api.signin(userCredentialsDTOvalide);

    try {
      ResponseEntity response2 = api.signin(userCredentialsDTOvalide);
      Assertions.fail();
    }
    catch (ApiException e) {
      assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
    }
    apiAuth.delete("hamza");
  }

  /**
   * Sign out the current user
   *
   * @throws ApiException if the Api call fails
   */
  @Test
  public void signoutTest() throws ApiException {
    UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO()
      .login("zakaria")
      .password("pwd")
      .email("zakaria@buzzchat.com")
      .dateDeNaissance("26/02/2002")
      .prenom("Zakaria")
      .nom("Ehd")
      .photo("assets/photo.png");

    api.signup(userCredentialsDTO);
    api.signin(userCredentialsDTO);
    apiAuth.delete("zakaria");
  }

  /**
   * Sign up a new user
   *
   * @throws ApiException if the Api call fails
   */
  @Test
  public void signupTest() throws ApiException {
    UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO()
      .login("aymane")
      .password("pwd")
      .email("aymane@buzzchat.com")
      .dateDeNaissance("26/02/2002")
      .prenom("Aymane")
      .nom("Menfaa")
      .photo("assets/photo.png");
    ResponseEntity response = api.signup(userCredentialsDTO);
    try {
      ResponseEntity response2 = api.signup(userCredentialsDTO);
      Assertions.fail();
    }
    catch (ApiException e) {
      assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
    }
    api.signin(userCredentialsDTO);
    apiAuth.delete("aymane");
  }

}