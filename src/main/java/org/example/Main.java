package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("""
                - Sistema de Gestão de Fornecedores e Equipamentos -
                
                1 - Cadastrar Fornecedor
                2 - Cadastrar Equipamento
                3 - Buscar Fornecedor por ID
                4 - Buscar Equipamento por ID
                5 - Listar Todos os Fornecedores
                6 - Listar Todos os Equipamentos por ID do Fornecedor
                7 - Atualizar Fornecedor
                8 - Atualizar Equipamento
                9 - Deletar Fornecedor
                10 - Deletar Equipamento
                
                Escreva: 
                """);

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 : {
                cadastrarFornecedor();
                break;
            }

            case 2 : {

                break;
            }

            case 3 : {
                buscarFornecedorPorId();
                break;
            }

            case 4 : {

                break;
            }

            case 5 : {

                break;
            }

            case 6 : {

                break;
            }

            case 7 : {

                break;
            }

            case 8 : {

                break;
            }

            case 9 : {

                break;
            }

            case 10 : {

                break;
            }
        }
    }

    public static void cadastrarFornecedor(){
        System.out.println("Qual o nome do Fornecedor que deseja cadastrar?: ");
        sc.nextLine();
        String nome = sc.nextLine();

        System.out.println("Qual o CNPJ do Fornecedor que deseja cadastrar?: ");
        String cnpj = sc.nextLine();

        var dao = new SistemaDAO();

        try {
            dao.cadastrarFornecedor(new Fornecedor(nome, cnpj));
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar um Fornecedor!");
            e.printStackTrace();
        }
    }

    public static void buscarFornecedorPorId(){
        Fornecedor fornecedor = null;
        System.out.println("Qual o ID do Fornecedor que deseja buscar?: ");
        int buscaId = sc.nextInt();

        var dao = new SistemaDAO();

        try {
            fornecedor = dao.buscarFornecedorPorId(buscaId);
        } catch (SQLException e) {
            System.out.println("ID do Fornecedor não encontrado!");
            e.printStackTrace();
        }
        if(fornecedor != null) {
            System.out.println("id | nome | cnpj");
            System.out.println(fornecedor.getId() + " | " + fornecedor.getNome() + " | " + fornecedor.getCnpj());
        } else {
            System.out.println("O ID do Fornecedor é inexistente!");
        }
    }

    public static void listarTodosFornecedores(){

    }

}