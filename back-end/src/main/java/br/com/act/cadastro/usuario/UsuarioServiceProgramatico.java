package br.com.act.cadastro.usuario;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UsuarioServiceProgramatico {

    @Resource
    private UserTransaction userTransaction;

    public void executeBusinessLogic() {
        try {
            userTransaction.begin();
            // Lógica de negócios aqui
            userTransaction.commit();
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception rollbackException) {
                // Tratamento de erro
            }
            // Tratamento de erro
        }
    }
}
