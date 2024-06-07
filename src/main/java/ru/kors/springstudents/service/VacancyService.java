package ru.kors.springstudents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.Vacancy;
import ru.kors.springstudents.repository.vacancyRepository;

import java.util.List;

@Service
public class VacancyService {
    @Autowired
    vacancyRepository vacancyRepository;

    public List<Vacancy> getAll(){
        return vacancyRepository.findAll();
    }
    public void addVacancy(Vacancy item){
        vacancyRepository.save(item);
    }
    public List<Vacancy> getVacanciesViaEvent(Integer event){
        return vacancyRepository.getVacancyViaEventId(event);
    }
}
