package Services;

import FrontEnd.FrontEnd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReplicaManager implements Runnable {

    //Classe que representa o replica manager, responsável pelo salvamento dos backups, cada replica manager possui sua thread.

    private final static String path = "C:\\Users\\Alexandre\\IdeaProjects\\PassiveReplication\\Backup\\";

    private String texto = FrontEnd.getInstance().getInput();
    private String nomeArquivo = FrontEnd.getInstance().getNomeArquivo() + "_backup";
    private Long pid = 0L;
    private String confirmacao = "";

    // Método construtor
    public ReplicaManager(int id) {
        Thread thread = new Thread(this);
        this.pid = thread.getId();
        this.nomeArquivo = this.nomeArquivo + id;
        thread.run();
    }

    // Método mais importante do replica manager, onde ele cria o arquivo backup e realiza o salvamento do mesmo na pasta Backup do diretório especificado
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


    // Método override da thread, resposável pelo start.
    @Override
    public void run() {
        try {
            salvarBackup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter da confirmação do salvamento do arquivo
    public String getConfirmacao() {
        return confirmacao;
    }
}

