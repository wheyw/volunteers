package ru.kors.springstudents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.kors.springstudents.controller.OtclickController;
import ru.kors.springstudents.model.Otclick;
import ru.kors.springstudents.service.OtclickService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OtclickControllerTest {

    @Mock
    private OtclickService service;

    @InjectMocks
    private OtclickController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ShouldReturnOkStatus_WhenOtclicksExist() {
        List<Otclick> otclicks = List.of(new Otclick());
        when(service.getAll()).thenReturn(otclicks);

        ResponseEntity<List<Otclick>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(otclicks, response.getBody());
        verify(service, times(1)).getAll();
    }

    @Test
    void addVacancy_ShouldReturnBadRequest_WhenBindingResultHasErrors() {
        var bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = controller.addVacancy(new Otclick(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(service, never()).add(any(Otclick.class));
    }

    @Test
    void setCameStatus_ShouldReturnOkStatus() {
        Integer id = 1;
        Boolean status = true;

        ResponseEntity<?> response = controller.setCameStatus(id, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).setCameStatus(id, status);
    }
}
