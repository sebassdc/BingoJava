import java.util.Arrays;
public class Bingo {
    public static void main(String[] args){
        String[] card = {"68 32 6 36 83",
                        "36 10 5 29 38",
                        "40 59 53 57 52",
                        "42 15 18 46 32",
                         "50 45 33 73 0"}; // Tarjeta de ejemplo

        int[] num = {57, 68, 19, 32, 36, 67, 46, 58, 6, 0, 9, 83}; // Lista de numeros de ejemplo
        System.out.println(validate(card, num));
    }


    public static String validate(String[] card, int[] num){
        int n = card.length; // Calcula una sola vez
        int[][] cardv = vectorizador(card, n); // Indicando que es un array n x n
        //probando diagonales
        int[] diag1 = new int[n];
        int[] diag2 = new int[n];
        int[] filas;
        int[] col = new int [n];
        Arrays.sort(num); // Ordenando array para aplicar busqueda binaria
        //diagonal 1
        for(int i = 0; i < n; i++){ // Este ciclo obtiene un array de la diagonal
            diag1[i] = cardv[i][i];         // diagonal 1
            diag2[i] = cardv[i][n - i - 1]; // diagonal 2
            filas = cardv[i]; // FILAS
            if (comprobador(filas, num)) {
                return "VALIDO por fila";
            }
            for (int j = 0; j < n; j++) {
                col[j] = cardv[j][i];// Obtiene columnas
            }
            if(comprobador(col, num)){
                return "VALIDO por columna";
            }
        }
        if(comprobador(diag1, num) || comprobador(diag2, num)){
            return "VALIDO por diagonal";
        }
        return "INVALIDO";
    }

    public static boolean comprobador(int[] arraux,int[] num){
        for (int anArraux : arraux) {
            if (Arrays.binarySearch(num, anArraux) < 0) {
                return false; // retorna falso si un elemento del arraaux no esta en las ganadoras
            }
        }
        return true; //retorna verdadero si todas pasan la prueba
    }

    public static int[][] vectorizador(String[] card, int n){// Recibe como parametro tarjeta y dimension
        int[][] tarjetaS = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] aux = card[i].split(" ");
            for (int j = 0; j < n; j++) {
                tarjetaS[i][j] = Integer.parseInt(aux[j]);
            }
        }
        return tarjetaS; //retorna una tarjeta de 2 dimensiones facil de analizar
    }
}
