package br.org.pessoal.adapter.out.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Mercadoria")
@Getter
@Setter
@RequiredArgsConstructor
@AttributeOverride(name = "id",
    column = @Column(name = "id_mercadoria")
)
public class MercadoriaEntity extends BaseEntity<Integer> {

    @Column(name = "ds_mercadoria")
    private String descMercadoria;

    @Column(name = "cd_especie")
    private Integer codEspecie;

    @Column(name = "cd_classe")
    private Integer codClasse;

}