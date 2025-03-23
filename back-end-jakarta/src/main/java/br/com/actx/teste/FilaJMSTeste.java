package br.com.actx.teste;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import jakarta.jms.QueueConnection;
import jakarta.jms.QueueConnectionFactory;
import jakarta.jms.QueueReceiver;
import jakarta.jms.QueueSender;
import jakarta.jms.QueueSession;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class FilaJMSTeste {

    public static void main(String[] args) {
        // Contexto inicial
        InitialContext context = null;
        QueueConnectionFactory connectionFactory = null;
        QueueConnection connection = null;
        QueueSession session = null;
        Queue queue = null;
        QueueSender sender = null;
        QueueReceiver receiver = null;

        try {
            // Criar contexto inicial
            context = new InitialContext();

            // Obter a fábrica de conexão e a fila
            connectionFactory = (QueueConnectionFactory) context.lookup("java:/ConnectionFactory");
            queue = (Queue) context.lookup("java:/jms/queue/ExpiryQueue");

            // Criar conexão, sessão e remetente
            connection = connectionFactory.createQueueConnection();
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createSender(queue);

            // Criar mensagem de texto
            TextMessage message = session.createTextMessage("Mensagem de teste na fila ExpiryQueue");
            sender.send(message);
            System.out.println("Mensagem enviada: " + message.getText());

            // Criar receptor e receber mensagem
            receiver = session.createReceiver(queue);
            connection.start();
            TextMessage receivedMessage = (TextMessage) receiver.receive(1000);
            System.out.println("Mensagem recebida: " + (receivedMessage != null ? receivedMessage.getText() : "Nenhuma mensagem recebida"));

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                // Fechar recursos
                if (sender != null) sender.close();
                if (receiver != null) receiver.close();
                if (session != null) session.close();
                if (connection != null) connection.close();
                if (context != null) context.close();
            } catch (JMSException | NamingException e) {
                e.printStackTrace();
            }
        }
    }
}