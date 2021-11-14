package br.com.queroquero.loja.api;

public enum ErroEnum {
    
    // @formatter:off
    ERRO_CRIAR_VENDEDOR(1, "Erro ao criar o vendedor."), 
    ERRO_CRIAR_PRODUTO(2,"Erro ao criar o produto."), 
    ERRO_CRIAR_VENDA(3, "Erro ao criar a venda."), 
    ERRO_BUSCA_MAIORES_VENDEDORES_VALOR(4, "Erro ao buscar os maiores vendedores por valor de venda."), 
    ERRO_BUSCA_MAIORES_VENDEDORES_NUM_VENDAS(5, "Erro ao buscar os maiores vendedores por número de vendas."), 
    ERRO_BUSCA_PRODUTOS_MAIS_VENDIDOS(6, "Erro ao buscar os produtos mais vendidos."),
    ERRO_VENDEDOR_INEXISTENTE_AO_CRIAR_VENDA(7, "Erro ao criar a venda: vendedor não existe.")
    ;
    // @formatter:on
    
	private int codigo;
	private String mensagem;

	ErroEnum(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}


}
