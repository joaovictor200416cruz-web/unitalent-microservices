package br.com.unitalent.msestudantes.dto;
public record EstudanteRequest(@jakarta.validation.constraints.NotBlank String nome, @jakarta.validation.constraints.NotBlank String email, @jakarta.validation.constraints.NotBlank String curso, @jakarta.validation.constraints.NotBlank String habilidades, @jakarta.validation.constraints.NotBlank String curriculoUrl) {}
