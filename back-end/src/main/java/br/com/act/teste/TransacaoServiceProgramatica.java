package br.com.act.teste;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.act.cadastro.usuario.UsuarioDAO;
import br.com.act.cadastro.usuario.UsuarioEntity;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransacaoServiceProgramatica {

	@EJB
	private UsuarioDAO usuarioDAO;

	@Resource
	private UserTransaction userTransaction;

	private TransactionManager transactionManager;

	public TransacaoServiceProgramatica() {
		try {
			InitialContext ic = new InitialContext();
			transactionManager = (TransactionManager) ic.lookup("java:jboss/TransactionManager");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void transacaoProgramaticaUserTransaction(UsuarioEntity entity) {
		try {
			userTransaction.begin();
			usuarioDAO.persist(entity);
			userTransaction.commit();
		} catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (Exception rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void transacaoProgramaticaTransactionManager(UsuarioEntity entity) {
		try {
			transactionManager.begin();
			usuarioDAO.persist(entity);
			transactionManager.commit();
		} catch (Exception e) {
			try {
				transactionManager.rollback();
			} catch (Exception rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public UserTransaction getUserTransaction() {
		return userTransaction;
	}

	public void setUserTransaction(UserTransaction userTransaction) {
		this.userTransaction = userTransaction;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
