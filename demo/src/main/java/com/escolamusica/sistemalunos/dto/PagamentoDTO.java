package com.escolamusica.sistemalunos.dto;

import java.math.BigDecimal;

public record PagamentoDTO(Long alunoId, int ano, int mes, BigDecimal valor) {
}