package Model.Bean;

public class Funcionario {

    private String nome;
    private int id;
    private int funcao;
    private String setor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSetor(String setor) {

        this.setor = setor;
    }

    public String getSetor() {

        return setor;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }

}
