package br.org.pessoal.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<T> {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private T id;

    // As implementações de equals e hashCode são baseadas nas informações
    // disponíveis em:
    // https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        BaseEntity<T> other = (BaseEntity<T>) o;

        return this.getId() != null && this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

}
