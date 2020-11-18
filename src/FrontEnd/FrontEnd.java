package FrontEnd;

import javax.swing.*;

public class FrontEnd {

    private String input = "";
    private String nomeArquivo = "";
    private static FrontEnd uniqueInstance;
    private int numeroBackups = 2;

    private FrontEnd() {
        this.nomeArquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo");
        this.input = JOptionPane.showInputDialog(null, "Digite um texto");
    }

    public String getInput() {
        return input;
    }

    public static synchronized FrontEnd getInstance() {
        if(uniqueInstance == null) {
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

//    public void showMessageArquivoAtualizado() {
//        JOptionPane.showMessageDialog(null, "O " + this.nomeArquivo + " foi atualizado");
//    }

    public void showMessageTextoAdicionado() {
        JOptionPane.showMessageDialog(null, "O seguinte texto foi adicionado ao arquivo: " + this.input);
    }

    public void showMessageSemTexto() {
        JOptionPane.showMessageDialog(null, "Nenhum texto foi digitado");
    }


    public void showMessageArquivoSemTitulo() {
        JOptionPane.showMessageDialog(null, "O nome do arquivo é obrigatorio");
    }

    public void showMessageConfirmacao(String confirmacao) {
        JOptionPane.showMessageDialog(null, confirmacao);
    }

    public void showMessageConfirmacaoPrincipal(Long pid) {
        JOptionPane.showMessageDialog(null, "O arquivo principal foi gravado com sucesso pelo Replica Manager " + pid);
    }
}
