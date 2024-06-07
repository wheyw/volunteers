package ru.kors.springstudents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.kors.springstudents.controller.VacancyController;
import ru.kors.springstudents.model.Vacancy;
import ru.kors.springstudents.service.VacancyService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VacancyControllerTest {

    @Mock
    private VacancyService service;

    @InjectMocks
    private VacancyController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllVacancies_ShouldReturnLockedStatus_WhenNoVacanciesExist() {
        when(service.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Vacancy>> response = controller.getAllVacancies();

        assertEquals(HttpStatus.LOCKED, response.getStatusCode());
        verify(service, times(1)).getAll();
    }

    @Test
    void getVacanciesViaEvent_ShouldReturnLockedStatus_WhenNoVacanciesExist() {
        Integer eventId = 1;
        when(service.getVacanciesViaEvent(eventId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Vacancy>> response = controller.getVacanciesViaEvent(eventId);

        assertEquals(HttpStatus.LOCKED, response.getStatusCode());
        verify(service, times(1)).getVacanciesViaEvent(eventId);
    }

    @Test
    void addVacancy_ShouldReturnBadRequest_WhenBindingResultHasErrors() {
        var bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = controller.addVacancy(new Vacancy(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(service, never()).addVacancy(any(Vacancy.class));
    }
}
