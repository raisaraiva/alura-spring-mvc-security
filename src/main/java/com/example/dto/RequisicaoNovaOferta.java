package com.example.dto;

import com.example.model.Oferta;
import com.example.model.Pedido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequisicaoNovaOferta {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String comentario;

    @NotNull
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    private String dataDaEntrega;

    private Long idPedido;

    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    private String valor;

    // constructors

    public RequisicaoNovaOferta() {
    }

    // getters and setters

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDataDaEntrega() {
        return dataDaEntrega;
    }

    public void setDataDaEntrega(String dataDaEntrega) {
        this.dataDaEntrega = dataDaEntrega;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    // methods

    public Oferta toOferta(Pedido pedido) {
        Oferta oferta = new Oferta();

        oferta.setComentario(getComentario());
        oferta.setDataDaEntrega(LocalDate.parse(getDataDaEntrega(), DATE_TIME_FORMATTER));
        oferta.setPedido(pedido);
        oferta.setValor(new BigDecimal(getValor()));

        return oferta;
    }
}
