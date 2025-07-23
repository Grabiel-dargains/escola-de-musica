package com.escolamusica.sistemalunos.service;

import com.escolamusica.sistemalunos.dto.DespesaDTO;
import com.escolamusica.sistemalunos.dto.PagamentoDTO;
import com.escolamusica.sistemalunos.dto.SaldoDTO;
import com.escolamusica.sistemalunos.model.*;
import com.escolamusica.sistemalunos.repository.AlunoRepository;
import com.escolamusica.sistemalunos.repository.LancamentoCaixaRepository;
import com.escolamusica.sistemalunos.repository.MensalidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
public class CaixaService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private MensalidadeRepository mensalidadeRepository;
    @Autowired
    private LancamentoCaixaRepository lancamentoCaixaRepository;

    @Transactional 
    public Mensalidade registrarPagamento(PagamentoDTO pagamentoDTO) {
        Aluno aluno = alunoRepository.findById(pagamentoDTO.alunoId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + pagamentoDTO.alunoId()));

        YearMonth mesAnoReferencia = YearMonth.of(pagamentoDTO.ano(), pagamentoDTO.mes());

        // Procura se já existe uma mensalidade para este aluno neste mês/ano.
        Mensalidade mensalidade = mensalidadeRepository.findByAlunoAndMesAno(aluno, mesAnoReferencia)
                .orElse(new Mensalidade()); // Se não existir, cria uma nova.

        // Atualiza ou preenche os dados da mensalidade.
        mensalidade.setAluno(aluno);
        mensalidade.setMesAno(mesAnoReferencia);
        mensalidade.setValorPago(pagamentoDTO.valor());
        mensalidade.setDataPagamento(LocalDate.now());
        mensalidade.setStatus(StatusPagamento.PAGO);
        Mensalidade mensalidadeSalva = mensalidadeRepository.save(mensalidade);

        // Cria o lançamento no caixa.
        LancamentoCaixa lancamento = new LancamentoCaixa();
        lancamento.setDescricao("Pagamento mensalidade de " + aluno.getNome() + " - " + mesAnoReferencia);
        lancamento.setValor(pagamentoDTO.valor());
        lancamento.setData(LocalDateTime.now());
        lancamento.setTipo(TipoLancamento.ENTRADA);
        lancamentoCaixaRepository.save(lancamento);

        return mensalidadeSalva;
    }

    @Transactional
    public LancamentoCaixa registrarDespesa(DespesaDTO despesaDTO) {
        LancamentoCaixa lancamento = new LancamentoCaixa();
        lancamento.setDescricao(despesaDTO.descricao());
        lancamento.setValor(despesaDTO.valor());
        lancamento.setData(LocalDateTime.now());
        lancamento.setTipo(TipoLancamento.SAIDA);
        return lancamentoCaixaRepository.save(lancamento);
    }

    public SaldoDTO verificarSaldo() {
        BigDecimal saldo = lancamentoCaixaRepository.calcularSaldo();
        return new SaldoDTO(saldo);
    }
}