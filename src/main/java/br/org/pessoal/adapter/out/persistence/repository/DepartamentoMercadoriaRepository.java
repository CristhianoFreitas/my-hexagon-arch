package br.org.pessoal.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.pessoal.adapter.out.persistence.entity.DepartamentoMercadoriaEntity;

@Repository
public interface DepartamentoMercadoriaRepository extends JpaRepository<DepartamentoMercadoriaEntity, Integer> {

}
