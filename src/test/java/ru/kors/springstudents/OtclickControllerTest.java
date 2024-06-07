package ru.kors.springstudents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kors.springstudents.controller.OtclickController;
import ru.kors.springstudents.service.OtclickService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OtclickControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OtclickService otclickService;

    @InjectMocks
    private OtclickController otclickController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(otclickController).build();
    }

    @Test
    public void testSetCameStatus_Success() throws Exception {
        int otclickId = 1;
        boolean cameStatus = true;

        // Настройка mock-объектов
        doNothing().when(otclickService).setCameStatus(otclickId, cameStatus);

        mockMvc.perform(put("/api/v1/otclicks/came")
                        .param("id", String.valueOf(otclickId))
                        .param("status", String.valueOf(cameStatus))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSetCameStatus_BadRequest() throws Exception {
        // Отправляем запрос с некорректным параметром id
        mockMvc.perform(put("/api/v1/otclicks/came")
                        .param("id", "invalid-id")
                        .param("status", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
