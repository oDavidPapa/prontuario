package com.ufes.prontuario.model;

import com.ufes.prontuario.enums.TipoContatoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Contato {

    @Id
    @SequenceGenerator(name = "seq_contato", sequenceName = "seq_contato", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contato")
    private Long id;

    @Column
    private String celular;

    @Column
    private String email;

    @Column(nullable = true)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato")
    private TipoContatoEnum tipoContato;

}
