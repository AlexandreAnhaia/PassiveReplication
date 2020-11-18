package Controllers;

import FrontEnd.FrontEnd;
import Services.ReplicaManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller implements Runnable {

    private final static String path = "C:\\Users\\Alexandre\\IdeaProjects\\PassiveReplication\\";

    private FrontEnd frontEnd = FrontEnd.getInstance();
    private String texto = frontEnd.getInput();
    private ReplicaManager replicaManagerUm;
    private ReplicaManager replicaManagerDois;
    private Long pid = 0L;

    public Controller() {
        Thread thread = new Thread(this);
        this.pid = thread.getId();
        thread.run();
    }

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
//                frontEnd.showMessageArquivoAtualizado();
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

    public void salvarBackup() {
        replicaManagerUm = new ReplicaManager(1);
        replicaManagerDois = new ReplicaManager(2);
    }


    @Override
    public void run() {
        try {
            salvaTexto();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
