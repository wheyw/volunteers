package ru.kors.springstudents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kors.springstudents.model.Otclick;

public interface OtclickRepository extends JpaRepository<Otclick, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Otclick o SET o.came = :status WHERE o.id = :id")
    public void changeCameStatus(@Param("id") Integer id, @Param("status") Boolean status);
}
