/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesports.classes;

import java.util.Scanner;

/**
 *
 * @author nkrumah
 */
public class Campeonato {

    public Piloto pilotos[];
    public String nome;
    public Integer ano;

    public static Campeonato[] criarCampeonatos() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Digite a quantidade de campionatos: ");
        int qtd_campeonatos = ler.nextInt();

        while (qtd_campeonatos < 1) {
            System.out.print("A quantidade de campeonatos é invalida.\nDigite uma nova quantidade: ");
            qtd_campeonatos = ler.nextInt();
        }

        ler.nextLine();

        Campeonato campeonatos[] = new Campeonato[qtd_campeonatos];

        for (int i = 0; i < qtd_campeonatos; i++) {
            Campeonato campeonato = new Campeonato();

            System.out.print("Digite o nome do " + (i + 1) + "º campeonato: ");
            campeonato.nome = ler.nextLine();

            while (campeonato.nome.isEmpty() || campeonato.nome.length() > 50) {
                System.out.print("O nome digitado é invalido.\nDigite um nome entre 1 e 50 caracteres: ");
                campeonato.nome = ler.nextLine();
            }

            System.out.print("Digite o ano do " + (i + 1) + "º campeonato: ");
            campeonato.ano = ler.nextInt();

            while (campeonato.ano < 1990 || campeonato.ano > 2050) {
                System.out.print("O ano digitado é invalido.\nDigite um ano entre 1990 e 2050 : ");
                campeonato.ano = ler.nextInt();
            }

            ler.nextLine();

            System.out.print("Digite o numero de pilotos: ");
            int qtd_pilotos = ler.nextInt();

            ler.nextLine();

            campeonato.pilotos = new Piloto[qtd_pilotos];

            for (int j = 0; j < qtd_pilotos; j++) {
                Piloto piloto = new Piloto();

                System.out.print("Digite o nome do " + (j + 1) + "º piloto: ");
                piloto.nome = ler.nextLine();
                while (piloto.nome.isEmpty() || piloto.nome.length() > 50) {
                    System.out.println("O nome digitado é invalido.");
                    System.out.print("Digite o nome do " + (j + 1) + "º piloto: ");
                    piloto.nome = ler.nextLine();
                }

                System.out.print("Digite a equipa do " + (j + 1) + "º piloto: ");
                piloto.equipa = ler.nextLine();

                while (piloto.equipa.isEmpty() || piloto.equipa.length() > 50) {
                    System.out.println("A equipa digitada é invalida.");
                    System.out.print("Digite a equipa do " + (j + 1) + "º piloto: ");
                    piloto.equipa = ler.nextLine();
                }

                //Verificar existencia do piloto
                while (Campeonato.validarPiloto(campeonato.pilotos, piloto, j)) {
                    System.out.println("O piloto escolhido já foi inserido.");
                    System.out.print("Digite o nome do " + (j + 1) + "º piloto: ");
                    piloto.nome = ler.nextLine();

                    System.out.print("Digite a equipa do " + (j + 1) + "º piloto: ");
                    piloto.equipa = ler.nextLine();
                }

                campeonato.pilotos[j] = piloto;
            }

            campeonatos[i] = campeonato;
        }
        return campeonatos;

    }

    public static boolean validarPiloto(Piloto pilotos[], Piloto piloto, int pos) {

        for (int i = 0; i < pos; i++) {
            if (pilotos[i].nome.equalsIgnoreCase(piloto.nome) && pilotos[i].equipa.equalsIgnoreCase(piloto.equipa)) {
                return true;
            }
        }

        return false;
    }

}
