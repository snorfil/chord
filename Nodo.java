public class Nodo {

    /**
     * Clase Nodo
     * Esta clase representa un nodo en una red Chord.
     * Cada nodo tiene un índice y una letra aleatoria entre a-z.
     */
        // Índice del nodo
        private int indice;

        // Letra del nodo
        private char letra;

        // Vecinos del nodo
        private Nodo[] vecinos;

        // Tabla de rutas del nodo
        private Nodo[] tablaRutas;

        /**
         * Constructor de la clase Nodo
         *
         * @param indice Índice del nodo
         */
        public Nodo(int indice) {
            this.indice = indice;
        }

        /**
         * Constructor de la clase Nodo
         *
         * @param indice Índice del nodo
         * @param letra Letra del nodo
         */
        public Nodo(int indice, char letra) {
            this.indice = indice;
            this.letra = letra;
        }

        /**
         * Función para obtener el índice del nodo
         *
         * @return Índice del nodo
         */
        public int getIndice() {
            return indice;
        }

        /**
         * Función para obtener la letra del nodo
         *
         * @return Letra del nodo
         */
        public char getLetra() {
            return letra;
        }

        /**
         * Función para establecer los vecinos del nodo
         *
         * @param vecinos Array de los vecinos del nodo
         */
        public void establecerVecinos(Nodo[] vecinos) {
            this.vecinos = vecinos;
        }

        /**
         * Función para obtener los vecinos del nodo
         *
         * @return Array de los vecinos del nodo
         */
        public Nodo[] obtenerVecinos() {
            return vecinos;
        }

        /**
         * Función para establecer la tabla de rutas del nodo
         *
         * @param tablaRutas Array de la tabla de rutas del nodo
         */
        public void establecerTablaRutas(Nodo[] tablaRutas) {
            this.tablaRutas = tablaRutas;
        }

        /**
         * Función para obtener la tabla de rutas del nodo
         *
         * @return Array de la tabla de rutas del nodo
         */
        public Nodo[] obtenerTablaRutas() {
            return tablaRutas;
        }
}
