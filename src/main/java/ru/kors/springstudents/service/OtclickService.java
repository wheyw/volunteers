package ru.kors.springstudents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.model.Otclick;
import ru.kors.springstudents.model.Vacancy;
import ru.kors.springstudents.repository.OtclickRepository;
import ru.kors.springstudents.repository.vacancyRepository;

import java.util.List;

@Service
public class OtclickService {
    @Autowired
    OtclickRepository repository;

    public List<Otclick> getAll(){
        return repository.findAll();
    }
    public void add(Otclick item){
        repository.save(item);
    }
    public void setCameStatus(Integer id, boolean status)
    {
        repository.changeCameStatus(id,status);
    }
}
