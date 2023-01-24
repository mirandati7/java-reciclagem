package org.example.future;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * Cliente do servidor desenvolvido para testar as classes de execução em paralelo
 *
 * @author Eduardo Santana
 *
 */
public class Cliente {

    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 8000;
        try {
            System.out.println("Iniciando a conexão!");
            //faz a conexão
            Socket client = new Socket(serverName, port);
            System.out.println("Conectado a: " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();

            // envia a mensagem para o servidor
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Olá Servidor! " + client.getLocalSocketAddress());

            // recebe a resposta do servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            //imprime a resposta
            System.out.println("Resposta: " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}