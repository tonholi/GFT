package Model.Bean;

public class Servico {

    private String nome;
    private int solicitacao;
    private String email;
    private String tipo;
    private String setor;
    private String obterSetor;
    private String funcionario;
    private int ativo;

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(int solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String obterSetor(int tipo) {
        if (tipo == 1) {
            obterSetor = "Cidadania";
        } else if (tipo >= 2 && tipo <= 5) {
            obterSetor = "estado Civil";
        } else if (tipo == 6) {
            obterSetor = "Naturalizacao";
        } else if (tipo == 7) {
            obterSetor = "Passaporte";
        } else if (tipo == 8) {
            obterSetor = "Visto";
        } else {
            obterSetor = "ERRO!";
        }
        return obterSetor;
    }

    @Override
    public String toString() {
        return this.solicitacao + " | Solicitante: " + this.nome + " | Tipo: " + this.setor + "\n";
    }

}
