import java.util.Scanner;

public class Empresa {
    public No raiz;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        Empresa empresa = new Empresa();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Departamento");
            System.out.println("2. Adicionar Funcionário");
            System.out.println("3. Remover Funcionário");
            System.out.println("4. Visualizar Árvore");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Entre com os dados do Departamento:");
                    Departamento departamento = criarDepartamento(scanner);
                    empresa.inserirDepartamento(departamento);
                    break;
                case 2:
                    if (empresa.raiz != null) {
                    
                        System.out.println("Entre com os dados do Funcionário:");
                        Funcionario funcionario = criarFuncionario(scanner);
                        scanner.nextLine();
                        System.out.print("ID do Departamento: ");
                        int idDepartamento = scanner.nextInt();
                        empresa.adicionarFuncionario(funcionario, idDepartamento);
                    } else {
                        System.out.println("Adicione um departamento antes de adicionar funcionários.");
                    }
                    break;
                case 3:
                    if (empresa.raiz != null) {
                        System.out.println("Entre com o ID do Funcionário a ser removido:");
                        int idFuncionario = scanner.nextInt();
                        No.removerFuncionario(empresa.raiz, idFuncionario);
                    } else {
                        System.out.println("A árvore está vazia. Adicione departamentos e funcionários primeiro.");
                    }
                    break;
                case 4:
                    if (empresa.raiz != null) {
                        System.out.println("Árvore Organizacional (Pré-Ordem):");
                        No.preOrdem(empresa.raiz);
                    } else {
                        System.out.println("A árvore está vazia.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Departamento criarDepartamento(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();

        System.out.print("ID do Departamento: ");
        int id = scanner.nextInt();

        System.out.print("ID do Gerente: ");
        int idGerente = scanner.nextInt();
        Funcionario gerente = new Funcionario(idGerente, "Gerente", "Cargo");

        return new Departamento(id, nome, gerente);
    }

    private static Funcionario criarFuncionario(Scanner scanner) {
        System.out.print("ID do Funcionário: ");
        int id = scanner.nextInt();

        System.out.print("Nome do Funcionário: ");
        String nome = scanner.next();

        System.out.print("Cargo do Funcionário: ");
        String cargo = scanner.next();

        return new Funcionario(id, nome, cargo);
    }

    private void inserirDepartamento(Departamento departamento) {
        raiz = No.inserirDepartamento(raiz, departamento);
    }

    private void adicionarFuncionario(Funcionario funcionario, int idDepartamento) {
        raiz = No.adicionarFuncionario(raiz, funcionario, idDepartamento);
    }
}
