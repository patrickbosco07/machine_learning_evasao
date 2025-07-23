package br.com.tabelafipe.model;

public class Marca {
    private Integer codigo;
    private String nome;

    public Marca(Dados dadosMarca) {
        this.codigo = Integer.valueOf(dadosMarca.codigo());
        this.nome = dadosMarca.nome();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Código = " + codigo + '\'' + ", nome= " + nome;
    }
}
