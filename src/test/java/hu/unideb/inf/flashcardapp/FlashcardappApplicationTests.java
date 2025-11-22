package hu.unideb.inf.flashcardapp;

import hu.unideb.inf.flashcardapp.controller.CsomagController;
import hu.unideb.inf.flashcardapp.data.entity.FelhasznaloEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import hu.unideb.inf.flashcardapp.data.repository.FelhasznaloRepository;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;



@SpringBootTest
@AutoConfigureMockMvc
class FlashcardappApplicationTests {

    @Autowired
    private CsomagController csomagController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

    @Test
    void csomagControllerLoads() {
        assertNotNull(csomagController);
    }

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        mockMvc.perform(post("/auth/regisztracio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "nev": "Gipsz Jakab",
                      "email": "regisztracio@teszt.hu",
                      "felhasznalonev": "registeruser",
                      "jelszo": "12345"
                    }
                """))
                .andExpect(status().isOk());
    }

    @Test
    void login_withExistingUser_shouldReturnOk() throws Exception {

        FelhasznaloEntity user = new FelhasznaloEntity();
        user.setNev("Teszt Elek");
        user.setEmail("bejelentkezes@teszt.hu");
        user.setFelhasznalonev("loginuser");
        user.setJelszo(passwordEncoder.encode("12345"));
        felhasznaloRepository.save(user);

        mockMvc.perform(post("/auth/bejelentkezes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "felhasznalonev": "loginuser",
              "jelszo": "12345"
            }
            """))
                .andExpect(status().isOk());
    }


}
