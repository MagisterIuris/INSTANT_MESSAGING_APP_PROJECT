package fr.mightycode.cpoo.server;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class ServerApplicationTests {
  @Autowired
  private MockMvc mvc;
  @Autowired
  private WebTestClient webClient;
  @Test
  void testSignUpSignInSignOut() throws Exception {

    mvc.perform(post("/user/signup")
        .contentType(APPLICATION_JSON)
        .content("""
          {
            "login": "admin",
            "password": "admin"
          }"""))
      .andExpect(status().isConflict());

    mvc.perform(post("/user/signup")
        .contentType(APPLICATION_JSON)
        .content("""
          {
            "login": "test",
            "email": "test@buzzchat.com",
            "password": "monmotdepasse",
            "nom": "test",
            "prenom": "test",
            "dateDeNaissance": "01/01/1990"
          }"""))
      .andExpect(status().isOk());

    mvc.perform(post("/user/signup")
        .contentType(APPLICATION_JSON)
        .content("""
          {
            "login": "test",
            "email": "test@buzzchat.com",
            "password": "monmotdepasse",
            "nom": "test",
            "prenom": "test",
            "dateDeNaissance": "01/01/1990"
          }"""))
      .andExpect(status().isConflict());
    webClient.post()
      .uri("/user/signin")
      .contentType(APPLICATION_JSON)
      .bodyValue("""
        {
          "login": "test",
          "password": "monmotdepasse"
        }""")
      .exchange()
      .expectStatus().isOk();

    mvc.perform(delete("/user/test"));

    webClient.post()
      .uri("/user/signin")
      .contentType(APPLICATION_JSON)
      .bodyValue("""
        {
          "login": "user",
          "password": "invalid"
        }""")
      .exchange()
      .expectStatus().isUnauthorized();

    mvc.perform(post("/user/signup")
        .contentType(APPLICATION_JSON)
        .content("""
          {
            "login": "ad",
            "email": "ad@buzzchat.com",
            "password": "ad",
            "nom": "ad",
            "prenom": "ad",
            "dateDeNaissance": "01/01/1990"
          }"""))
      .andExpect(status().isOk());

    webClient.post()
      .uri("/user/signin")
      .contentType(APPLICATION_JSON)
      .bodyValue("""
        {
          "login": "ad",
          "password": "ad"
        }""")
      .exchange()
      .expectStatus().isOk();
    mvc.perform(delete("/user/ad"));
  }
}
