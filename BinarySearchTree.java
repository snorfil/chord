public class BinarySearchTree {


        /* Clase privada para representar un nodo */
        private class Node {
            int data;
            Node left;
            Node right;

            Node(int data) {
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }

        // Raíz del árbol
        private Node root;

        // Constructor
        BinarySearchTree() {
            root = null;
        }

        // Método para insertar un nodo en el árbol
        public void insert(int data) {
            root = insertRec(root, data);
        }

        /* Un método recursivo para insertar un nuevo valor
        en el árbol BST */
        private Node insertRec(Node root, int data) {

            /* Si el árbol está vacío, devuelve un nuevo nodo */
            if (root == null) {
                root = new Node(data);
                return root;
            }

            /* De lo contrario, recurre por el árbol */
            if (data < root.data)
                root.left = insertRec(root.left, data);
            else if (data > root.data)
                root.right = insertRec(root.right, data);

            /* Regresa la raíz del árbol */
            return root;
        }

        // Método para buscar un valor en el árbol
        public boolean search(int data) {
            return searchRec(root, data);
        }

        /* Un método recursivo para buscar un valor
        en el árbol BST */
        private boolean searchRec(Node root, int data) {

            // Caso base
            if (root == null)
                return false;

                // Si el valor buscado es menor que el valor
                // en el nodo actual, entonces sólo buscar en
                // el subárbol izquierdo
            else if (data < root.data)
                return searchRec(root.left, data);

                // Si el valor buscado es mayor que el valor
                // en el nodo actual, entonces sólo buscar en
                // el subárbol derecho
            else if (data > root.data)
                return searchRec(root.right, data);

                // Si el valor buscado es igual al valor
                // en el nodo actual, entonces regresa verdadero
            else
                return true;
        }
}
