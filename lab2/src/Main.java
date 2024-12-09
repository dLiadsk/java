public class Main {
    public static void main(String[] args) {
        try {
            String inputText = "Сьогодні чудовий день, щоб почати новий проект. Програмування — це мистецтво, яке поєднує логіку та креативність. Не бійся помилок, адже вони — найкращі вчителі. Кожна написана стрічка коду наближає тебе до досягнення мети!";
            System.out.println("Вхідний текст:");
            System.out.println(inputText);
            char startChar = 'т';
            char endChar = 'е';

            // Обробка тексту
            String result = processText(inputText, startChar, endChar);

            // Виведення результату
            System.out.println("Результат обробки тексту:");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }

    /**
     * Метод обробки тексту: видаляє підрядок найбільшої довжини, що починається та
     * закінчується заданими літерами
     *
     * @param text      вхідний текст
     * @param startChar початкова літера
     * @param endChar   кінцева літера
     * @return текст після обробки
     */
    public static String processText(String text, char startChar, char endChar) {
        String[] sentences = text.split("(?<=\\.|!|\\?)"); // Розбиваємо текст на речення
        StringBuilder result = new StringBuilder(); // Для зберігання кінцевого результату

        for (String sentence : sentences) {
            char[] sentenceMas = sentence.toCharArray();
            int startIdx = -1, endIdx = -1;

            // Знаходимо індекси початкової та кінцевої літери
            for (int i = 0; i < sentenceMas.length; i++) {
                if (sentenceMas[i] == startChar && startIdx == -1) {
                    startIdx = i;
                }
                if (sentenceMas[i] == endChar) {
                    endIdx = i;
                }
            }

            // Якщо знайдено підрядок для видалення
            StringBuilder sentenceResult = new StringBuilder();
            if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
                // Додаємо частини рядка до і після видаленого підрядка
                for (int i = 0; i < sentenceMas.length; i++) {
                    if (i < startIdx || i > endIdx) {
                        sentenceResult.append(sentenceMas[i]);
                    }
                }
            } else {
                // Якщо підрядок не знайдено, залишаємо речення без змін
                sentenceResult.append(sentence);
            }
            result.append(sentenceResult).append(" ");
        }

        return result.toString().trim(); // Повертаємо оброблений текст
    }
}
