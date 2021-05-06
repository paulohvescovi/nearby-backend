package br.com.neartech.nearby.empresa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMPRESA")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RAZAO_SOCIAL", nullable = false, length = 200)
    private String razaoSocial;

    public Empresa() {
    }

    public Empresa(Long id, String razaoSocial) {
        this.id = id;
        this.razaoSocial = razaoSocial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
