import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList; 

public class Leilao {
    private int idLeilao;
    private Date dataInicioLeilao;
    private Time horaInicioLeilao;
    private Date dataFimLeilao;
    private Time horaFimLeilao;
    private Boolean statusLeilao;

    //construtor 
    public Leilao(int id) {
        this.idLeilao = id;
        this.statusLeilao = false; 
        this.dataInicioLeilao = null;
        this.horaInicioLeilao = null;
        this.dataFimLeilao = null;
        this.horaFimLeilao = null;
    }

    //get/setters
    public int getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(int idLeilao) {
        this.idLeilao = idLeilao;
    }

    public Date getDataInicioLeilao() {
        return dataInicioLeilao;
    }

    public void setDataInicioLeilao(Date dataInicioLeilao) {
        this.dataInicioLeilao = dataInicioLeilao;
    }

    public Time getHoraInicioLeilao() {
        return horaInicioLeilao;
    }

    public void setHoraInicioLeilao(Time horaInicioLeilao) {
        this.horaInicioLeilao = horaInicioLeilao;
    }

    public Date getDataFimLeilao() {
        return dataFimLeilao;
    }

    public void setDataFimLeilao(Date dataFimLeilao) {
        this.dataFimLeilao = dataFimLeilao;
    }

    public Time getHoraFimLeilao() {
        return horaFimLeilao;
    }

    public void setHoraFimLeilao(Time horaFimLeilao) {
        this.horaFimLeilao = horaFimLeilao;
    }

    public Boolean getStatusLeilao() {
        return statusLeilao;
    }

    public void setStatusLeilao(Boolean statusLeilao) {
        this.statusLeilao = statusLeilao;
    }

    public int getId() {
        return this.idLeilao;
    }

    //métodos de consultar, registrar, iniciar, finalizar e listar leilões

    public Leilao consultarLeilao() throws Exception {
        try (FileReader fr = new FileReader("leiloes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(","); // Lê separado por vírgula
                if (dados.length < 6) continue;

                int idLido = Integer.parseInt(dados[0]);

                if (idLido == this.idLeilao) {
                    Leilao leilaoEncontrado = new Leilao(idLido);
                    leilaoEncontrado.dataInicioLeilao = dados[1].equals("null") ? null : Date.valueOf(dados[1]);
                    leilaoEncontrado.horaInicioLeilao = dados[2].equals("null") ? null : Time.valueOf(dados[2]);
                    leilaoEncontrado.dataFimLeilao    = dados[3].equals("null") ? null : Date.valueOf(dados[3]);
                    leilaoEncontrado.horaFimLeilao    = dados[4].equals("null") ? null : Time.valueOf(dados[4]);
                    leilaoEncontrado.statusLeilao = Boolean.parseBoolean(dados[5]);
                    
                    return leilaoEncontrado;
                }
            }
        }
        return null;
    }

    public Boolean registrarLeilao() throws Exception {
        String dIni = String.valueOf(this.dataInicioLeilao);
        String hIni = String.valueOf(this.horaInicioLeilao);
        String dFim = String.valueOf(this.dataFimLeilao);
        String hFim = String.valueOf(this.horaFimLeilao);

        try (FileWriter fw = new FileWriter("leiloes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String linha = this.idLeilao + "," +
                           dIni + "," + hIni + "," +
                           dFim + "," + hFim + "," +
                           this.statusLeilao;

            bw.write(linha);
            bw.newLine();
            bw.close();
            return true;
        }
    }

    public Boolean iniciarLeilao(Date data, Time hora) {
        if (data == null || hora == null) return false;
        this.dataInicioLeilao = data;
        this.horaInicioLeilao = hora;
        this.statusLeilao = true;
        return true;
    } 


    public Boolean finalizarLeilao(Date data, Time hora) {
        if (data == null || hora == null) return false;
        this.dataFimLeilao = data;
        this.horaFimLeilao = hora;
        this.statusLeilao = false;
        return true;
    }

    public void atualizarLeilaoNoArquivo() throws Exception {
        ArrayList<String> todasLinhas = new ArrayList<>();
        
        try (FileReader fr = new FileReader("leiloes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String linhaLeitura;
            while ((linhaLeitura = br.readLine()) != null) {
                todasLinhas.add(linhaLeitura);
            }
        }

        try (FileWriter fw = new FileWriter("leiloes.txt", false); 
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (String linha : todasLinhas) {
                String[] dados = linha.split(",");
                if (dados.length > 0) {
                    int idLido = Integer.parseInt(dados[0]);

                    if (idLido == this.idLeilao) {
                        String dIni = String.valueOf(this.dataInicioLeilao);
                        String hIni = String.valueOf(this.horaInicioLeilao);
                        String dFim = String.valueOf(this.dataFimLeilao);
                        String hFim = String.valueOf(this.horaFimLeilao);

                        String novaLinha = this.idLeilao + "," + dIni + "," + hIni + "," + dFim + "," + hFim + "," + this.statusLeilao;
                        bw.write(novaLinha);
                    } else {
                        bw.write(linha);
                    }
                    bw.newLine();
                }
            }
        }
    }

    public ArrayList<Leilao> listarLeiloes() throws Exception {
        ArrayList<Leilao> lista = new ArrayList<>();
        try (FileReader fr = new FileReader("leiloes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 6) continue;

                Leilao l = new Leilao(Integer.parseInt(dados[0]));
                l.dataInicioLeilao = dados[1].equals("null") ? null : Date.valueOf(dados[1]);
                l.horaInicioLeilao = dados[2].equals("null") ? null : Time.valueOf(dados[2]);
                l.dataFimLeilao    = dados[3].equals("null") ? null : Date.valueOf(dados[3]);
                l.horaFimLeilao    = dados[4].equals("null") ? null : Time.valueOf(dados[4]);
                l.statusLeilao     = Boolean.parseBoolean(dados[5]);
                lista.add(l);
            }
        }
        return lista;
    }

 //método mostrar
    public void mostrar() {
        System.out.println("=== Detalhes do Leilão ===");
        System.out.println("ID: " + this.idLeilao);
        System.out.println("Início: " + this.dataInicioLeilao + " às " + this.horaInicioLeilao);
        System.out.println("Fim:    " + this.dataFimLeilao + " às " + this.horaFimLeilao);
        if (this.statusLeilao) {
            System.out.println("Status: ABERTO (Recebendo lances)");
        } else {
            System.out.println("Status: FINALIZADO");
        }
    }
}