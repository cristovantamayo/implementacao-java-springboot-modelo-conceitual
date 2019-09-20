package com.cristovantamayo.mcexemple.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.cristovantamayo.mcexemple.domain.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 6744790939349905791L;

	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, StatusPagamento status, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, status, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimeto) {
		this.dataVencimento = dataVencimeto;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}
