// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2021-1
// Leonardo Vargas Schilling

package dictionary.domain;

public class Word {
    private final String word;
    private final String meaning;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    @Override
    public String toString() {
        return word + ": " + meaning + "\n";
    }

}
