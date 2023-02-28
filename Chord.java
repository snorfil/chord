public class Chord  {
    // Número total de nodos en la red
        private int numNodos;

        // Array de los nodos
        private Nodo[] nodos;

        /**
         * Constructor de la clase Chord
         *
         * @param numNodos Número de nodos en la red
         */
        public Chord(int numNodos) {
            this.numNodos = numNodos;
            this.nodos = new Nodo[numNodos];

            // Inicializar los nodos
            for (int i = 0; i < numNodos; i++) {
                // Generar una letra aleatoria entre a-z
                char letra = (char)('a' + Math.random() * ('z' - 'a' + 1));
                this.nodos[i] = new Nodo(i, letra);
            }
        }

        /**
         * Función para inicializar el algoritmo de Chord
         */
        public void iniciar() {
            // Establecer los vecinos iniciales
            for (int i = 0; i < numNodos; i++) {
                nodos[i].establecerVecinos(calcularVecinos(nodos[i]));
            }

            // Establecer la tabla de rutas de cada nodo
            for (int i = 0; i < numNodos; i++) {
                nodos[i].establecerTablaRutas(calcularTablaRutas(nodos[i]));
            }
        }

        /**
         * Función para calcular los vecinos de cada nodo
         *
         * @param nodo Nodo para el que se quieren calcular los vecinos
         * @return Array de los vecinos del nodo
         */
        private Nodo[] calcularVecinos(Nodo nodo) {
            Nodo[] vecinos = new Nodo[2];
            int indiceInicial = nodo.getIndice();
            int indiceFinal = (indiceInicial + 1) % numNodos;
            vecinos[0] = nodos[indiceInicial];
            vecinos[1] = nodos[indiceFinal];
            return vecinos;
        }

        /**
         * Función para calcular la tabla de rutas de cada nodo
         *
         * @param nodo Nodo para el que se quiere calcular la tabla de rutas
         * @return Array de la tabla de rutas del nodo
         */
        private Nodo[] calcularTablaRutas(Nodo nodo) {
            Nodo[] tablaRutas = new Nodo[numNodos - 1];
            int indiceInicial = nodo.getIndice();
            int indice = 0;
            for (int i = 0; i < numNodos; i++) {
                if (i == indiceInicial) {
                    continue;
                }
                tablaRutas[indice++] = nodos[i];
            }
            return tablaRutas;
        }

        /**
         * Función para consultar cuántos nodos tienen una letra determinada
         *
         * @param letra Letra que se quiere comprobar
         * @return Número de nodos que tienen la letra
         */
        public int consultarLetra(char letra) {
            int contador = 0;
            for (int i = 0; i < numNodos; i++) {
                if (nodos[i].getLetra() == letra) {
                    contador++;
                }
            }
            return contador;
        }

        /**
         * Función para añadir nodos al algoritmo de Chord
         *
         * @param numNodos Número de nodos a añadir
         */
        public void añadirNodos(int numNodos) {
            // Aumentar el número de nodos en la red
            this.numNodos += numNodos;
            Nodo[] nodosNuevos = new Nodo[numNodos];

            // Inicializar los nodos nuevos
            for (int i = 0; i < numNodos; i++) {
                // Generar una letra aleatoria entre a-z
                char letra = (char)('a' + Math.random() * ('z' - 'a' + 1));
                nodosNuevos[i] = new Nodo(i + this.numNodos - numNodos, letra);
            }

            // Combina los nuevos nodos con los nodos ya existentes
            Nodo[] nodosTotales = new Nodo[this.numNodos];
            System.arraycopy(this.nodos, 0, nodosTotales, 0, this.numNodos - numNodos);
            System.arraycopy(nodosNuevos, 0, nodosTotales, this.numNodos - numNodos, numNodos);
            this.nodos = nodosTotales;

            // Establecer los vecinos iniciales
            for (int i = 0; i < numNodos; i++) {
                nodos[i].establecerVecinos(calcularVecinos(nodos[i]));
            }

            // Establecer la tabla de rutas de cada nodo
            for (int i = 0; i < numNodos; i++) {
                nodos[i].establecerTablaRutas(calcularTablaRutas(nodos[i]));
            }
        }

        /**
         * Función para eliminar nodos del algoritmo de Chord
         *
         * @param numNodos Número de nodos a eliminar
         */
        public void eliminarNodos(int numNodos) {
            if (numNodos > this.numNodos) {
                return;
            }

            // Disminuir el número de nodos en la red
            this.numNodos -= numNodos;
            Nodo[] nodosEliminados = new Nodo[numNodos];

            // Eliminar los nodos
            System.arraycopy(this.nodos, 0, nodosEliminados, 0, numNodos);
            Nodo[] nodosRestantes = new Nodo[this.numNodos];
            System.arraycopy(this.nodos, numNodos, nodosRestantes, 0, this.numNodos);
            this.nodos = nodosRestantes;

            // Establecer los vecinos iniciales
            for (int i = 0; i < this.numNodos; i++) {
                nodos[i].establecerVecinos(calcularVecinos(nodos[i]));
            }

            // Establecer la tabla de rutas de cada nodo
            for (int i = 0; i < this.numNodos; i++) {
                nodos[i].establecerTablaRutas(calcularTablaRutas(nodos[i]));
            }
        }
}