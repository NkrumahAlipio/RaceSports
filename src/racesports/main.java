/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import racesports.classes.Campeonato;

/**
 *
 * @author nkrumah
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Campeonato campeonatos[] = Campeonato.criarCampeonatos();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        File ficheiro = new File(dtf.format(now) + ".txt");
        ficheiro.createNewFile();

        FileWriter escrever = new FileWriter(ficheiro);

        for (Campeonato campeonato : campeonatos) {
            escrever.append(campeonato.nome + ", " + campeonato.ano + "\n");
            System.out.println(campeonato.nome + ", " + campeonato.ano);
            escrever.append("\nClassificação após " + campeonato.provas.length + " provas realizadas\n");
            System.out.println("\nClassificação após " + campeonato.provas.length + " provas realizadas");
            escrever.append("\nPilotos\n");
            System.out.println("\nPilotos\n");

            for (int i = 0; i < campeonato.pilotos.length; i++) {
                escrever.append((i + 1) + ". " + campeonato.pilotos[i].nome + ", " + campeonato.pilotos[i].equipa + ", " + campeonato.pilotos[i].pontuacao + " pontos\n");
                System.out.println((i + 1) + ". " + campeonato.pilotos[i].nome + ", " + campeonato.pilotos[i].equipa + ", " + campeonato.pilotos[i].pontuacao + " pontos");
            }

            escrever.append("\nEquipas\n\n");
            System.out.println("\nEquipas\n");

            for (int i = 0; i < campeonato.equipas.size(); i++) {
                escrever.append((i + 1) + ". " + campeonato.equipas.get(i).nome + ", " + campeonato.equipas.get(i).pontos + "\n");
                System.out.println((i + 1) + ". " + campeonato.equipas.get(i).nome + ", " + campeonato.equipas.get(i).pontos);
            }
            System.out.println("");
        }

        escrever.close();

    }

}
