/**
 * @autor Tiago Barreto dos Santos
 * @github https://github.com/tiagonic
 * @projeto https://github.com/tiagonic/act.git
 *
 * Classe abstrata representando um Data Transfer Object (DTO) no contexto da aplicação.
 *
 * Justificativa Arquitetural:
 * Esta classe abstrata serve como base para os Data Transfer Objects, que são utilizados
 * para transportar dados entre diferentes camadas do sistema. Eles não contêm lógica de negócios,
 * apenas os dados necessários para a comunicação.
 * 
 * Função:
 * - Transferir dados entre camadas da aplicação (como entre a camada de apresentação e a camada de serviço).
 * - Facilitar a serialização e desserialização de dados para transporte através de web services ou outras interfaces.
 *
 * Importância na Arquitetura:
 * DTOs ajudam a separar as preocupações de transporte de dados da lógica de negócios, promovendo
 * uma arquitetura mais organizada e manutenível.
 * 
 * @param <ID> Tipo da chave primária da entidade.
 * 
 * @autor Tiago Barreto dos Santos
 * @github https://github.com/tiagonic
 * @projeto https://github.com/tiagonic/actx.git
 * 
 * Data: 12 de Dezembro de 2024
 * 
 */

package br.com.actx.arquitetura.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.actx.arquitetura.common.Identifiable;

public abstract class DataTransferObject<ID extends Serializable> implements Identifiable<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ID id;

	private LocalDateTime dataCriacao;

	private LocalDateTime dataAtualizacao;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

}
