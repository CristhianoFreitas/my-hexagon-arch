package br.org.pessoal.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DepartamentoMercadoria")
@Getter
@Setter
@RequiredArgsConstructor
public class DepartamentoMercadoriaEntity {

    @Id
    @Column(name = "cd_departamento_mercadoria")
    private Integer codDepartamentoMercadoria;

    @Column(name = "nm_departamento_mercadoria")
    private String nomeDepartamentoMercadoria;

    @Column(name = "st_departamento_mercadoria")
    private Character flagDepartamento;

}
