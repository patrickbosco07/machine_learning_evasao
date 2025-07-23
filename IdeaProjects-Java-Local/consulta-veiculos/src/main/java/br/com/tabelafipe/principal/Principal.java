package br.com.tabelafipe.principal;

import br.com.tabelafipe.model.*;
import br.com.tabelafipe.service.ConsumoAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDado converteDado = new ConverteDado();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibirMenu() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("""
                ******* INICIANDO *******\n
                ******* OPÇÕES ******* \n
                Carros
                Moto
                Caminhão
                """);
        System.out.println("Digite o tipo de veículo que deseja consultar:");
        var tipoVeiculo = leitura.nextLine();
        var retorno = "";
        var UrlConsulta = "";
        if (tipoVeiculo.equalsIgnoreCase("carro")) {
            UrlConsulta = ENDERECO + tipoVeiculo + "/marcas";
            retorno = consumoAPI.consumoApi(UrlConsulta);
        } else if (tipoVeiculo.equalsIgnoreCase("caminhao")) {
            UrlConsulta = ENDERECO + tipoVeiculo + "/marcas";
            retorno = consumoAPI.consumoApi(UrlConsulta);
        } else {
            UrlConsulta = ENDERECO + tipoVeiculo + "/marcas";
            retorno = consumoAPI.consumoApi(UrlConsulta);
        }

        var dadosMarcaList = converteDado.obterList(retorno, Dados.class);


        if (!dadosMarcaList.isEmpty()) {
            System.out.println("\nLista de marcas do tipo de veículo:");
            dadosMarcaList.stream()
                    .map(m -> new Marca(m))
                    .sorted(Comparator.comparing(Marca::getCodigo))
                    .forEach(System.out::println);

            System.out.println("\nEscolha o código da marca desejada");
            var marca = leitura.nextLine();

            UrlConsulta += "/" + marca + "/modelos";
            retorno = consumoAPI.consumoApi(UrlConsulta);
            DadosModelosMarca dadosModelosMarca = converteDado.converteDado(retorno, DadosModelosMarca.class);
            List<Modelo> listaModelos = dadosModelosMarca.modelos().stream()
                    .map(m -> new Modelo(m))
                    .sorted(Comparator.comparing(Modelo::getNome))
                    .collect(Collectors.toList());
            System.out.println("Lista de modelos da marca escolhida:");
            listaModelos.forEach(System.out::println);

            System.out.println("\nDigite um trecho do modelo que quer visualizar");
            var trecho = leitura.nextLine();
            List<Modelo> modelosEspecificos = listaModelos.stream()
                    .filter(m -> m.getNome().toUpperCase().contains(trecho.toUpperCase()))
                    .collect(Collectors.toList());
            System.out.println("\nLista de modelos que contém a palavra informada no nome:");
            modelosEspecificos.forEach(System.out::println);

            System.out.println("\nEscolha um modelo específico pelo código: ");
            var modeloEspecifico = leitura.nextLine();

            UrlConsulta += "/" + modeloEspecifico + "/anos";
            retorno = consumoAPI.consumoApi(UrlConsulta);
            var dadosAnos = converteDado.obterList(retorno, Dados.class);
            List<DadosVeiculo> listaVeiculos = new ArrayList<>();
            for (Dados dado : dadosAnos) {
                retorno = consumoAPI.consumoApi(UrlConsulta + "/" + dado.codigo());
                DadosVeiculo dadosVeiculo = converteDado.converteDado(retorno, DadosVeiculo.class);
                listaVeiculos.add(dadosVeiculo);
            }
            System.out.println("\nLista com todos os anos lançados do modelo escolhido: ");
            listaVeiculos.stream()
                    .map(Veiculo::new)
                    .sorted(Comparator.comparing(Veiculo::getAno))
                    .forEach(System.out::println);

        } else {
            System.out.println("\nUm erro ocorreu, encerrando o programa.");
        }


    }
}
