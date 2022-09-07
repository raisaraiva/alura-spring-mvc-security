package com.example.api;

import com.example.dto.RequisicaoNovaOferta;
import com.example.model.Oferta;
import com.example.model.Pedido;
import com.example.model.StatusPedido;
import com.example.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/oferta")
@RestController
public class OfertaRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping()
    private Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicaoNovaOferta) {
        Optional<Pedido> optional = pedidoRepository.findById(requisicaoNovaOferta.getIdPedido());

        if (!optional.isPresent()) {
            return null;
        }

        Pedido pedido = optional.get();

        if (pedido.getOfertaList() == null) {
            pedido.setOfertaList(new ArrayList<>());
        }

        Oferta oferta = requisicaoNovaOferta.toOferta(pedido);

        pedido.getOfertaList().add(oferta);

        pedidoRepository.save(pedido);

        return oferta;
    }
}
