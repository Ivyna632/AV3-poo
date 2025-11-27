import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ItemLeilao {
    
    private int idItem;
    private Leilao leilao;
    private String descricaoItem;
    private Double lanceMinimoItem;
    private Boolean itemArrematado;
    private Lance lanceArrematante;

    //construtor
    public ItemLeilao(int idItem, String descricaoItem, Double lanceMinimoItem, Leilao leilao) {
        this.idItem = idItem;
        this.descricaoItem = descricaoItem;
        this.lanceMinimoItem = lanceMinimoItem;
        this.leilao = leilao;
        this.itemArrematado = false; // Padrão inicial
        this.lanceArrematante = null; // Padrão inicial
    }

    //get/setters
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Double getLanceMinimoItem() {
        return lanceMinimoItem;
    }

    public void setLanceMinimoItem(Double lanceMinimoItem) {
        this.lanceMinimoItem = lanceMinimoItem;
    }

    public Boolean getItemArrematado() {
        return itemArrematado;
    }

    public void setItemArrematado(Boolean itemArrematado) {
        this.itemArrematado = itemArrematado;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }

    public Lance getLanceArrematante() {
        return lanceArrematante;
    }

    public void setLanceArrematante(Lance lanceArrematante) {
        this.lanceArrematante = lanceArrematante;
    }

    //métodos de registrar, consultar e listar itens

    public Boolean registrarItem() throws Exception {
        try (FileWriter fw = new FileWriter("itens.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            // Proteção para pegar ID do Leilão
            int idLeilaoSalvar = (this.leilao != null) ? this.leilao.getIdLeilao() : 0;

            // Proteção para pegar ID do Lance (se já foi arrematado)
            int idLanceSalvar = 0;
            if (this.lanceArrematante != null) {
                idLanceSalvar = this.lanceArrematante.getIdLance();
            }

            String linha = this.idItem + "," +
                           this.descricaoItem + "," + 
                           this.lanceMinimoItem + "," + 
                           this.itemArrematado + "," + 
                           idLeilaoSalvar + "," + 
                           idLanceSalvar;

            bw.write(linha);
            bw.newLine();
            return true;
        }
    }
    
    public void arrematarItem(Lance lance) {
        this.itemArrematado = true;
        this.lanceArrematante = lance;
    }

    public ItemLeilao consultarItem() throws Exception {
        // Implementação básica de consulta lendo o arquivo
        try (FileReader fr = new FileReader("itens.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 5) continue;

                int idLido = Integer.parseInt(dados[0]);

                if (idLido == this.idItem) {
                    // Reconstrói o Leilão usando o ID (usa o construtor da sua classe Leilao)
                    Leilao l = new Leilao(Integer.parseInt(dados[4]));

                    ItemLeilao item = new ItemLeilao(
                        idLido,
                        dados[1],
                        Double.parseDouble(dados[2]),
                        l
                    );
                    item.setItemArrematado(Boolean.parseBoolean(dados[3]));
                    return item;
                }
            }
        }
        return null;
    }

    public ArrayList<ItemLeilao> listarItens() throws Exception {
        ArrayList<ItemLeilao> lista = new ArrayList<>();
        
        try (FileReader fr = new FileReader("itens.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 5) continue;

                int id = Integer.parseInt(dados[0]);
                String desc = dados[1];
                Double min = Double.parseDouble(dados[2]);
                Boolean arrematado = Boolean.parseBoolean(dados[3]);
                int idLeilao = Integer.parseInt(dados[4]);

                // Instancia um Leilao dummy para o construtor
                Leilao leilaoDoItem = new Leilao(idLeilao);

                ItemLeilao item = new ItemLeilao(id, desc, min, leilaoDoItem);
                item.setItemArrematado(arrematado);

                // Se houver ID de lance (coluna 5), não instanciamos o Lance completo
                // para evitar loop infinito, mas a lógica do arquivo está preservada.

                lista.add(item);
            }
        }
        return lista;
    }

    //método mostrar
    public void mostrar() {
        System.out.println("=== Detalhes do Item ===");
        System.out.println("ID Item: " + this.idItem);
        System.out.println("Descrição: " + this.descricaoItem);
        System.out.println("Lance Mínimo: R$ " + this.lanceMinimoItem);
        System.out.println("Arrematado? " + (this.itemArrematado ? "Sim" : "Não"));
        if (this.leilao != null) {
            System.out.println("ID do Leilão: " + this.leilao.getIdLeilao());
        }
        System.out.println("========================");
    }
}