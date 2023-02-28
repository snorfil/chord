import java.util.Arrays;

public class Nodo {

        // Índice del nodo en el algoritmo
        private int indice;

        // Letra identificadora del nodo
        private char letra;

        // Vecinos del nodo
        private Nodo[] vecinos;

        // Tabla de rutas del nodo
        private Nodo[] tablaRutas;

        /**
         * Constructor de la clase Nodo
         *
         * @param indice Índice del nodo en el algoritmo
         * @param letra Letra identificadora del nodo
         */
        public Nodo(int indice, char letra) {
            this.indice = indice;
            this.letra = letra;
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
         * Función para establecer la tabla de rutas del nodo
         *
         * @param tablaRutas Array de la tabla de rutas del nodo
         */
        public void establecerTablaRutas(Nodo[] tablaRutas) {
            this.tablaRutas = tablaRutas;
        }

        /**
         * Función para obtener el índice del nodo
         *
         * @return Índice del nodo en el algoritmo
         */
        public int getIndice() {
            return indice;
        }

        /**
         * Función para obtener la letra del nodo
         *
         * @return Letra identificadora del nodo
         */
        public char getLetra() {
            return letra;
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
         * Función para obtener la tabla de rutas del nodo
         *
         * @return Array de la tabla de rutas del nodo
         */
        public Nodo[] obtenerTablaRutas() {
            return tablaRutas;
        }

        /**
         * Función para agregar un nodo a la red
         *
         * @param nodo Nodo a agregar
         */
        public void agregarNodo(Nodo nodo) {
            // Añadir el nodo al algoritmo Chord
            Chord chord = new Chord(this.tablaRutas.length + 1);
            chord.añadirNodos(1);

            // Establecer los vecinos del nodo
            nodo.establecerVecinos(chord.calcularVecinos(nodo));

            // Establecer la tabla de rutas del nodo
            nodo.establecerTablaRutas(chord.calcularTablaRutas(nodo));

            // Establecer los vecinos y la tabla de rutas de los otros nodos
            for (int i = 0; i < this.tablaRutas.length; i++) {
                Nodo[] vecinos = chord.calcularVecinos(this.tablaRutas[i]);
                this.tablaRutas[i].establecerVecinos(vecinos);
                this.tablaRutas[i].establecerTablaRutas(chord.calcularTablaRutas(this.tablaRutas[i]));
            }

            // Añadir el nodo a la tabla de rutas
            Nodo[] nuevaTablaRutas = new Nodo[this.tablaRutas.length + 1];
            System.arraycopy(this.tablaRutas, 0, nuevaTablaRutas, 0, this.tablaRutas.length);
            nuevaTablaRutas[this.tablaRutas.length] = nodo;
            this.tablaRutas = nuevaTablaRutas;
        }

        /**
         * Función para eliminar un nodo de la red
         *
         * @param nodo Nodo a eliminar
         */
        public void eliminarNodo(Nodo nodo) {
            // Eliminar el nodo del algoritmo Chord
            Chord chord = new Chord(this.tablaRutas.length - 1);
            chord.eliminarNodos(1);

            // Establecer los vecinos de los otros nodos
            for (int i = 0; i < this.tablaRutas.length; i++) {
                if (this.tablaRutas[i] == nodo) {
                    continue;
                }
                Nodo[] vecinos = chord.calcularVecinos(this.tablaRutas[i]);
                this.tablaRutas[i].establecerVecinos(vecinos);
            }

            // Establecer la tabla de rutas de los otros nodos
            for (int i = 0; i < this.tablaRutas.length; i++) {
                if (this.tablaRutas[i] == nodo) {
                    continue;
                }
                this.tablaRutas[i].establecerTablaRutas(chord.calcularTablaRutas(this.tablaRutas[i]));
            }

            // Eliminar el nodo de la tabla de rutas
            Nodo[] nuevaTablaRutas = new Nodo[this.tablaRutas.length - 1];
            int indice = 0;
            for (int i = 0; i < this.tablaRutas.length; i++) {
                if (this.tablaRutas[i] == nodo) {
                    continue;
                }
                nuevaTablaRutas[indice++] = this.tablaRutas[i];
            }
            this.tablaRutas = nuevaTablaRutas;
        }

        /**
         * Función para consultar cuántos nodos tienen una letra determinada
         *
         * @param letra Letra que se quiere comprobar
         * @return Número de nodos que tienen la letra
         */
        public int consultarLetra(char letra) {
            int contador = 0;
            for (int i = 0; i < this.tablaRutas.length; i++) {
                if (this.tablaRutas[i].getLetra() == letra) {
                    contador++;
                }
            }
            return contador;
        }

        @Override
        public String toString() {
            return "Nodo{" +
                    "indice=" + indice +
                    ", letra=" + letra +
                    ", vecinos=" + Arrays.toString(vecinos) +
                    ", tablaRutas=" + Arrays.toString(tablaRutas) +
                    '}';
        }
}
