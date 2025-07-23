package com.escolamusica.sistemalunos.controller;

import com.escolamusica.sistemalunos.dto.AlunoDTO;
import com.escolamusica.sistemalunos.model.Aluno;
import com.escolamusica.sistemalunos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(alunoDTO.nome());
        novoAluno.setEndereco(alunoDTO.endereco());
        novoAluno.setInstrumento(alunoDTO.instrumento());
        Aluno alunoSalvo = alunoRepository.save(novoAluno);
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}