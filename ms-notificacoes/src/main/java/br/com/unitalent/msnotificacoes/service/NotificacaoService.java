package br.com.unitalent.msnotificacoes.service;

import br.com.unitalent.msnotificacoes.dto.*;
import br.com.unitalent.msnotificacoes.model.Notificacao;
import br.com.unitalent.msnotificacoes.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository repository) {
        this.repository = repository;
    }

    public List<NotificacaoResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public NotificacaoResponse findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Notificação não encontrada")));
    }

    public NotificacaoResponse create(NotificacaoRequest req) {
        Notificacao entity = new Notificacao(
                req.destinatario(),
                req.mensagem(),
                req.tipo(),
                req.lida()
        );

        return toResponse(repository.save(entity));
    }

    public NotificacaoResponse update(Long id, NotificacaoRequest req) {
        Notificacao entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Notificação não encontrada"));

        entity.setDestinatario(req.destinatario());
        entity.setMensagem(req.mensagem());
        entity.setTipo(req.tipo());
        entity.setLida(req.lida());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private NotificacaoResponse toResponse(Notificacao entity) {
        return new NotificacaoResponse(
                entity.getId(),
                entity.getDestinatario(),
                entity.getMensagem(),
                entity.getTipo(),
                entity.getLida()
        );
    }
}