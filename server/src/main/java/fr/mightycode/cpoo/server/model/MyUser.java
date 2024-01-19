package fr.mightycode.cpoo.server.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "MyUser")
public class MyUser {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID id;

  @Column(name = "login", nullable = false, unique = true)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "nom", nullable = false)
  private String nom;

  @Column(name = "prenom", nullable = false)
  private String prenom;

  @Column(name = "date_de_naissance", nullable = false)
  private String dateDeNaissance;

  @Column(name = "photo")
  private String photo;

  @Column(name = "preference_mode")
  private String preferenceMode;

  @Column(name = "preference_langue")
  private String preferenceLangue;

  @Column(name = "status")
  private String status;

  @Column(name = "domaine")
  private String domaine;

  @ManyToMany
  @JoinTable(
    name = "user_relations",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "related_user_id")
  )
  private List<MyUser> contacts;

  public MyUser() {}

}

