package br.com.unitalent.msestudantes.service;
    import br.com.unitalent.msestudantes.dto.*;
    import br.com.unitalent.msestudantes.model.Estudante;
    import br.com.unitalent.msestudantes.repository.EstudanteRepository;
    import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class EstudanteService {
        private final EstudanteRepository repository;
        public EstudanteService(EstudanteRepository repository) { this.repository = repository; }

        public List<EstudanteResponse> findAll() {
            return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        }
        public EstudanteResponse findById(Long id) {
            return toResponse(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado")));
        }
        public EstudanteResponse create(EstudanteRequest req) {
            Estudante entity = new Estudante(req.nome(), req.email(), req.curso(), req.habilidades(), req.curriculoUrl());
            return toResponse(repository.save(entity));
        }
        public EstudanteResponse update(Long id, EstudanteRequest req) {
            Estudante entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado"));
            entity.setNome(req.nome());
entity.setEmail(req.email());
entity.setCurso(req.curso());
entity.setHabilidades(req.habilidades());
entity.setCurriculoUrl(req.curriculoUrl());
            return toResponse(repository.save(entity));
        }
        public void delete(Long id) { repository.deleteById(id); }
        private EstudanteResponse toResponse(Estudante entity) {
            return new EstudanteResponse(entity.getId(), entity.getNome(), entity.getEmail(), entity.getCurso(), entity.getHabilidades(), entity.getCurriculoUrl());
        }
    }
