import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;


public class Leilao {
    private int idLeilao;
    private LocalDate dataInicioLeilao;
    private LocalTime horaInicioLeilao;
    private LocalDate dataFimLeilao;
    private LocalTime horaFimLeilao;
    private Boolean statusLeilao;

    //get/setters
    public int getidLeilao() {
    return idLeilao;
    }
    public void setidLeilao( int idLeilao) {
        this.idLeilao = idLeilao;
    }
    public LocalDate getdataInicioLeilao() {
        return dataInicioLeilao;
    }
    public void setdataInicioLeilao(LocalDate dataInicioLeilao) {
        this.dataInicioLeilao = dataInicioLeilao;
    }
    public LocalDate getdataFimLeilao() {
        return dataFimLeilao;
    }
    public void setdataFimLeilao(LocalDate dataFimLeilao) {
        this.dataFimLeilao = dataFimLeilao;
    }
    public LocalTime gethoraInicioLeilao() {
        return horaInicioLeilao;
    }
    public void sethoraInicioLeilao(LocalTime horaInicioLeilao) {
        this.horaInicioLeilao = horaInicioLeilao;
    } 
    public LocalTime gethoraFimLeilao() {
        return horaFimLeilao;
    }
    public void sethoraFimLeilao ( LocalTime horaFimLeilao) {
        this.horaFimLeilao = horaFimLeilao;
    }

    // métodos
    public Boolean getstatusLeilao() {
        return statusLeilao;
    }
    public void setstatusLeilao( Boolean statusLeilao) {
        this.statusLeilao = statusLeilao;
    }

    //métodos 
    public Leilao consultarLeilao() {
        return this.consultarLeilao();
    }

    public Boolean iniciarLeilao() {
        return this.iniciarLeilao();
    }

    public Boolean registrarLeilao() {
        return this.registrarLeilao();
    }

    public Boolean finalizarLeilao() {
        return this.finalizarLeilao();
    }

    public ArrayList<Leilao> listarLeiloes() {
        return this.listarLeiloes();
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