// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2021-1
// Leonardo Vargas Schilling

package dictionary.menu;

import dictionary.algorithm.DictionaryAlgorithm;
import dictionary.domain.Word;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final DictionaryAlgorithm d = new DictionaryAlgorithm();
    public void run() {
        int opcao;
        do {
            System.out.println("SISTEMA DE BUSCA DE PALAVRAS EM DICIONÁRIO");
            System.out.println("\nESCOLHA DENTRE AS OPÇÕES");
            System.out.println("[1] BUSCAR POR PREFIXO");
            System.out.println("[2] LER ARQUIVO CSV");
            System.out.println("[3] SAIR");
            opcao = sc.nextInt();
            this.menu(opcao);
        } while (opcao != 3);
    }

    private void menu(int opcao) {
        switch (opcao) {
            case 1 -> this.findWordByPrefix();
            case 2 -> this.readCsv();
            case 3 -> System.out.println("\nSAINDO");
            default -> System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private void readCsv() {
        System.out.println("DIGITE O NOME DO ARQUIVO CSV SALVO EM RESOURCES: ");
        try {
            d.readCsv(sc.next());
        } catch (IOException e) {
            System.out.println("ERRO NA LEITURA, DICIONÁRIO NÃO COMPLETOU ATUALIZAÇÃO");
        }
    }

    private String definePrefix() {
        System.out.println("INSIRA O PREFIXO DESEJADO: ");
        return sc.next();
    }

    private void printAllWords(List<Word> wordList) {
        for (Word w: wordList) {
            System.out.println(w.getWord());
        }
    }

    private void findWordByPrefix() {
        List<Word> wordList = d.findWordsByPrefix(this.definePrefix());
        this.printAllWords(wordList);
        if(!wordList.isEmpty()) {
            System.out.println("DIGITE A PALAVRA ");
            String choosedWord = sc.next();
            System.out.println(d.searchWordMeaning(choosedWord));
        } else {
            System.out.println("NÃO HÁ PALAVRAS COM ESTE PREFIXO");
        }
    }

}
