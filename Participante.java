import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Participante {
    private int idParticipante;
    private String nomeParticipante;
    private String loginParticipante;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;

    //construtor 
    public Participante(int idParticipante, String nomeParticipante, String loginParticipante, String senha, String email, String endereco,  String telefone) {
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.loginParticipante = loginParticipante;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    //get/setters
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
    
    // métodos de login, registro e listagem

    public Participante loginParticipante() throws Exception {
        FileReader fr = new FileReader("participantes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        Participante participanteEncontrado = null;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 7) continue;
            
            // Compara o login (2) e senha (4) do arquivo com os dados atuais
            if (dados[2].equals(this.loginParticipante) && dados[4].equals(this.senha)) {
                participanteEncontrado = new Participante(
                    Integer.parseInt(dados[0]),
                    dados[1],
                    dados[2],
                    dados[3],
                    dados[4],
                    dados[5],
                    dados[6]
                );
                break;
            }
        }
        
        br.close();
        return participanteEncontrado;
    }

    //salva os dados 
    public Boolean registrarParticipante() throws Exception {
        FileWriter fw = new FileWriter("participantes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        String linha = this.idParticipante + "," + 
                       this.nomeParticipante + "," + 
                       this.loginParticipante + "," + 
                       this.email + "," + 
                       this.senha + "," + 
                       this.endereco + "," + 
                       this.telefone;
                        
        bw.write(linha);
        bw.newLine();
        bw.close();
        return true;
    }
    
     public ArrayList<Participante> listarParticipantes() throws Exception {
        ArrayList<Participante> lista = new ArrayList<>();
        FileReader fr = new FileReader("participantes.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length < 7) continue;

            Participante p = new Participante(
                Integer.parseInt(dados[0]),
                dados[1],
                dados[2],
                dados[3],
                dados[4],
                dados[5],
                dados[6]
            );

            lista.add(p);
        }

        br.close();
        return lista;
    }

    // Mostrar: Exibe os detalhes principais do participante no console
    public void mostrar() {
        System.out.println("=== Detalhes do Participante ===");
        System.out.println("ID: " + this.idParticipante);
        System.out.println("Nome: " + this.nomeParticipante);
        System.out.println("Email: " + this.email);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("================================");
    }
}