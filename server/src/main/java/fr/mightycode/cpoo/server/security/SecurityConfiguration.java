package fr.mightycode.cpoo.server.security;
import static org.springframework.security.config.Customizer.*;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;
import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(withDefaults());
    http.csrf(AbstractHttpConfigurer::disable);
    http.authorizeHttpRequests(authorizeRequests ->
      authorizeRequests
        .requestMatchers(antMatcher("/user/signup")).permitAll()
        .requestMatchers(antMatcher("/user/signin")).permitAll()
        .requestMatchers(antMatcher(HttpMethod.DELETE, "/user/*")).permitAll()
        .requestMatchers(antMatcher("/user/is-authenticated")).authenticated()
        .requestMatchers(antMatcher("/conversation/addConversation")).authenticated()
        .requestMatchers(antMatcher("/message/getAllMessagesOfConversation")).authenticated()
        .requestMatchers(antMatcher(HttpMethod.GET, "/user/{login}/status")).permitAll()
        .requestMatchers(antMatcher(HttpMethod.PUT, "/user/{login}/status/{status}")).permitAll()
        .requestMatchers(antMatcher("/message")).authenticated()
        .requestMatchers(antMatcher("/error")).permitAll()
        .requestMatchers(antMatcher("/status-updates")).permitAll()
        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
        .requestMatchers(antMatcher(HttpMethod.POST, "/message/{messageId}/reactions/{reaction}")).permitAll()
        .requestMatchers(antMatcher(HttpMethod.DELETE, "/message/{messageId}/reactions")).permitAll()
        .requestMatchers(antMatcher(HttpMethod.GET, "/message/{messageId}/reactions")).permitAll()
        .requestMatchers(antMatcher(HttpMethod.DELETE, "/message/{messageId}")).permitAll()
        .requestMatchers(antMatcher("/sse/stream")).permitAll()
        .anyRequest().authenticated());

    http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable()));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource, PasswordEncoder passwordEncoder) {
    UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
    UserDetails tester = User.withUsername("tester")
      .password(passwordEncoder.encode("tester"))
      .roles("USER")
      .build();
    userDetailsManager.createUser(tester);

    UserDetails admin = User.withUsername("admin")
      .password(passwordEncoder.encode("admin"))
      .roles("USER", "ADMIN")
      .build();
    userDetailsManager.createUser(admin);

    UserDetails alice = User.withUsername("alice")
      .password(passwordEncoder.encode("alice"))
      .roles("USER")
      .build();
    userDetailsManager.createUser(alice);

    UserDetails bob = User.withUsername("bob")
      .password(passwordEncoder.encode("bob"))
      .roles("USER")
      .build();
    userDetailsManager.createUser(bob);

    return userDetailsManager;
  }
}
