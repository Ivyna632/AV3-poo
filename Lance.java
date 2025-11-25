import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;

public class Lance {
    private int idLance;
    private Participante participante;
    private ItemLeilao itemLeilao;
    private double valorLance;
    private LocalDate dataLance;
    private LocalTime horaLance;

    //get/setters
    public int getidLance() {
        return idLance;
    }
    public void setidLance(int idLance) {
        this.idLance = idLance;
    }
    public Participante getparticipante() {
        return participante;
    }
    public void setparticipante(Participante participante) {
        this.participante = participante;
    }
    public ItemLeilao getitemLeilao() {
        return itemLeilao;
    }
    public void setitemLeilao(ItemLeilao itemLeilao) {
        this.itemLeilao = itemLeilao;
    }
    public double getvalorLance() {
        return valorLance;
    }
    public void setvalorLance(double valorLance) {
        this.valorLance = valorLance;
    }
    public LocalDate getdataLance() {
        return dataLance;
    }
    public void setdataLance(LocalDate dataLance) {
        this.dataLance = dataLance;
    }
    public LocalTime gethoraLance() {
        return horaLance;
    }
    public void sethoraLance(LocalTime horaLance) {
        this.horaLance = horaLance;
    }

    // métodos de registro e listagem
    public Boolean registrarLance() {
        return this.registrarLance();
    }

    public ArrayList<Lance> listarLances() {
        return this.listarLances();
    }

    //metodo mostrar
    public void mostrar() {
        System.out.println("--- Lance ---");
        System.out.println("ID Lance: " + this.idLance);
        System.out.println("Valor: R$ " + this.valorLance);
        System.out.println("Data/Hora: " + this.dataLance + " às " + this.horaLance);
        
        if (this.participante != null) {
            System.out.println("Feito por: " + this.participante.getnomeParticipante());
        } else {
            System.out.println("Feito por: [Desconhecido]");
        }
    }
}
