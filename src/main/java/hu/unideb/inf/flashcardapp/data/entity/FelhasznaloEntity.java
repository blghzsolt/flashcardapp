package hu.unideb.inf.flashcardapp.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class FelhasznaloEntity implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "name", nullable = false)
        private String nev;
        @Column(name = "email", nullable = false, unique = true)
        private String email;
        @Column(name = "username", unique = true, nullable = false)
        private String felhasznalonev;
        @Column(name = "password", nullable = false)
        private String jelszo;
        @ManyToMany(fetch = FetchType.EAGER)
        private List<JogosultsagEntity> jogosultsagok;
        @OneToMany(mappedBy = "felhasznalo")
        private List<CsomagEntity> csomagok;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return jogosultsagok;
    }

    @Override
    public String getPassword() {
        return jelszo;
    }

    @Override
    public String getUsername() {
        return felhasznalonev;
    }
}
