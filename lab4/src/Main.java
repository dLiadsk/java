import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            String inputText = "Сьогодні чудовий день, щоб почати новий проект. Програмування — це мистецтво, яке поєднує логіку та креативність. Не бійся помилок, адже вони — найкращі вчителі. Кожна написана стрічка коду наближає тебе до досягнення мети!";
            System.out.println("Вхідний текст:");
            System.out.println(inputText);
            char startChar = 'п';
            char endChar = 'ь';

            // Create an instance of TextProcessor and execute the task
            TextProcessor processor = new TextProcessor();
            String result = processor.process(inputText, startChar, endChar);

            System.out.println("\nРезультат обробки тексту:");
            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }
}

/**
 * Class representing a single letter.
 */
class Letter {
    private final char character;

    public Letter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}

/**
 * Class representing a word composed of letters.
 */
class Word {
    private final Letter[] letters;

    public Word(String word) {
        this.letters = word.chars().mapToObj(c -> new Letter((char) c)).toArray(Letter[]::new);
    }

    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for (Letter letter : letters) {
            word.append(letter.getCharacter());
        }
        return word.toString();
    }
}
/**
 * Class representing a punctuation mark composed of letters.
 */
class Punctuation{
    private final char aChar;

    Punctuation(char aChar) {
        this.aChar = aChar;
    }

    public char getaChar() {
        return aChar;
    }

    @Override
    public String toString() {
        return "" + aChar;
    }
}

/**
 * Class representing a sentence composed of words and punctuation marks.
 */
class Sentence {
    private final Word[] words;
    private final Punctuation punctuation;

    public Sentence(String sentence) {
        String trimmedSentence = sentence.trim();
        int lastCharIdx = trimmedSentence.length() - 1;

        if (".!?".contains(String.valueOf(trimmedSentence.charAt(lastCharIdx)))) {
            this.punctuation = new Punctuation(trimmedSentence.charAt(lastCharIdx));
            trimmedSentence = trimmedSentence.substring(0, lastCharIdx);
        } else {
            this.punctuation = new Punctuation(Character.MIN_VALUE);
        }

        this.words = Arrays.stream(trimmedSentence.split("\\s+"))
                .map(Word::new)
                .toArray(Word[]::new);
    }

    public String removeSubstring(char startChar, char endChar) {
        String sentenceText = this.toString();
        int startIdx = sentenceText.indexOf(startChar);
        int endIdx = sentenceText.lastIndexOf(endChar);

        if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
            sentenceText = sentenceText.substring(0, startIdx) + sentenceText.substring(endIdx + 1);
        }
        return sentenceText;
    }

    @Override
    public String toString() {
        StringBuilder sentence = new StringBuilder();
        for (Word word : words) {
            sentence.append(word.toString()).append(" ");
        }
        return sentence.toString().trim() + punctuation;
    }
}

/**
 * Class representing a text composed of sentences.
 */
class Text {
    private final Sentence[] sentences;

    public Text(String text) {
        String normalizedText = text.replaceAll("[\t\n]+", " ").replaceAll("\\s+", " ").trim();
        this.sentences = Arrays.stream(normalizedText.split("(?<=[.!?])\\s+"))
                .map(Sentence::new)
                .toArray(Sentence[]::new);
    }

    public String processText(char startChar, char endChar) {
        StringBuilder result = new StringBuilder();
        for (Sentence sentence : sentences) {
            result.append(sentence.removeSubstring(startChar, endChar)).append(" ");
        }
        return result.toString().trim();
    }
}

/**
 * Class responsible for processing text using the defined classes.
 */
class TextProcessor {
    public String process(String inputText, char startChar, char endChar) {
        Text text = new Text(inputText);
        return text.processText(startChar, endChar);
    }
}
