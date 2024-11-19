package br.org.pessoal.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.pessoal.adapter.out.persistence.entity.MercadoriaEntity;

@Repository
public interface MercadoriaRepository extends JpaRepository<MercadoriaEntity, Integer> {

}
