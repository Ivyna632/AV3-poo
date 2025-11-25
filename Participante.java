import java.util.ArrayList;

public class Participante {
    private int idParticipante;
    private String nomeParticipante;
    private String loginParticipante;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;

    public int getidParticipante() {
        return idParticipante;
    }
    public void setidParticipante(int idPartcipante) {
        this.idParticipante = idPartcipante;
    }
    public String getnomeParticipante() {
        return nomeParticipante;
    }
    public void setnomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }
    public String getloginParticipante() {
        return loginParticipante;
    }
    public void setloginParticipante(String loginParticipante) {
        this.loginParticipante = loginParticipante;
    }
    public String getsenha() {
        return senha;
    }
    public void setsenha(String senha) {
        this.senha =senha;
    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email =email;
    }
    public String getendereco() {
        return endereco;
    }
    public void setendereco(String endereco) {
        this.endereco =endereco;
    }
    public String gettelefone() {
        return telefone;
    }
    public void settelefone(String telefone) {
        this.telefone =telefone;
    }

    public Participante loginParticipante() {
        return this.loginParticipante();
    }
    
    // métodos de login, registro e listagem
    public Boolean loginParticipante(String login, String senha) {
        return true;
    }

    public Boolean registrarParticipante(String nome, String login, String email, String telefone) {
        return true;
    }
    
    //terminar método
    public ArrayList<Participante> listarParticipantes() {
        return null;
    }

    //método mostrar 
    public void mostrar() {
        System.out.println("=== Detalhes do Participante ===");
        System.out.println("ID: " + this.idParticipante);
        System.out.println("Nome: " + this.nomeParticipante);
        System.out.println("Email: " + this.email);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("================================");
    }
}