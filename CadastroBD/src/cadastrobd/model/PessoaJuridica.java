package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    String cnpj;

    public PessoaJuridica() {}

    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
    
    @Override
public String toString() {
    return """
           Pessoa Jur\u00eddica:
           ID: """ + id + "\n" +
           "Nome: " + nome + "\n" +
           "Logradouro: " + logradouro + "\n" +
           "Cidade: " + cidade + "\n" +
           "Estado: " + estado + "\n" +
           "Telefone: " + telefone + "\n" +
           "Email: " + email + "\n" +
           "CNPJ: " + cnpj + "\n";
}
}