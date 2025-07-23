package br.com.tabelafipe.model;

public class Veiculo {
    private String descricao;
    private Integer ano;
    private String valor;
    private String combustivel;

    public Veiculo(DadosVeiculo dadosVeiculo) {
        this.descricao = dadosVeiculo.descricao();
        this.ano = dadosVeiculo.ano();
        this.valor = dadosVeiculo.valor();
        this.combustivel = dadosVeiculo.combustivel();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    @Override
    public String toString() {
        return descricao + " ano: " + ano + " valor: " + valor + " combustivel: " + combustivel;
    }
}
