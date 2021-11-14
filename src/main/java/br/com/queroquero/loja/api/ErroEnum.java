package br.com.queroquero.loja.api;

public enum ErroEnum {
	ERRO_CRIAR_VENDEDOR(1, "Erro ao criar o vendedor."), ERRO_CRIAR_PRODUTO(2, "Erro ao criar o produto."),
	ERRO_CRIAR_VENDA(3, "Erro ao criar a venda.");

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
