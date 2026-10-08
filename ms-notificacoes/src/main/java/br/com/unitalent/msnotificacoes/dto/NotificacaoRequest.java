package br.com.unitalent.msnotificacoes.dto;
public record NotificacaoRequest(@jakarta.validation.constraints.NotBlank String destinatario, @jakarta.validation.constraints.NotBlank String mensagem, @jakarta.validation.constraints.NotBlank String tipo, Boolean lida) {}
