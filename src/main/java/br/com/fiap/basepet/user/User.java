package br.com.fiap.basepet.user;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "githubuser")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    Long id;
    String name;
    int telefone;
    String endereco;

    public static User convert(OAuth2User oAuth2User){
        return User.builder()
                    .id(Long.valueOf(oAuth2User.getName()))
                    .name(oAuth2User.getAttribute("name"))
                    .telefone(oAuth2User.getAttribute("telefone"))
                    .endereco(oAuth2User.getAttribute("endereco"))
                    .build()
                    ;
    }
}
