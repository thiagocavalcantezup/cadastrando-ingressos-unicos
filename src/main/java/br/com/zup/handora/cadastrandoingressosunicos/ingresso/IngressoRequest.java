package br.com.zup.handora.cadastrandoingressosunicos.ingresso;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

public class IngressoRequest {

    @NotBlank
    private String nomeCliente;

    @NotBlank
    @CPF
    private String cpfCliente;

    @NotNull
    private Integer numero;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraEvento;

    public IngressoRequest() {}

    public IngressoRequest(@NotBlank String nomeCliente, @NotBlank @CPF String cpfCliente,
                           @NotNull Integer numero, @NotNull @Future LocalDateTime dataHoraEvento) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.numero = numero;
        this.dataHoraEvento = dataHoraEvento;
    }

    public Ingresso toModel() {
        return new Ingresso(nomeCliente, cpfCliente, numero, dataHoraEvento);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDateTime getDataHoraEvento() {
        return dataHoraEvento;
    }

}
