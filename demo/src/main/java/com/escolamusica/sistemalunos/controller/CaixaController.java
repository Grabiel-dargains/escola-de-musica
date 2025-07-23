package com.escolamusica.sistemalunos.controller;

import com.escolamusica.sistemalunos.dto.DespesaDTO;
import com.escolamusica.sistemalunos.dto.PagamentoDTO;
import com.escolamusica.sistemalunos.dto.SaldoDTO;
import com.escolamusica.sistemalunos.model.LancamentoCaixa;
import com.escolamusica.sistemalunos.model.Mensalidade;
import com.escolamusica.sistemalunos.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @PostMapping("/pagamentos")
    public ResponseEntity<Mensalidade> registrarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        Mensalidade mensalidade = caixaService.registrarPagamento(pagamentoDTO);
        return ResponseEntity.ok(mensalidade);
    }
    
    @PostMapping("/caixa/despesas")
    public ResponseEntity<LancamentoCaixa> registrarDespesa(@RequestBody DespesaDTO despesaDTO) {
        LancamentoCaixa lancamento = caixaService.registrarDespesa(despesaDTO);
        return ResponseEntity.ok(lancamento);
    }

    @GetMapping("/caixa/saldo")
    public ResponseEntity<SaldoDTO> getSaldo() {
        return ResponseEntity.ok(caixaService.verificarSaldo());
    }
}