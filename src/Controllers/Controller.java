package Controllers;

import FrontEnd.FrontEnd;
import Services.ReplicaManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller implements Runnable {
    // Classe controller e onde é salvo o arquivo primário (original)
    private final static String path = "C:\\Users\\Alexandre\\IdeaProjects\\PassiveReplication\\";

    private FrontEnd frontEnd = FrontEnd.getInstance();
    private String texto = frontEnd.getInput();
    private ReplicaManager replicaManagerUm;
    private ReplicaManager replicaManagerDois;
    private Long pid = 0L;

    // Método construtor da classe controller, ele possui sua própria thread de execução.
    public Controller() {
        Thread thread = new Thread(this);
        this.pid = thread.getId();
        thread.run();
    }

    // Esse método é o principal método do sistema, onde ele pega o texto digitado pelo usuário, cria o arquivo no diretório  especificado, faz também as validações necessárias
    // Então ele salva primeiramente o arquivo original, e então chama a classe ReplicaManager para salvar os backups, em threads separadas.
    public void salvaTexto() throws IOException {
        FileWriter arquivo = new FileWriter(path + frontEnd.getNomeArquivo() + "Principal.txt", true);
        File file = new File(path + frontEnd.getNomeArquivo() + "Principal.txt");
        if (!frontEnd.getNomeArquivo().equals("")) {
            if (!texto.equals("")) {
                if (file.length() > 0) {
                    arquivo.append(" " + texto);
                } else {
                    arquivo.append(texto);
                }
                PrintWriter gravaDados = new PrintWriter(arquivo);
                arquivo.close();
                frontEnd.showMessageTextoAdicionado();
                salvarBackup();
                frontEnd.showMessageConfirmacaoPrincipal(pid);
                frontEnd.showMessageConfirmacao(replicaManagerUm.getConfirmacao());
                frontEnd.showMessageConfirmacao(replicaManagerDois.getConfirmacao());
            } else {
                frontEnd.showMessageSemTexto();
            }
        } else {
            frontEnd.showMessageArquivoSemTitulo();
        }
    }

    // Método que faz a chamada para o replica manager salvar os backups.
    public void salvarBackup() {
        replicaManagerUm = new ReplicaManager(1);
        replicaManagerDois = new ReplicaManager(2);
    }

    // Método override da thread para dar o "start"
    @Override
    public void run() {
        try {
            salvaTexto();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
