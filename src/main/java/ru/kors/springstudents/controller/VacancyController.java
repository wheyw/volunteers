package ru.kors.springstudents.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kors.springstudents.model.Vacancy;
import ru.kors.springstudents.service.VacancyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@AllArgsConstructor
public class VacancyController {
    private final VacancyService service;
    @GetMapping("/all")
    public ResponseEntity<List<Vacancy>> getAllVacancies()
    {
        var result = service.getAll();
        if(!result.isEmpty())
        {
            return ResponseEntity.ok(service.getAll());
        }
        else return ResponseEntity.status(HttpStatus.LOCKED).build();
    }
    @GetMapping("/event")
    public ResponseEntity<List<Vacancy>> getVacanciesViaEvent(@RequestParam("eventId") Integer event)
    {
        var result = service.getVacanciesViaEvent(event);
        if(!result.isEmpty())
        {
            return ResponseEntity.ok(service.getVacanciesViaEvent(event));
        }
        else return ResponseEntity.status(HttpStatus.LOCKED).build();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addVacancy(@Valid @RequestBody Vacancy item,
                                        BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        service.addVacancy(item);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
