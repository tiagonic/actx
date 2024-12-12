/**
 * Interface representando um contrato para identificação de entidades dentro do sistema.
 *
 * Justificativa Arquitetural:
 * Esta interface define um contrato genérico para objetos que possuem uma identidade.
 * Ao estender Serializable, ela garante que as classes que a implementam podem ser 
 * facilmente serializadas, o que é essencial para transferência de dados e persistência.
 * 
 * Função:
 * - Prover uma maneira uniforme de acessar a identidade de objetos.
 * - Facilitar a serialização de objetos, permitindo que sejam transmitidos ou persistidos.
 *
 * Importância na Arquitetura:
 * A interface Identifiable é fundamental para a arquitetura do sistema, pois padroniza 
 * a maneira como a identidade dos objetos é tratada e acessada. Isso promove 
 * consistência e reutilização de código, além de facilitar a integração entre diferentes 
 * componentes do sistema.
 * 
 * @param <ID> Tipo da chave primária da entidade.
 * 
 * @autor Tiago Barreto dos Santos
 * @github https://github.com/tiagonic
 * @projeto https://github.com/tiagonic/actx.git
 * 
 * Data: 12 de Dezembro de 2024
 */
package br.com.actx.arquitetura.common;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> extends Serializable {
	ID getId();
}
