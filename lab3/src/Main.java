import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Створення масиву об'єктів BuildingBlock
        BuildingBlock[] blocks = {
                new BuildingBlock("Stone", "Gray", 5, 8, 2.5),
                new BuildingBlock("Wood", "Brown", 2, 6, 0.6),
                new BuildingBlock("Iron", "Silver", 4, 6, 7.8),
                new BuildingBlock("Brick", "Red", 4, 6, 2.0),
                new BuildingBlock("Sandstone", "Yellow", 3, 5.5, 1.9),
                new BuildingBlock("Gold", "Gold", 15, 3.5, 19.3),
                new BuildingBlock("Glass", "Transparent", 1, 2, 2.6),
                new BuildingBlock("Clay", "Gray", 3.5, 6, 1.8),
                new BuildingBlock("Obsidian", "Black", 12, 20, 5.1),
                new BuildingBlock("Concrete", "White", 8, 9.0, 2.4),
                new BuildingBlock("Dirt", "Brown", 1.5, 1.5, 1.2),
                new BuildingBlock("Marble", "White", 9, 7, 2.7)
        };

        // Сортування масиву
        Arrays.sort(blocks);

        // Виведення відсортованого масиву
        System.out.println("Sorted Blocks:");
        for (BuildingBlock block : blocks) {
            System.out.println(block);
        }

        // Пошук ідентичного об'єкта
        BuildingBlock searchBlock = new BuildingBlock("Clay", "Gray", 3.5, 6.0, 1.8);
        int index = Arrays.binarySearch(blocks, searchBlock);

        if (index >= 0) {
            System.out.println("\nFound block: " + blocks[index]);
        } else {
            System.out.println("\nBlock not found.");
        }
    }

    /**
     * Клас BuildingBlock представляє будівельний блок.
     */
    static class BuildingBlock implements Comparable<BuildingBlock> {
        private String material;  // Матеріал блоку
        private String color;     // Колір блоку
        private double weight;    // Вага блоку
        private double durability; // Міцність блоку
        private double density;   // Густина блоку

        // Конструктор
        public BuildingBlock(String material, String color, double weight, double durability, double density) {
            this.material = material;
            this.color = color;
            this.weight = weight;
            this.durability = durability;
            this.density = density;
        }

        // Метод порівняння об'єктів для сортування
        @Override
        public int compareTo(BuildingBlock other) {
            // Порівнюємо за міцністю (durability) за зростанням
            if (this.durability != other.durability) {
                return Double.compare(this.durability, other.durability);
            }
            // Якщо міцність однакова, порівнюємо за вагою (weight) за спаданням
            if (this.weight != other.weight) return Double.compare(other.weight, this.weight);
            // Якщо вага однакова, порівнюємо за густиною (density) за зростанням
            return Double.compare(this.density, other.density);
        }

        @Override
        public String toString() {
            return String.format("BuildingBlock{material='%s', color='%s', weight=%.2f, durability=%.2f, density=%.2f}",
                    material, color, weight, durability, density);
        }
    }

}