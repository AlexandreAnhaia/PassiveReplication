package Services;

import FrontEnd.FrontEnd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReplicaManager implements Runnable {

    private final static String path = "C:\\Users\\Alexandre\\IdeaProjects\\PassiveReplication\\Backup\\";

    private String texto = FrontEnd.getInstance().getInput();
    private String nomeArquivo = FrontEnd.getInstance().getNomeArquivo() + "_backup";
    private Long pid = 0L;
    private String confirmacao = "";

    public ReplicaManager(int id) {
        Thread thread = new Thread(this);
        this.pid = thread.getId();
        this.nomeArquivo = this.nomeArquivo + id;
        thread.run();
    }

    private void salvarBackup() throws IOException {
        FileWriter arquivo = new FileWriter(path + nomeArquivo + ".txt", true);
        File file = new File(path + this.nomeArquivo + ".txt");
        if (file.length() > 0) {
            arquivo.append(" " + texto);
        } else {
            arquivo.append(texto);
        }
        PrintWriter gravaDados = new PrintWriter(arquivo);
        arquivo.close();
        this.confirmacao =  "ReplicaManager " + pid + " confirma o salvamento de backup";
    }

    @Override
    public void run() {
        try {
            salvarBackup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getConfirmacao() {
        return confirmacao;
    }
}

