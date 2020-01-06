package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.ItemPedido;
import com.carolinaalves.cursospringboot.domain.PagamentoComBoleto;
import com.carolinaalves.cursospringboot.domain.Pedido;
import com.carolinaalves.cursospringboot.domain.enums.EstadoPagamento;
import com.carolinaalves.cursospringboot.repository.ItemPedidoRepository;
import com.carolinaalves.cursospringboot.repository.PagamentoRepository;
import com.carolinaalves.cursospringboot.repository.PedidoRepository;
import com.carolinaalves.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
  @Autowired
  private PedidoRepository repository;

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @Autowired
  private ProdutoService produtoService;

  public Pedido find(Integer id) {
    Optional<Pedido> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
  }

  public Pedido insert(Pedido pedido) {
    pedido.setId(null);
    pedido.setInstante(new Date());
    pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
    pedido.getPagamento().setPedido(pedido);
    if (pedido.getPagamento() instanceof PagamentoComBoleto) {
      PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
      BoletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
    }

    pedido = repository.save(pedido);
    pagamentoRepository.save(pedido.getPagamento());
    for (ItemPedido itemPedido : pedido.getItens()) {
      itemPedido.setDesconto(0.0);
      itemPedido.setPreco(produtoService.find(itemPedido.getProduto().getId()).getPreco());
      itemPedido.setPedido(pedido);
    }

    itemPedidoRepository.saveAll(pedido.getItens());
    return pedido;
  }
}
