package br.com.unitalent.msnotificacoes.dto;
public record NotificacaoResponse(Long id, String destinatario, String mensagem, String tipo, Boolean lida) {}
