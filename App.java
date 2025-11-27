import java.util.Scanner;
import java.sql.Date;
import java.sql.Time;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("BEM-VINDO AO SISTEMA DE LEILÃO");

        while (opcao != 10) { // Agora o Sair é 10
            System.out.println("\n--------------------------------");
            System.out.println("        MENU PRINCIPAL");
            System.out.println("--------------------------------");
            System.out.println("1. Cadastrar Participante");
            System.out.println("2. Listar Participantes");
            System.out.println("3. Cadastrar Leilão");
            System.out.println("4. Listar Leilões");
            System.out.println("5. Finalizar Leilão");
            System.out.println("6. Cadastrar Item (Vincular a Leilão)");
            System.out.println("7. Listar Itens");
            System.out.println("8. Dar um Lance");
            System.out.println("9. Listar Lances");
            System.out.println("10. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                // 1. CADASTRAR PARTICIPANTE
                if (opcao == 1) {
                    System.out.println("\n--- Cadastro de Participante ---");
                    System.out.print("ID: "); int idP = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("Login: "); String login = scanner.nextLine();
                    System.out.print("Senha: "); String senha = scanner.nextLine();
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("Endereço: "); String endereco = scanner.nextLine();
                    System.out.print("Telefone: "); String fone = scanner.nextLine();

                    Participante p = new Participante(idP, nome, login, senha, email, endereco, fone);
                    if (p.registrarParticipante()) System.out.println(">> Sucesso!");

                // 2. LISTAR PARTICIPANTES
                } else if (opcao == 2) {
                    System.out.println("\n--- Lista de Participantes ---");
                    Participante pTemp = new Participante(0,"","","","","","");
                    if(new File("participantes.txt").exists()) {
                        for (Participante part : pTemp.listarParticipantes()) part.mostrar();
                    } else System.out.println("Nenhum participante cadastrado.");

                // 3. CADASTRAR LEILÃO
                } else if (opcao == 3) {
                    System.out.println("\n--- Cadastro de Leilão ---");
                    System.out.print("ID do Leilão: "); int idL = scanner.nextInt();
                    Leilao leilao = new Leilao(idL);
                    System.out.println("Iniciar agora? (1-Sim / 2-Não)");
                    if(scanner.nextInt() == 1) {
                        long millis = System.currentTimeMillis();
                        leilao.iniciarLeilao(new Date(millis), new Time(millis));
                    }
                    if (leilao.registrarLeilao()) System.out.println(">> Sucesso!");

                // 4. LISTAR LEILÕES (Texto do menu ajustado para Listar para manter coerência)
                } else if (opcao == 4) {
                    System.out.println("\n--- Lista de Leilões ---");
                    Leilao lTemp = new Leilao(0);
                    if(new File("leiloes.txt").exists()) {
                        for (Leilao lei : lTemp.listarLeiloes()) {
                            System.out.println("ID: " + lei.getIdLeilao() + " | Status: " + (lei.getStatusLeilao() ? "Aberto" : "Fechado"));
                        }
                    } else System.out.println("Nenhum leilão cadastrado.");

                // 5. FINALIZAR LEILÃO
                } else if (opcao == 5) {
                    System.out.println("\n--- Finalizar Leilão ---");
                    System.out.print("ID do Leilão: ");
                    int idFinalizar = scanner.nextInt();

                    Leilao leilao = new Leilao(idFinalizar);
                    if(new File("leiloes.txt").exists()) leilao = leilao.consultarLeilao();
                    else leilao = null;

                    if (leilao != null) {
                        if (leilao.getStatusLeilao()) {
                            long m = System.currentTimeMillis();
                            leilao.finalizarLeilao(new Date(m), new Time(m));
                            leilao.atualizarLeilaoNoArquivo(); 
                            System.out.println(">> Leilão finalizado com sucesso!");
                        } else System.out.println(">> Este leilão já está fechado.");
                    } else System.out.println(">> Leilão não encontrado.");

                // 6. CADASTRAR ITENS
                } else if (opcao == 6) {
                    System.out.println("\n--- Cadastro de Item ---");
                    System.out.print("ID do Item: "); int idItem = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Descrição: "); String desc = scanner.nextLine();
                    System.out.print("Lance Mínimo: "); Double min = scanner.nextDouble();
                    System.out.print("ID do Leilão: "); int idLeilaoVinculo = scanner.nextInt();

                    Leilao leilaoBusca = new Leilao(idLeilaoVinculo);
                    if(new File("leiloes.txt").exists()) leilaoBusca = leilaoBusca.consultarLeilao();
                    else leilaoBusca = null;

                    if (leilaoBusca != null) {
                        ItemLeilao novoItem = new ItemLeilao(idItem, desc, min, leilaoBusca);
                        if (novoItem.registrarItem()) System.out.println(">> Sucesso!");
                    } else System.out.println(">> Erro: Leilão não encontrado!");

                // 7. LISTAR ITENS
                } else if (opcao == 7) {
                    System.out.println("\n--- Lista de Itens ---");
                    ItemLeilao iTemp = new ItemLeilao(0, "", 0.0, null);
                    if(new File("itens.txt").exists()) {
                        for (ItemLeilao item : iTemp.listarItens()) item.mostrar();
                    } else System.out.println("Nenhum item cadastrado.");

                // 8. DAR UM LANCE
                } else if (opcao == 8) {
                    System.out.println("\n--- Registrar Lance ---");
                    System.out.print("ID do Lance: "); int idLance = scanner.nextInt();
                    System.out.print("ID do Participante: "); int idPart = scanner.nextInt();
                    System.out.print("ID do Item: "); int idItem = scanner.nextInt();
                    System.out.print("Valor: "); Double valor = scanner.nextDouble();

                    ItemLeilao itemBusca = new ItemLeilao(idItem, "", 0.0, null);
                    if(new File("itens.txt").exists()) itemBusca = itemBusca.consultarItem();
                    else itemBusca = null;

                    if (itemBusca != null) {
                        Participante part = new Participante(idPart, "", "", "", "", "", "");
                        long m = System.currentTimeMillis();
                        Lance l = new Lance(idLance, part, itemBusca, valor, new Date(m), new Time(m));
                        if (l.registrarLance()) System.out.println(">> Lance registrado!");
                    } else System.out.println(">> Erro: Item não encontrado.");

                // 9. LISTAR LANCES
                } else if (opcao == 9) {
                    System.out.println("\n--- Lista de Lances ---");
                    Lance lTemp = new Lance(0, null, null, 0.0, null, null);
                    if(new File("lances.txt").exists()) {
                        for (Lance l : lTemp.listarLances()) l.mostrar();
                    } else System.out.println("Nenhum lance cadastrado.");

                // 10. SAIR
                } else if (opcao == 10) {
                    System.out.println("Saindo...");

                } else {
                    System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}