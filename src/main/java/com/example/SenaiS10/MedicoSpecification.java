package com.example.SenaiS10;

import com.example.SenaiS10.entities.Medico;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MedicoSpecification {

    public static Specification<Medico> nomeIgual(String nome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Medico> especialidadeIgual(Especialidade especialidade) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("especialidade"), especialidade);
    }

    public static Specification<Medico> dataNascimentoIgual(LocalDate dataNascimento) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dataNascimento"), dataNascimento);
    }
}

