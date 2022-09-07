package com.example.api;

import com.example.model.Pedido;
import com.example.model.StatusPedido;
import com.example.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/pedido")
@RestController
public class PedidoRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    private List<Pedido> getPedidosAguardando() {
        return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, PageRequest.of(0, 10, Sort.by("id").descending()));
    }
}
