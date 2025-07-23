package com.escolamusica.sistemalunos.repository;

import com.escolamusica.sistemalunos.model.Aluno;
import com.escolamusica.sistemalunos.model.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {
    
    Optional<Mensalidade> findByAlunoAndMesAno(Aluno aluno, YearMonth mesAno);
    List<Mensalidade> findByAluno(Aluno aluno);
}