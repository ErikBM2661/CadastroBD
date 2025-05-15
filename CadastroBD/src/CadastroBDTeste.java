import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
            PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
            int opcao;

            do {
                System.out.println("MENU");
                System.out.println("1 - Incluir");
                System.out.println("2 - Alterar");
                System.out.println("3 - Excluir");
                System.out.println("4 - Exibir por ID");
                System.out.println("5 - Exibir todos");
                System.out.println("0 - Sair");
                
                opcao = lerInt(scanner, "Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> incluir(scanner, pfDAO, pjDAO);
                    case 2 -> alterar(scanner, pfDAO, pjDAO);
                    case 3 -> excluir(scanner, pfDAO, pjDAO);
                    case 4 -> exibirPorId(scanner, pfDAO, pjDAO);
                    case 5 -> exibirTodos(scanner, pfDAO, pjDAO);
                    case 0 -> System.out.println("Encerrando o programa...");
                    default -> System.out.println("Opção inválida!");
                }
            } while (opcao != 0);
        }
    }

    private static int lerInt(Scanner scanner, String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    private static void incluir(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        int tipo = lerInt(scanner, "Tipo (1 - Física, 2 - Jurídica): ");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                PessoaFisica pf = new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
                pfDAO.incluir(pf);
                System.out.println("Pessoa Física incluída com sucesso!");
            }
            case 2 -> {
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                PessoaJuridica pj = new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
                pjDAO.incluir(pj);
                System.out.println("Pessoa Jurídica incluída com sucesso!");
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        int tipo = lerInt(scanner, "Tipo (1 - Física, 2 - Jurídica): ");
        int id = lerInt(scanner, "ID: ");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                PessoaFisica pf = new PessoaFisica(id, nome, logradouro, cidade, estado, telefone, email, cpf);
                pfDAO.alterar(pf);
                System.out.println("Pessoa Física alterada com sucesso!");
            }
            case 2 -> {
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                PessoaJuridica pj = new PessoaJuridica(id, nome, logradouro, cidade, estado, telefone, email, cnpj);
                pjDAO.alterar(pj);
                System.out.println("Pessoa Jurídica alterada com sucesso!");
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        int tipo = lerInt(scanner, "Tipo (1 - Física, 2 - Jurídica): ");
        int id = lerInt(scanner, "ID: ");

        switch (tipo) {
            case 1 -> {
                pfDAO.excluir(id);
                System.out.println("Pessoa Física excluída (se existia).");
            }
            case 2 -> {
                pjDAO.excluir(id);
                System.out.println("Pessoa Jurídica excluída (se existia).");
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void exibirPorId(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        int tipo = lerInt(scanner, "Tipo (1 - Física, 2 - Jurídica): ");
        int id = lerInt(scanner, "ID: ");

        switch (tipo) {
            case 1 -> {
                PessoaFisica pf = pfDAO.getPessoa(id);
                System.out.println(pf != null ? pf : "Pessoa Física não encontrada!");
            }
            case 2 -> {
                PessoaJuridica pj = pjDAO.getPessoa(id);
                System.out.println(pj != null ? pj : "Pessoa Jurídica não encontrada!");
            }
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        int tipo = lerInt(scanner, "Tipo (1 - Física, 2 - Jurídica): ");

        switch (tipo) {
            case 1 ->                 {
                    List<PessoaFisica> pessoas = pfDAO.getPessoas();
                    if (pessoas.isEmpty()) {
                        System.out.println("Nenhuma Pessoa Física cadastrada.");
                    } else {
                        pessoas.forEach(System.out::println);
                    }                      }
            case 2 ->                 {
                    List<PessoaJuridica> pessoas = pjDAO.getPessoas();
                    if (pessoas.isEmpty()) {
                        System.out.println("Nenhuma Pessoa Jurídica cadastrada.");
                    } else {
                        pessoas.forEach(System.out::println);
                    }                      }
            default -> System.out.println("Tipo inválido!");
        }
    }
}