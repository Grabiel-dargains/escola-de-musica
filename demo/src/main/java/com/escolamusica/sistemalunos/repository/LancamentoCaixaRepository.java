package com.escolamusica.sistemalunos.repository;

import com.escolamusica.sistemalunos.model.LancamentoCaixa;
import com.escolamusica.sistemalunos.model.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LancamentoCaixaRepository extends JpaRepository<LancamentoCaixa, Long> {
    // Query para calcular o saldo.
    @Query("SELECT COALESCE(SUM(CASE WHEN l.tipo = 'ENTRADA' THEN l.valor ELSE -l.valor END), 0) FROM LancamentoCaixa l")
    BigDecimal calcularSaldo();
}