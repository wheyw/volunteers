package ru.kors.springstudents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kors.springstudents.model.Vacancy;

import java.util.List;

@Repository
public interface vacancyRepository extends JpaRepository<Vacancy, Long>
{
    @Query("SELECT v FROM Vacancy v WHERE v.eventId = :eventId")
    public List<Vacancy> getVacancyViaEventId(@Param("eventId") Integer eventId);
}
