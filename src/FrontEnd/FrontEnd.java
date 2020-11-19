package FrontEnd;

import javax.swing.*;

public class FrontEnd {

    // Essa classe é o frontend do trabalho.
    private String input = "";
    private String nomeArquivo = "";
    private static FrontEnd uniqueInstance;
    private int numeroBackups = 2;


    //Método construtor
    private FrontEnd() {
        this.nomeArquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo");
        this.input = JOptionPane.showInputDialog(null, "Digite um texto");
    }

    public String getInput() {
        return input;
    }

    // Singleton para evitar diversos frontend sendo instanciados sem necessidade
    public static synchronized FrontEnd getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FrontEnd();
        }
        return uniqueInstance;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public int getNumeroBackups() {
        return numeroBackups;
    }

    // Método que imprime mensagem na tela
    public void showMessageTextoAdicionado() {
        JOptionPane.showMessageDialog(null, "O seguinte texto foi adicionado ao arquivo: " + this.input);
    }
    // Método que imprime mensagem na tela
    public void showMessageSemTexto() {
        JOptionPane.showMessageDialog(null, "Nenhum texto foi digitado");
    }

    // Método que imprime mensagem na tela
    public void showMessageArquivoSemTitulo() {
        JOptionPane.showMessageDialog(null, "O nome do arquivo é obrigatorio");
    }

    // Método que imprime mensagem na tela
    public void showMessageConfirmacao(String confirmacao) {
        JOptionPane.showMessageDialog(null, confirmacao);
    }

    // Método que imprime mensagem na tela
    public void showMessageConfirmacaoPrincipal(Long pid) {
        JOptionPane.showMessageDialog(null, "O arquivo principal foi gravado com sucesso pelo Replica Manager " + pid);
    }
}
