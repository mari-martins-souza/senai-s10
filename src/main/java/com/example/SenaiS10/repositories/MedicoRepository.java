package com.example.SenaiS10.repositories;

import com.example.SenaiS10.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByCrm(String crm);
}

