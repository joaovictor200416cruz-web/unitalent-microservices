package br.com.unitalent.msvagas.dto;
public record VagaResponse(Long id, String titulo, String descricao, String requisitos, Long empresaId, String status) {}
