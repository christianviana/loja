package br.com.queroquero.loja.api.erros;

/**
 * Classe que representa um erro para ser utilizada na Response das chamadas à
 * API, quando houver erro
 * 
 *
 */
public class Erro {

	private int codigo;
	private String msgUsuario;

	public Erro(ErroEnum tipoErro) {
		this.codigo = tipoErro.getCodigo();
		this.msgUsuario = tipoErro.getMensagem();
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMsgUsuario() {
		return msgUsuario;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setMsgUsuario(String msgUsuario) {
		this.msgUsuario = msgUsuario;
	}

}

