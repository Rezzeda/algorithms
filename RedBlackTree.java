public class RedBlackTree {
    Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            root.color = Color.black;
            return true;
        }
        Node newNode = addNode(root, value);
        if (newNode == null)
            return false;
        balanceAfterInsertion(newNode);
        return true;
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String indent, boolean isRightChild) {
        if (node != null) {
            String childPrefix = isRightChild ? "└── " : "├── ";
            printTree(node.right, indent + (isRightChild ? "    " : "│   "), true);
            System.out.println(indent + childPrefix + node.value + " (" + node.color + ")");
            printTree(node.left, indent + (isRightChild ? "│   " : "    "), false);
        }
    }

    private Node addNode(Node node, int value) {
        if (node.value == value)
            return null;
        if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value);
                node.left.parent = node; // Устанавливаем ссылку на родительский узел
                return node.left;
            } else
                return addNode(node.left, value);
        } else {
            if (node.right == null) {
                node.right = new Node(value);
                node.right.parent = node; // Устанавливаем ссылку на родительский узел
                return node.right;
            } else
                return addNode(node.right, value);
        }
    }

    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    private void balanceAfterInsertion(Node node) {
        node.color = Color.red;
        while (node != root && node.parent.color == Color.red) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (uncle != null && uncle.color == Color.red) {
                    node.parent.color = Color.black;
                    uncle.color = Color.black;
                    node.parent.parent.color = Color.red;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = Color.black;
                    node.parent.parent.color = Color.red;
                    rotateRight(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (uncle != null && uncle.color == Color.red) {
                    node.parent.color = Color.black;
                    uncle.color = Color.black;
                    node.parent.parent.color = Color.red;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = Color.black;
                    node.parent.parent.color = Color.red;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.color = Color.black;
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    private class Node {
        int value;
        Node left;
        Node right;
        Node parent; // Добавляем ссылку на родительский узел
        Color color;

        Node() {
            this.color = Color.red;
        }

        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }
    }

    enum Color {red, black}
}
