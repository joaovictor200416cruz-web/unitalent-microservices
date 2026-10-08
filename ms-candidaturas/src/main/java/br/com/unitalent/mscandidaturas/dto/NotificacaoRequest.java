package br.com.unitalent.mscandidaturas.dto;
public record NotificacaoRequest(String destinatario, String mensagem, String tipo, Boolean lida) {}
