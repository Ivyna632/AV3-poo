import java.util.ArrayList;

public class ItemLeilao {
    private int idItem;
    private Leilao leilao;
    private String descricaoItem;
    private Double lanceMinimoItem;
    private Boolean itemArrematado;
    private Lance lanceArrematante;
    //private ArrayList<Lance> historicoLances = new ArrayList<>(); (??)

    //regras 
    //“Registrado” – Quando for inserido e o início ainda não for registrado;
    //“Agendado” – Quando for chamado o método iniciarLeilao e for inserido os seguintes atributos: dataInicioLeilao, horaInicioLeilao.
    //“Finalizado” – Quando for chamado o método finalizarLeilao e for inserido os seguintes atributos: dataFimLeilao, horaFimLeilao.
   
   
    //get/setters  
    public int getidItem() {
        return idItem;
    }
    public void setidItem(int idItem) {
        this.idItem = idItem;
    }
    public Leilao getleilao() {
        return leilao;
    }
    public void setleilao(Leilao leilao) {
        this.leilao = leilao;
    }
    public String getdescricaoItem() {
        return descricaoItem;
    }
    public void setdescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }
    public Double getlanceMinimoItem() {
        return lanceMinimoItem;
    }
    public void setlanceMinimoItem(Double lanceMinimoItem) {
        this.lanceMinimoItem = lanceMinimoItem;
    }
    public Boolean getitemArrematado() {
        return itemArrematado;
    }
    public void setitemArrematado(Boolean itemArrematado) {
        this.itemArrematado = itemArrematado;
    }
    public Lance getlanceArrematante() {
        return lanceArrematante;
    }
    public void setlanceArrematante(Lance lanceArrematante) {
        this.lanceArrematante = lanceArrematante;
    }

    //métodos de registro, consulta, arremate e listagem
    public Boolean registrarItem() {
        return true;
    }
    public ItemLeilao consultarItem() {
        return this;
    }
    public void arrematarItem(Lance lance) {
        this.itemArrematado = true;
        System.out.println("Item arrematado com sucesso!");
    }

    public ArrayList<ItemLeilao> listarItens() {
        return this.listarItens();
    }

    //metodo mostrar
    public void mostrar() {
        System.out.println("=== Detalhes do Item de Leilão ===");
        System.out.println("ID Item: " + this.idItem);
        System.out.println("Descrição Item: " + this.descricaoItem);
        System.out.println("Lance Mínimo Item: " + this.lanceMinimoItem);
        System.out.println("Item Arrematado: " + (this.itemArrematado ? "Sim" : "Não"));
        
        if (this.lanceArrematante != null) {
            System.out.println("Lance Arrematante: " + this.lanceArrematante.getvalorLance());
        } else {
            System.out.println("Lance Arrematante: Nenhum");
        }
    }
}
