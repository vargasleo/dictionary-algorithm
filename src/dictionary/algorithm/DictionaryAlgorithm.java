// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2021-1
// Leonardo Vargas Schilling

package dictionary.algorithm;

import dictionary.domain.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DictionaryAlgorithm {

    private final CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    private final String path = System.getProperty("user.dir") + File.separatorChar
            + "src" + File.separatorChar
            + "resources" + File.separatorChar;

    private static class CharNode {

        private Word content;
        private final char data;
        private final CharNode[] childList = new CharNode[26];

        private CharNode(char character) {
            this.data = character;
        }

        private CharNode getChild(char character) {
            return childList[character - 'a'];
        }

    }

    public DictionaryAlgorithm() {
        this.root = new CharNode('-');
    }

    public void readCsv(String filename) throws IOException {
        String[] data;
        String r;
        BufferedReader reader = Files.newBufferedReader(Paths.get(this.path + filename), StandardCharsets.UTF_8);
        final String UTF8_BOM = new String("\uFEFF".getBytes(StandardCharsets.UTF_8));
        while ((r = reader.readLine()) != null) {
            data = r.split(";");
            this.insert(new Word(data[0].toLowerCase().replaceFirst(UTF8_BOM,""), data[1]));
        }

        reader.close();
    }

    public void insert(Word word) {
        String content = word.getWord();
        CharNode node = root;
        int index;
        for (int i = 0; i < content.length(); i++) {
            index = content.charAt(i) - 'a';
            if (node.childList[index] == null) {
                node.childList[index] = new CharNode(content.charAt(i));
                totalNodes++;
            }
            node = node.childList[index];
        }
        node.content = word;
        totalWords++;
    }

    public String searchWordMeaning(String word) {
        return this.search(word).content.getMeaning();
    }

    public CharNode search(String word) {
        CharNode node = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (node.childList[index] == null) {
                return null;
            }
            node = node.childList[index];
        }
        return node;
    }

    public CharNode searchPrefix(String prefix) {
        return this.search(prefix);
    }

    public List<Word> findWordsByPrefix(String prefix) {
        CharNode node = this.searchPrefix(prefix);
        if (node != null) {
            List<Word> words = new ArrayList<>();
            this.addWordByPrefix(node, prefix, words);
            return words;
        }
        return Collections.emptyList();
    }

    private void addWordByPrefix(CharNode node, String word, List<Word> words) {
        if (node.content != null) {
            words.add(node.content);
        }

        for (int i = 0; i < node.childList.length; i++) {
            CharNode next = node.getChild((char) (i + 'a'));
            if (next != null) {
                addWordByPrefix(next, word + (char) (i + 'a'), words);
            }
        }
    }

}
