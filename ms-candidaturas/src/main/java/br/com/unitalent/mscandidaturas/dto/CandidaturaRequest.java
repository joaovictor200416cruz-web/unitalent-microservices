package br.com.unitalent.mscandidaturas.dto;
import jakarta.validation.constraints.NotNull;
public record CandidaturaRequest(@NotNull Long estudanteId, @NotNull Long vagaId) {}
