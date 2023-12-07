import java.util.ArrayList;
import java.util.List;

public class Departamento {
    public int id;
    public String nome;
    public Funcionario gerente;
    public List<Funcionario> funcionarios;

    public Departamento(int id, String nome, Funcionario gerente) {
        this.id = id;
        this.nome = nome;
        this.gerente = gerente;
        this.funcionarios = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Funcionario getGerente() {
        return gerente;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGerente(Funcionario gerente) {
        this.gerente = gerente;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerFuncionarioClasse(int idFuncionario) {
        for (int i = 0; i <= funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == idFuncionario) {
                funcionarios.remove(funcionarios.get(i));
            }
        }
    }
}
