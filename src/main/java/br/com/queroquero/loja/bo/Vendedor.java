package br.com.queroquero.loja.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa um vendedor da loja
 * 
 */
@Entity
public class Vendedor {

	@Id
    @SequenceGenerator(name = "vendedorSeq", sequenceName = "vendedor_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "vendedorSeq")
	// o id é usado apenas para o ORM, o mundo externo não o conhece
	@JsonIgnore
	private Long id;
	// matrícula é o campo que identifica o vendedor de maneira única
	@Column(unique = true)
    private String matricula;
    private String nome;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public String getMatricula() {
    	return this.matricula;
    }

    
    public String getNome() {
    	return this.nome;
    }


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", matricula=" + matricula + ", nome=" + nome + "]";
	}


  
}