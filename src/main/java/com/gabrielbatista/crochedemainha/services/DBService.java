package com.gabrielbatista.crochedemainha.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gabrielbatista.crochedemainha.domain.Adresses;
import com.gabrielbatista.crochedemainha.domain.Categoria;
import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.domain.Imagem;
import com.gabrielbatista.crochedemainha.domain.ItemPedido;
import com.gabrielbatista.crochedemainha.domain.Order;
import com.gabrielbatista.crochedemainha.domain.Pagamento;
import com.gabrielbatista.crochedemainha.domain.PagamentoComBoleto;
import com.gabrielbatista.crochedemainha.domain.PagamentoComCartao;
import com.gabrielbatista.crochedemainha.domain.Produto;
import com.gabrielbatista.crochedemainha.domain.enums.Cor;
import com.gabrielbatista.crochedemainha.domain.enums.EstadoPagamento;
import com.gabrielbatista.crochedemainha.domain.enums.Perfil;
import com.gabrielbatista.crochedemainha.domain.enums.TipoCliente;
import com.gabrielbatista.crochedemainha.repositories.CategoriaRepository;
import com.gabrielbatista.crochedemainha.repositories.ClienteRepository;
import com.gabrielbatista.crochedemainha.repositories.EnderecoRepository;
import com.gabrielbatista.crochedemainha.repositories.ImagemRepository;
import com.gabrielbatista.crochedemainha.repositories.ItemPedidoRepository;
import com.gabrielbatista.crochedemainha.repositories.OrderRepository;
import com.gabrielbatista.crochedemainha.repositories.PagamentoRepository;
import com.gabrielbatista.crochedemainha.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ImagemRepository imagemRepository;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Bolsas");
		Categoria cat2 = new Categoria(null, "Souplast");
		Categoria cat3 = new Categoria(null, "Centro de Mesa");
		Categoria cat4 = new Categoria(null, "Conjunto de Praia");
		Categoria cat5 = new Categoria(null, "Mascaras");
		Categoria cat6 = new Categoria(null, "Artigos Natalinos");

		Produto p1 = new Produto(null, "Souplast Retangular", 59.90, "Conjunto de dois souplast em formato retangular",
				'S', cat2, 0.5);
		Produto p2 = new Produto(null, "Souplast Coração", 49.90,
				"Conjunto de dois souplast em formato de coração e de brinde ganhe um conjunto de porta copos.", 'S',
				cat2, 0.5);
		Produto p3 = new Produto(null, "Centro de Mesa Natalino", 49.90,
				"Centro de mesa com tema natalino para decorar sua ceia", 'S', cat3, 0.3);
		Produto p4 = new Produto(null, "Carteira", 39.90, "Carteira com zíper para te acompanhar em todos os momentos!",
				'S', cat1, 0.2);
		Produto p5 = new Produto(null, "Conjunto de Praia biquini", 119.90,
				"Conjunto de praia nas cores preta e marrom.", 'N', cat4, 0.8);
		Produto p6 = new Produto(null, "Conjunto de Praia top", 119.90,
				"Conjunto de praia nas cores laranja e branco. Obs: Não acompanha óculos.", 'N', cat4, 0.8);

		p1.addCor(Cor.ROXO);
		p1.addCor(Cor.VERDE);
		p2.addCor(Cor.ROSA);
		p3.addCor(Cor.VERDE);
		p4.addCor(Cor.VERDE);
		p4.addCor(Cor.VINHO);
		p5.addCor(Cor.MARROM);
		p6.addCor(Cor.LARANJA);

		Imagem i1 = new Imagem(null, "souplast01a.png", p1);
		Imagem i2 = new Imagem(null, "souplast02a.png", p2);
		Imagem i3 = new Imagem(null, "centro-de-mesa01a.png", p3);
		Imagem i4 = new Imagem(null, "bolsa01a.png", p4);
		Imagem i7 = new Imagem(null, "conjunto-de-praia01a.png", p5);
		Imagem i8 = new Imagem(null, "conjunto-de-praia02a.png", p6);

//		p1.setCategoria(cat1);
//		p2.setCategoria(cat2);
//		p3.setCategoria(cat3);
//		p4.setCategoria(cat4);
//		p5.setCategoria(cat5);
//		p6.setCategoria(cat6);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		imagemRepository.saveAll(Arrays.asList(i1, i2, i3, i4, i7, i8));

		Cliente cli1 = new Cliente(null, "Nina Silva", "ggondimrb@gmail.com", "3637453432", TipoCliente.PESSOAFISICA,
				pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("38345421", "30904567"));
		cli1.addPerfil(Perfil.ADMIN);

		Cliente cli2 = new Cliente(null, "Gabriel Batista", "ggrb@gmail.com", "91659991005", TipoCliente.PESSOAFISICA,
				pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("38398421", "30902167"));
		cli2.addPerfil(Perfil.ADMIN);

		Adresses end1 = new Adresses(null, "Rua Professor José Cândido Pessoa", "1448", "Ap 604", "Bairro Novo", "Olinda",
				"53030020", true, cli1);

		Adresses end2 = new Adresses(null, "Avenida Matos", "290", "Apt 101", "Floresta", "Nao sei", "35423", false, cli1);
		Adresses end3 = new Adresses(null, "Avenida Jose Ribeiro", "290", null, "Floresta", "Sei la", "35423", true, cli2);

		cli1.getAdresses().addAll(Arrays.asList(end1, end2));
		cli2.getAdresses().addAll(Arrays.asList(end3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 1);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
