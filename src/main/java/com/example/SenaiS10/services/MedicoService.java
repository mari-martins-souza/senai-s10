package com.example.SenaiS10.services;

import com.example.SenaiS10.Especialidade;
import com.example.SenaiS10.MedicoSpecification;
import com.example.SenaiS10.dtos.MedicoDTO;
import com.example.SenaiS10.entities.Medico;
import com.example.SenaiS10.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico criar(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setNome(medicoDTO.getNome());
        medico.setCrm(medicoDTO.getCrm());
        medico.setDataNascimento(medicoDTO.getDataNascimento());
        medico.setTelefone(medicoDTO.getTelefone());
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        return medicoRepository.save(medico);
    }

    public Medico buscarPorId(Long id) throws ChangeSetPersister.NotFoundException {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public Medico atualizar(Long id, MedicoDTO medicoDTO) throws ChangeSetPersister.NotFoundException {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        medicoExistente.setNome(medicoDTO.getNome());
        medicoExistente.setCrm(medicoDTO.getCrm());
        medicoExistente.setDataNascimento(medicoDTO.getDataNascimento());
        medicoExistente.setTelefone(medicoDTO.getTelefone());
        medicoExistente.setEspecialidade(medicoDTO.getEspecialidade());

        return medicoRepository.save(medicoExistente);
    }

    public void deletar(Long id) throws ChangeSetPersister.NotFoundException {
        if (!medicoRepository.existsById(id)) {
            throw new ChangeSetPersister.NotFoundException();
        }
        medicoRepository.deleteById(id);
    }

    public Page<MedicoDTO> listarComFiltros(String nome, Especialidade especialidade, LocalDate dataNascimento, Pageable pageable) {
        Specification<Medico> spec = Specification.where(null);

        if (nome != null && !nome.isEmpty()) {
            spec = spec.and(MedicoSpecification.nomeIgual(nome));
        }
        if (especialidade != null) {
            spec = spec.and(MedicoSpecification.especialidadeIgual(especialidade));
        }
        if (dataNascimento != null) {
            spec = spec.and(MedicoSpecification.dataNascimentoIgual(dataNascimento));
        }

        return medicoRepository.findAll(spec, pageable)
                .map(medico -> new MedicoDTO(medico.getNome(), medico.getEspecialidade(), medico.getDataNascimento()));
    }


}
