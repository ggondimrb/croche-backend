package com.gabrielbatista.crochedemainha.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.domain.ItemPedido;
import com.gabrielbatista.crochedemainha.domain.Order;
import com.gabrielbatista.crochedemainha.domain.PagamentoComBoleto;
import com.gabrielbatista.crochedemainha.domain.enums.EstadoPagamento;
import com.gabrielbatista.crochedemainha.dto.OrderDTO;
import com.gabrielbatista.crochedemainha.dto.OrderedItemDTO;
import com.gabrielbatista.crochedemainha.dto.ProdutoDTO;
import com.gabrielbatista.crochedemainha.repositories.ItemPedidoRepository;
import com.gabrielbatista.crochedemainha.repositories.OrderRepository;
import com.gabrielbatista.crochedemainha.repositories.PagamentoRepository;
import com.gabrielbatista.crochedemainha.security.UserSS;
import com.gabrielbatista.crochedemainha.services.exceptions.AuthorizationException;
import com.gabrielbatista.crochedemainha.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private EmailService emailService;
	
	public OrderDTO find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		
		OrderDTO dto = convertToObjectDto(obj.get());
		Optional<OrderDTO> optDto = Optional.of(dto);
		
		return optDto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrderDTO.class.getName()));	
		
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId().toString()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setOrder(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setOrder(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public Page<OrderDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente =  clienteService.find(user.getId().toString());
		Page<Order> pagePedido = repo.findByCliente(cliente, pageRequest);
		
		Page<OrderDTO> pageDto = toPageObjectDto(pagePedido);
		
		return pageDto;
	}
	

	public Page<OrderDTO> toPageObjectDto(Page<Order> objects) {
	    Page<OrderDTO> dtos  = objects.map(this::convertToObjectDto);
	    return dtos;
	}

	private OrderDTO convertToObjectDto(Order obj) {
		OrderDTO dto = new OrderDTO();
		dto.setId(obj.getId());
		dto.setInstant(obj.getInstant());
		dto.setTotalValue(obj.getValorTotal());
		dto.setDeliveryAddress(obj.getEnderecoDeEntrega());
		
		for(ItemPedido item: obj.getItens()) {
			OrderedItemDTO itemPedido = new OrderedItemDTO();
			itemPedido.setProduct(new ProdutoDTO(item.getProduto()));
			itemPedido.setPrice(item.getPreco());
			itemPedido.setAmount(item.getQuantidade());
			dto.getItens().add(itemPedido);
		}
	    return dto;
	}
}
