package br.com.zup.handora.cadastrandoingressosunicos.ingresso;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "ingressos", uniqueConstraints = {
        @UniqueConstraint(name = "UK_INGRESSO_NUMERO_DATAHORAEVENTO", columnNames = {
                "numero", "dataHoraEvento"})})
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    @CPF
    private String cpfCliente;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    @Future
    private LocalDateTime dataHoraEvento;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Ingresso() {}

    public Ingresso(String nomeCliente, @CPF String cpfCliente, Integer numero,
                    @Future LocalDateTime dataHoraEvento) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.numero = numero;
        this.dataHoraEvento = dataHoraEvento;
    }

    public Long getId() {
        return id;
    }

}
