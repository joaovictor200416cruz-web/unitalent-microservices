package br.com.unitalent.mscandidaturas.service;

import br.com.unitalent.mscandidaturas.dto.*;
import br.com.unitalent.mscandidaturas.model.Candidatura;
import br.com.unitalent.mscandidaturas.repository.CandidaturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

@Service
public class CandidaturaService {
    private final CandidaturaRepository repository;
    private final RestClient restClient;

    public CandidaturaService(CandidaturaRepository repository, RestClient.Builder builder) {
        this.repository = repository;
        this.restClient = builder.build();
    }

    public CandidaturaResponse create(CandidaturaRequest req) {
        if (repository.existsByEstudanteIdAndVagaId(req.estudanteId(), req.vagaId())) {
            throw new IllegalArgumentException("Estudante já se candidatou a esta vaga");
        }

        // COMUNICAÇÃO SÍNCRONA: Candidaturas -> Vagas
        VagaResponse vaga = restClient.get()
            .uri("http://localhost:8084/api/vagas/{id}", req.vagaId())
            .retrieve()
            .body(VagaResponse.class);

        if (vaga == null || !"ATIVA".equalsIgnoreCase(vaga.status())) {
            throw new IllegalArgumentException("Vaga inexistente ou inativa");
        }

        // COMUNICAÇÃO SÍNCRONA: Candidaturas -> Estudantes
        EstudanteResponse estudante = restClient.get()
            .uri("http://localhost:8082/api/estudantes/{id}", req.estudanteId())
            .retrieve()
            .body(EstudanteResponse.class);

        if (estudante == null) {
            throw new IllegalArgumentException("Estudante não encontrado");
        }

        Candidatura saved = repository.save(new Candidatura(req.estudanteId(), req.vagaId(), "ENVIADA"));

        // COMUNICAÇÃO SÍNCRONA opcional: Candidaturas -> Notificações
        try {
            restClient.post()
                .uri("http://localhost:8086/api/notificacoes")
                .body(new NotificacaoRequest(
                    estudante.email(),
                    "Candidatura enviada para a vaga: " + vaga.titulo(),
                    "CANDIDATURA",
                    false))
                .retrieve().toBodilessEntity();
        } catch (Exception ignored) {
            // A candidatura permanece salva mesmo se a notificação estiver indisponível.
        }

        return toResponse(saved);
    }

    public List<CandidaturaResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    public CandidaturaResponse findById(Long id) {
        return toResponse(repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Candidatura não encontrada")));
    }
    private CandidaturaResponse toResponse(Candidatura c) {
        return new CandidaturaResponse(c.getId(), c.getEstudanteId(), c.getVagaId(), c.getStatus());
    }
}
