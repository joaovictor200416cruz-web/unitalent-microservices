package br.com.unitalent.msempresas.dto;
public record EmpresaRequest(@jakarta.validation.constraints.NotBlank String razaoSocial, @jakarta.validation.constraints.NotBlank String cnpj, @jakarta.validation.constraints.NotBlank String email, @jakarta.validation.constraints.NotBlank String descricao) {}
