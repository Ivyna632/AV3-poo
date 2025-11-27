import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Lance {

    private int idLance;
    private Participante participante;
    private ItemLeilao itemLeilao;
    private Double valorLance;
    private Date dataLance;
    private Time horaLance;

    //construtor
    public Lance(int idLance, Participante participante, ItemLeilao itemLeilao, Double valorLance, Date dataLance, Time horaLance) {
        this.idLance = idLance;
        this.participante = participante;
        this.itemLeilao = itemLeilao;
        this.valorLance = valorLance;
        this.dataLance = dataLance;
        this.horaLance = horaLance;
    }

    //get/setters
     public int getIdLance() {
        return idLance;
    }

    public void setIdLance(int idLance) {
        this.idLance = idLance;
    }

    public Double getValorLance() {
        return valorLance;
    }

    public void setValorLance(Double valorLance) {
        this.valorLance = valorLance;
    }

    public Date getDataLance() {
        return dataLance;
    }

    public void setDataLance(Date dataLance) {
        this.dataLance = dataLance;
    }

    public Time getHoraLance() {
        return horaLance;
    }

    public void setHoraLance(Time horaLance) {
        this.horaLance = horaLance;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public ItemLeilao getItemLeilao() {
        return itemLeilao;
    }

    public void setItemLeilao(ItemLeilao itemLeilao) {
        this.itemLeilao = itemLeilao;
    }

    //métodos de registrar e listar lances

    public Boolean registrarLance() throws Exception {
        try (FileWriter fw = new FileWriter("lances.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            // Pega os IDs com segurança (verifica nulos)
            // Nota: getidParticipante está com 'i' minúsculo para bater com sua classe Participante
            int idPart = (this.participante != null) ? this.participante.getidParticipante() : 0;
            int idItem = (this.itemLeilao != null) ? this.itemLeilao.getIdItem() : 0;

            String linha = this.idLance + "," +
                           idPart + "," + 
                           idItem + "," +
                           this.valorLance + "," +
                           this.dataLance + "," +
                           this.horaLance;

            bw.write(linha);
            bw.newLine();
        }

        // Atualiza o item para dizer que foi arrematado por este lance
        if (this.itemLeilao != null) {
            this.itemLeilao.arrematarItem(this);
        }
        
        return true;
    }

    public ArrayList<Lance> listarLances() throws Exception {
        ArrayList<Lance> lista = new ArrayList<>();
        
        try (FileReader fr = new FileReader("lances.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 6) continue;

                int idL = Integer.parseInt(dados[0]);
                int idPart = Integer.parseInt(dados[1]);
                int idItem = Integer.parseInt(dados[2]);
                Double valor = Double.parseDouble(dados[3]);
                Date dataArq = Date.valueOf(dados[4]);
                Time horaArq = Time.valueOf(dados[5]);

                // CRIAÇÃO DOS OBJETOS DUMMY (Só com ID para não dar erro)
                
                // 1. Participante: O construtor pede 7 strings. Passamos vazias.
                Participante p = new Participante(idPart, "", "", "", "", "", "");

                // 2. ItemLeilao: O construtor pede (int, String, Double, Leilao). Passamos dummy.
                ItemLeilao i = new ItemLeilao(idItem, "Carregado do Arquivo", 0.0, null);

                Lance lance = new Lance(idL, p, i, valor, dataArq, horaArq);
                lista.add(lance);
            }
        }
        return lista;
    }

    //método mostrar
    public void mostrar() {
        System.out.println("=== Detalhes do Lance ===");
        System.out.println("ID Lance: " + this.idLance);
        System.out.println("Valor: R$ " + this.valorLance);
        System.out.println("Data: " + this.dataLance + " às " + this.horaLance);
        if (this.participante != null) {
            System.out.println("Participante ID: " + this.participante.getidParticipante());
        }
        if (this.itemLeilao != null) {
            System.out.println("Item ID: " + this.itemLeilao.getIdItem());
        }
        System.out.println("=========================");
    }
}