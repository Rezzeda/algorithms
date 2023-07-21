public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // Добавление элементов в красно-черное дерево
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        // Проверка наличия элементов в дереве
        // System.out.println("Содержит 40? " + tree.contain(40)); // Ожидаемый результат: true
        // System.out.println("Содержит 55? " + tree.contain(55)); // Ожидаемый результат: false

        System.out.println("Дерево целиком:");
        tree.printTree();
    }
}


