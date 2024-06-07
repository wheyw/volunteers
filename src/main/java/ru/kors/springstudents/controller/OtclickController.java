package ru.kors.springstudents.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kors.springstudents.model.Otclick;
import ru.kors.springstudents.model.Vacancy;
import ru.kors.springstudents.service.OtclickService;
import ru.kors.springstudents.service.VacancyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/otclicks")
@AllArgsConstructor
public class OtclickController {
    private final OtclickService service;
    @GetMapping("/all")
    public ResponseEntity<List<Otclick>> getAll()
    {
        var result = service.getAll();
        if(!result.isEmpty())
        {
            return ResponseEntity.ok(result);
        }
        else return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addVacancy(@Valid @RequestBody Otclick item,
                                        BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        service.add(item);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping("/came")
    public ResponseEntity<?> setCameStatus(@Valid @RequestParam Integer id, @RequestParam Boolean status)
    {
        service.setCameStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
