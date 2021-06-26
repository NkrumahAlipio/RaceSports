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
public class Piloto {

    public String nome;
    public String equipa;
    public int pontuacao = 0;

    public static Piloto criarPiloto(int j) {
        Scanner ler = new Scanner(System.in);
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

        return piloto;
    }

}
