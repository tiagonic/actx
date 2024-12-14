/**
 * Classe abstrata representando um Value Object (VO) no contexto do domínio do sistema.
 *
 * Justificativa Arquitetural:
 * Esta classe abstrata serve como base para os Value Objects, que são definidos
 * por seus atributos e não possuem identidade própria. Os Value Objects são imutáveis
 * e representam conceitos do domínio que são comparados por igualdade de valor.
 * 
 * Função:
 * - Representar conceitos imutáveis dentro do domínio.
 * - Prover uma estrutura base para classes que encapsulam lógica de negócios
 *   baseada em atributos, sem identidade única.
 *
 * Importância na Arquitetura:
 * Value Objects ajudam a manter o domínio rico e expressivo, encapsulando lógica
 * de negócios específica e promovendo um design mais limpo e orientado a objetos.
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
package br.com.actx.arquitetura.domain.vo;

import java.io.Serializable;

import br.com.actx.arquitetura.common.Identifiable;

public abstract class ValueObject implements Identifiable<Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
