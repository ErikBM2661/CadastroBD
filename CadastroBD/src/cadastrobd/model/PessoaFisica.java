package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    String cpf;

    public PessoaFisica() {}

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
    
    @Override
public String toString() {
    return """
           Pessoa F\u00edsica:
           ID: """ + id + "\n" +
           "Nome: " + nome + "\n" +
           "Logradouro: " + logradouro + "\n" +
           "Cidade: " + cidade + "\n" +
           "Estado: " + estado + "\n" +
           "Telefone: " + telefone + "\n" +
           "Email: " + email + "\n" +
           "CPF: " + cpf + "\n";
}
    
}

