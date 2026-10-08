package br.com.unitalent.msvagas.dto;
public record VagaRequest(@jakarta.validation.constraints.NotBlank String titulo, @jakarta.validation.constraints.NotBlank String descricao, @jakarta.validation.constraints.NotBlank String requisitos, Long empresaId, @jakarta.validation.constraints.NotBlank String status) {}
