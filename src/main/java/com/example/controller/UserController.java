package com.example.controller;

import com.example.model.Pedido;
import com.example.model.StatusPedido;
import com.example.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UserController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("pedido")
    public String home(Model model, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findByUser(principal.getName());

        model.addAttribute("pedidos", pedidos);

        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/usuario/home";
    }

    @GetMapping("pedido/{status}")
    public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
        List<Pedido> pedidoList = pedidoRepository.findByUserAndStatus(principal.getName(), StatusPedido.valueOf(status.toUpperCase()));

        model.addAttribute("pedidos", pedidoList);
        model.addAttribute("status", status);

        return "usuario/home";
    }
}
