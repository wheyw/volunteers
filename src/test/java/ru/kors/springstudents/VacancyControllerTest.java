package ru.kors.springstudents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kors.springstudents.controller.VacancyController;
import ru.kors.springstudents.model.Vacancy;
import ru.kors.springstudents.service.VacancyService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VacancyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VacancyService vacancyService;

    @InjectMocks
    private VacancyController vacancyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vacancyController).build();
    }

    @Test
    public void testAddVacancy_Success() throws Exception {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1);
        vacancy.setEventId(100L);
        vacancy.setVolonterId(UUID.randomUUID());

        // Настройка mock-объектов
        doNothing().when(vacancyService).addVacancy(any(Vacancy.class));

        String vacancyJson = "{ \"eventId\": 100, \"volonterId\": \"" + vacancy.getVolonterId() + "\" }";

        mockMvc.perform(post("/api/v1/vacancies/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vacancyJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddVacancy_BadRequest() throws Exception {
        // Имитация ошибки валидации
        String invalidVacancyJson = "{ \"eventId\": \"invalid\", \"volonterId\": \"invalid-uuid\" }";

        mockMvc.perform(post("/api/v1/vacancies/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidVacancyJson))
                .andExpect(status().isBadRequest());
    }
}
