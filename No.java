import java.util.List;

public class No {
    public Departamento departamento;
    public Funcionario gerente;
    public List<Funcionario> funcionarios;

    public No esquerda;
    public No direita;
    public int altura;

    public No(Departamento departamento, Funcionario gerente) {
        this.departamento = departamento;
        this.gerente = gerente;
        this.funcionarios = departamento.getFuncionarios();
        this.altura = 1;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Funcionario getGerente() {
        return gerente;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public No rotacaoDireita() {
        No x = esquerda;
        No T2 = x.direita;

        x.direita = this;
        this.esquerda = T2;

        this.atualizarAltura(x, this);
        return x;
    }

    public No rotacaoEsquerda() {
        No y = direita;
        No T2 = y.esquerda;

        y.esquerda = this;
        this.direita = T2;

        this.atualizarAltura(this, y);
        return y;
    }

    public No rotacaoEsquerdaDireita() {
        esquerda = esquerda.rotacaoEsquerda();
        return rotacaoDireita();
    }

    public No rotacaoDireitaEsquerda() {
        direita = direita.rotacaoDireita();
        return rotacaoEsquerda();
    }

    public void atualizarAltura(No no1, No no2) {
        no1.altura = calcularAltura(no1);
        no2.altura = calcularAltura(no2);
    }

    public static int calcularAltura(No no) {
        if (no == null) {
            return 0;
        }
        return Math.max(altura(no.esquerda), altura(no.direita)) + 1;
    }

    public static No inserirDepartamento(No raiz, Departamento departamento) {
        if (raiz == null) {
            return new No(departamento, departamento.getGerente());
        }

        if (departamento.getId() < raiz.getDepartamento().getId()) {
            raiz.setEsquerda(inserirDepartamento(raiz.getEsquerda(), departamento));
        } else if (departamento.getId() > raiz.getDepartamento().getId()) {
            raiz.setDireita(inserirDepartamento(raiz.getDireita(), departamento));
        } else {
            System.out.println("Departamento com ID " + departamento.getId() + " já existe na árvore.");
            return raiz;
        }

        raiz.atualizarAltura(raiz, raiz);

        int balanceamento = obterBalanceamento(raiz);

        if (balanceamento > 1) {
            if (departamento.getId() < raiz.getEsquerda().getDepartamento().getId()) {
                return raiz.rotacaoDireita();
            } else {
                return raiz.rotacaoEsquerdaDireita();
            }
        }

        if (balanceamento < -1) {
            if (departamento.getId() > raiz.getDireita().getDepartamento().getId()) {
                return raiz.rotacaoEsquerda();
            } else {
                return raiz.rotacaoDireitaEsquerda();
            }
        }

        return raiz;
    }

    public static No adicionarFuncionario(No raiz, Funcionario funcionario, int idDepartamento) {
        if (raiz == null) {
            System.out.println("Departamento com ID " + idDepartamento + " não encontrado na árvore.");
            return null;
        }

        if (idDepartamento < raiz.getDepartamento().getId()) {
            raiz.setEsquerda(adicionarFuncionario(raiz.getEsquerda(), funcionario, idDepartamento));
        } else if (idDepartamento > raiz.getDepartamento().getId()) {
            raiz.setDireita(adicionarFuncionario(raiz.getDireita(), funcionario, idDepartamento));
        } else {
            raiz.getFuncionarios().add(funcionario);
            System.out.println("Funcionário adicionado ao Departamento " + idDepartamento + ".");
        }

        raiz.atualizarAltura(raiz, raiz);

        int balanceamento = obterBalanceamento(raiz);

        if (balanceamento > 1) {
            if (idDepartamento < raiz.getEsquerda().getDepartamento().getId()) {
                return raiz.rotacaoDireita();
            } else {
                return raiz.rotacaoEsquerdaDireita();
            }
        }

        if (balanceamento < -1) {
            if (idDepartamento > raiz.getDireita().getDepartamento().getId()) {
                return raiz.rotacaoEsquerda();
            } else {
                return raiz.rotacaoDireitaEsquerda();
            }
        }

        return raiz;
    }

    public static No removerFuncionario(No raiz, int idFuncionario) {
        if (raiz == null) {
            System.out.println("Funcionário com ID " + idFuncionario + " não encontrado na árvore.");
            return null;
        }

        Departamento departamento = encontrarDepartamento(raiz, idFuncionario);

        if (departamento != null) {
            departamento.removerFuncionarioClasse(idFuncionario);
            System.out.println("Funcionário removido do Departamento " + departamento.getId() + ".");
        } else {
            System.out.println("Funcionário com ID " + idFuncionario + " não encontrado na árvore.");
        }

        raiz.atualizarAltura(raiz, raiz);

        int balanceamento = obterBalanceamento(raiz);

        if (balanceamento > 1) {
            if (obterBalanceamento(raiz.getEsquerda()) >= 0) {
                return raiz.rotacaoDireita();
            } else {
                return raiz.rotacaoEsquerdaDireita();
            }
        }

        if (balanceamento < -1) {
            if (obterBalanceamento(raiz.getDireita()) <= 0) {
                return raiz.rotacaoEsquerda();
            } else {
                return raiz.rotacaoDireitaEsquerda();
            }
        }

        return raiz;
    }

    private static int obterBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    private static int altura(No no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }

    public static void preOrdem(No no) {
        if (no != null) {
            System.out.println("Departamento: " + no.getDepartamento().getNome());
            System.out.println("Gerente: " + no.getGerente().getNome());
            System.out.println("Funcionários:");

            for (Funcionario funcionario : no.getFuncionarios()) {
                System.out.println("- " + funcionario.getNome() + " (" + funcionario.getCargo() + ")");
            }

            System.out.println();

            preOrdem(no.getEsquerda());
            preOrdem(no.getDireita());
        }
    }

    private static Departamento encontrarDepartamento(No raiz, int idFuncionario) {
        if (raiz == null) {
            return null;
        }

        for (Funcionario funcionario : raiz.getFuncionarios()) {
            if (funcionario.getId() == idFuncionario) {
                return raiz.getDepartamento();
            }
        }

        Departamento encontradoEsquerda = encontrarDepartamento(raiz.getEsquerda(), idFuncionario);
        Departamento encontradoDireita = encontrarDepartamento(raiz.getDireita(), idFuncionario);

        return (encontradoEsquerda != null) ? encontradoEsquerda : encontradoDireita;
    }
}
