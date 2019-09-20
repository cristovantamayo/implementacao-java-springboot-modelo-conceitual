package com.cristovantamayo.mcexemple;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cristovantamayo.mcexemple.domain.Categoria;
import com.cristovantamayo.mcexemple.domain.Cidade;
import com.cristovantamayo.mcexemple.domain.Cliente;
import com.cristovantamayo.mcexemple.domain.Endereco;
import com.cristovantamayo.mcexemple.domain.Estado;
import com.cristovantamayo.mcexemple.domain.Pagamento;
import com.cristovantamayo.mcexemple.domain.PagamentoComBoleto;
import com.cristovantamayo.mcexemple.domain.PagamentoComCartao;
import com.cristovantamayo.mcexemple.domain.Pedido;
import com.cristovantamayo.mcexemple.domain.Produto;
import com.cristovantamayo.mcexemple.domain.enums.StatusPagamento;
import com.cristovantamayo.mcexemple.domain.enums.TipoCliente;
import com.cristovantamayo.mcexemple.repositories.CategoriaRepository;
import com.cristovantamayo.mcexemple.repositories.CidadeRepository;
import com.cristovantamayo.mcexemple.repositories.ClienteRepository;
import com.cristovantamayo.mcexemple.repositories.EnderecoRepository;
import com.cristovantamayo.mcexemple.repositories.EstadoRepository;
import com.cristovantamayo.mcexemple.repositories.PagamentoRepository;
import com.cristovantamayo.mcexemple.repositories.PedidoRepository;
import com.cristovantamayo.mcexemple.repositories.ProdutoRepository;

@SpringBootApplication
public class McExempleApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(McExempleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse Lazer", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "123123123-12", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11111111", "22222222"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenia Matos", "105", "Sala 800", "Centro", "38001001", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/07/2019 12:45"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 17:23"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,StatusPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null,StatusPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	}


}
