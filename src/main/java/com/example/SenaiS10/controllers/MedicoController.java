package com.example.SenaiS10.controllers;

import com.example.SenaiS10.Especialidade;
import com.example.SenaiS10.dtos.MedicoDTO;
import com.example.SenaiS10.entities.Medico;
import com.example.SenaiS10.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> listarTodos() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Medico buscarMedicoPorId(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return medicoService.buscarPorId(id);
    }

    @GetMapping
    public Page<MedicoDTO> listarMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Especialidade especialidade,
            @RequestParam(required = false) LocalDate dataNascimento,
            Pageable pageable) {
        return medicoService.listarComFiltros(nome, especialidade, dataNascimento, pageable);
    }

    @PostMapping
    public Medico cadastrarMedico(@RequestBody MedicoDTO medicoDTO) {
        return medicoService.criar(medicoDTO);
    }

    @PutMapping("/{id}")
    public Medico atualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) throws ChangeSetPersister.NotFoundException {
        return medicoService.atualizar(id, medicoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarMedico(@PathVariable Long id) {
        medicoService.deletar(id);
    }
}
