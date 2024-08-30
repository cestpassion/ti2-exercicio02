package app;

import dao.MusicDAO;
import model.Music;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        MusicDAO musicDao = new MusicDAO();
        musicDao.connect();
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n\n\n\n\n\n\n\n");

            System.out.println("\t\tMusic (not)Play List\n\n");
            System.out.println("Escolha uma opção:\n");
            System.out.println("1) Adicionar");
            System.out.println("2) Excluir");
            System.out.println("3) Alterar");
            System.out.println("4) Listar");
            System.out.println("5) Sair\n");
            System.out.print("Resposta: ");

            try {
                option = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                sc.nextLine(); // Limpa o buffer
                continue;
            }

            switch (option) {

                case 1:
                    try {
                    	System.out.println("\n\n\n\n\n\n\n\n");
                        System.out.println("\t\tCadastro de Música\n\n");

                        System.out.print("Nome da música: ");
                        String name = sc.nextLine();

                        System.out.print("Nome do artista: ");
                        String artist = sc.nextLine();

                        System.out.print("Gênero musical: ");
                        String genero = sc.nextLine();

                        Music newMusic = new Music(name, artist, genero);
                        musicDao.insertMusic(newMusic);

                        System.out.println("Música cadastrada com sucesso! Pressione Enter...");
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Verifique os dados fornecidos.");
                        sc.nextLine();
                    }
                    break;

                case 2:
                    try {
                    	System.out.println("\n\n\\n\n\n\n\n\n");
                        System.out.println("\t\tExclusão\n\n");
                        System.out.print("Digite o código da música a ser excluída: ");
                        int code = sc.nextInt();
                        sc.nextLine(); // Limpa o buffer

                        musicDao.deleteMusic(code);
                        System.out.println("\nMúsica excluída com sucesso!");
                    } catch (InputMismatchException e) {
                        System.out.println("\nEntrada inválida. Por favor, insira um número inteiro.");
                        sc.nextLine(); // Limpa o buffer
                    }
                    break;

                case 3:
                    try {
                    	System.out.println("\n\n\n\n\n\n\n\n");
                        System.out.println("\t\tAtualização\n\n");
                        System.out.print("Digite o código da música a ser atualizada: ");
                        int code = sc.nextInt();
                        sc.nextLine(); // Limpa o buffer

                        Music musicToUpdate = musicDao.getMusicByCode(code);
                        if (musicToUpdate == null) {
                            System.out.println("Música não encontrada.");
                            break;
                        }

                        System.out.println("Deseja fazer qual alteração?\n ");
                        System.out.println("A) Nome");
                        System.out.println("B) Artista");
                        System.out.println("C) Gênero");
                        System.out.println("D) Sair\n");
                        System.out.print("Resposta: ");
                        char optionABC = sc.nextLine().toUpperCase().charAt(0);
                        
                        switch (optionABC) {
                            case 'A':
                                System.out.print("Novo nome: ");
                                String newName = sc.nextLine();
                                musicToUpdate.setName(newName);
                                break;

                            case 'B':
                                System.out.print("Novo artista: ");
                                String newArtist = sc.nextLine();
                                musicToUpdate.setArtist(newArtist);
                                break;

                            case 'C':
                                System.out.print("Novo gênero: ");
                                String newGender = sc.nextLine();
                                musicToUpdate.setGender(newGender);
                                break;

                            case 'D':
                                continue;

                            default:
                                System.out.println("\nOpção inválida!");
                                sc.nextLine();
                                continue;
                        }

                        musicDao.updateMusic(musicToUpdate);
                        System.out.println("\nMúsica atualizada com sucesso!");

                    } catch (InputMismatchException e) {
                        System.out.println("\nEntrada inválida. Verifique os dados fornecidos.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                	System.out.println("\n\\n\n\n\n\n\n\n");
                    System.out.println("\t\tMusic (not)Play List\n\n");
                    
                    List<Music> musicList = musicDao.getMusic();
                    if (musicList == null || musicList.isEmpty()) {
                        System.out.println("Nenhuma música cadastrada.");
                    } else {
                        for (Music music : musicList) {
                            System.out.println(music);
                        }
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                    break;

                case 5:
                    System.out.println("\nFIM.");
                    return;

                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
                    break;
            }
        } while (option != 5);

        musicDao.close();
        sc.close();
    }
}
