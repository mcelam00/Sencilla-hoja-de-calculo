import java.util.ArrayList;
import java.util.Scanner;

public class MainHojaCalculo {
//podemos definir una variable global que sea numero de hojas, la relleno desde el metodo y luego ya la tengo en el main y puedo ponerlo como condicion de parada en el for. otra opcion seria poenrlo en el main con el scanner o en otro metodo 
	public static void main(String[] args) {

		
		
	
		ArrayList<String[][]> ListaHojas;
		ListaHojas = lectura(); /** Me vuelven todas las hojas leidas **/
		//Aqui llega mi lista de hojas con todas metidas
		int numeroHojasEnMiLista = ListaHojas.size();
		

			
			for (int i = 0; i < numeroHojasEnMiLista; i++){ //Bucle que recorre el numero de listas que tengo en el ArrayList
				String[][] Hojai = ListaHojas.get(i); //saco la lista correspondiente a la iteracion i de mi ArrayList
				
				Hoja oHojaExcel = new Hoja(Hojai); //Mi hoja en strings me la va a pasar a objeto
				oHojaExcel.resuelve(); 
							
				//PRECONDICION: en la caja que es mi objetooHojaExcel ya tengo guardadas la hoja de entrada y la hoja de salida que es la de entrada resuelta, entonces solo la pinto
				
				imprimeTablaEnteros(oHojaExcel); 
				
				/**  DESCOMENTAR PARA FUNCIONALIDADES ADICIONALES
				
				sumameLosElementosDeLaTabla(oHojaExcel);
				sumaFilasPares(oHojaExcel);
				sumaColumnasPares(oHojaExcel);
				sumaFilasImpares(oHojaExcel);
				sumaColumnasImpares(oHojaExcel);
				sumaDiagonal(oHojaExcel);
				
				 **/
				
				
			
							
			/** DESCOMENTAR PARA TENER LA FUNCIONALIDAD DE PINTAR LAS HOJAS CORRECTAS AUNQUE POR EL MEDIO SE INTRODUZCA UNA INCORRECTA
				
					//solamente se imprimira la hoja si esta correcta, es decir, si el booleano que han en la caja dice que esta correcta y no hay ningun fallo de entrada
				
				if(oHojaExcel.getTablaCorrecta() == true) {
					imprimeTablaEnteros(oHojaExcel.getHojaSalida()); //si el boolean global es true se imprime, si no, se salta esa hoja y se imprimen las que no tengan errores
				}
				
				**/
			}
		
		
	}

	
	

	
	
	
	
	
	



	/**ZONA DE LOS METODOS **/



	private static void imprimeTablaEnteros(Hoja oHojaExcel) {	
		
		String [][] hojaEntrada = oHojaExcel.getHojaEntrada(); /** Le paso la hoja de entrada para si se introduce un string que lo ponga tal cual estÃÂ¡ de la hoja de entrada  **/
		int [][] hojaSalida = oHojaExcel.getHojaSalida();
		StringBuffer cadenaTextoMetida = new StringBuffer(); /** Es para ir apendeando las palabras y pintarlas abajo si se requiere esa funcionalidad**/
		
		for(int i = 0; i < hojaSalida.length; i++) { //numero de filas, por ejemplo 2
			
			for(int j = 0; j < hojaSalida[i].length; j++) { //numero de columnas por ejemplo 3
				
					if(esUnaCadenaNormal(i,j,oHojaExcel) == true) { 		/** SI ES STRING SE PINTA DE LA TABLA DE ENTRADA**/
						
						//pinto el string de la cadena de entrada tal cual
						
						System.out.print(hojaEntrada[i][j]);
						
						//lo anexo a mi cadena que mostaré debajo de la matriz
						cadenaTextoMetida.append(hojaEntrada[i][j]);
						cadenaTextoMetida.append(" "); //le anexo un espacio entre palabras
						
					}else {						/** SI ES FORMULA O NUMERO SE PINTA DE LA TABLA DE SALIDA**/
						
						//pinto tal cual esta en la tabla de salida, es decir, la formula resuelta o el numero tal cual se introdujo
					System.out.print(hojaSalida[i][j]);
					
					}
					
					if(j != (hojaSalida[i].length-1)) { //En una matriz 2x3 al llegar a la ultima columa (3-1 en terminos de matrices porque empiezo a contar desde el 0) no tiene que pintar el espacio.
			
						System.out.print(" ");
					
					}
			
			}
			
			System.out.println();
		
		}
		
		/** PINTO LA CADENA EN ORDEN DE TODOS LOS CARACTERES TEXTO METIDOS  ----> DESCOMENTAR SI SE REQUIERE **/
			
	//	System.out.println(cadenaTextoMetida.toString().substring(0,cadenaTextoMetida.toString().length()-1)); //para quitarle el ultimo espacio hago un substring de toda la cadena sin coger el último espacio
	
		
		
		
}


	//Cogeremos la tabla de entrada(la leída, y si en cada posicion (me viene ya en el i y j) no es ni numero ni formula es que es string normal y retornamos true para pintarlo tal cual

	
	
	private static boolean esUnaCadenaNormal(int i, int j, Hoja oHojaExcel) {
		String[][] HojaEntrada = oHojaExcel.getHojaEntrada();
		boolean testigo = false;
		
		//si en la posicion actual del for, en la matriz de entrada, no hay un numero, ni hay una formula, es que es string y se devuelve true para pintarla tal cual
		if(oHojaExcel.esNumero(HojaEntrada[i][j]) == false && oHojaExcel.esFormula(HojaEntrada[i][j]) == false) { 
				testigo = true;
			
		}
		
			
		return testigo;
	}








	//Para inprimir la hoja de entrada, actualmente no se usa

/**

	private static void imprimeTabla(String[][] Hoja) { 

		for (int f = 0; f < Hoja.length; f++) {

			   for (int c = 0; c < Hoja[f].length; c++) {

			       System.out.print(Hoja[f][c] + " ");

			   }

			   System.out.println();
			}
		
		
		
}
**/

		

private static ArrayList<String[][]> lectura() {
	 
	Scanner leerTeclado = new Scanner(System.in);
	
	//Leo el número de hojas de cálculo (primera línea de mi programa)
	
	int numeroHojas;
	numeroHojas = leoNumeroHojasDeCalculo(leerTeclado); 
	
	ArrayList<String[][]> ListaHojas = new ArrayList<String[][]>(); /** Creo una lista para guardar en ella todas las hojas que vaya leyendo y no perderlas **/
	
	
		//En función del numero de hojas de cálculo que se quieran leer hacemos un for porque todo lo que viene ya se repite igual para todas las hojas.
		
		for(int i = 0; i < numeroHojas; i++) {
			String Hojai [][] = LeerCadaHoja(leerTeclado);	//En cada iteración (que es cada hoja) llamo al método que me hace su lectura.
			
			/** PRECONDICION: Hojai es la hoja rellenada **/
			
			ListaHojas.add(Hojai); /**  Añado la hoja rellena a la lista de hojas para que no se pierda**/
		}
		
	leerTeclado.close(); //cerramos la entrada estandar	

	return ListaHojas; /** retorno la lista de hojas **/

}





//La primera línea de la entrada estándar contiene el número de hojas de cálculo que siguen. 

private static int leoNumeroHojasDeCalculo(Scanner leerTeclado) {
	
	String numeroHojas;
	int numeroDeHojas;
	numeroDeHojas=0;
	
	//leo un entero
	numeroHojas = leerTeclado.nextLine();
	try {
	numeroDeHojas = Integer.parseInt(numeroHojas);
	}catch (Exception MeteUnString) {
		System.out.println("Entrada Inválida.");//Se ha introducido algo que no es un numero
		Hoja.salir(); //me salgo del programa
	}
	//lo retorno
	return numeroDeHojas;
}






//Las siguientes líneas de la hoja de cálculo contienen cada una una fila. 

private static String[][] LeerCadaHoja(Scanner leerTeclado) {
	
	//Cada hoja de cálculo comienza con una línea que consta de dos números enteros, separadas por un espacio, dando el número de columnas y filas. 
	String HojaCalculo[][];

	HojaCalculo = leerDimensiones(leerTeclado); /**  Retorna la hoja creada pero vacía **/
	
	//ya tengo la matriz creada pero vacía, entonces la voy a rellenar
	String Filai;
	String filai [];
	
	for(int i = 0; i < HojaCalculo.length; i++) { //recorro las filas 
		
		Filai = leerTeclado.nextLine(); //leo la línea sub i como cadena
	 	filai = Filai.split(" "); //separo por el espacio
	 	
	 	if(filai.length != HojaCalculo[0].length) { //compruebo que no se dice 2x2 y se mete 2x3
			System.out.println("Entrada Inválida."); 
			Hoja.salir();
	 	}	 	
		
	 	/** CADA POSICION DEL VECTOR filai SE CORRESPONDE CON EL NUMERO DE COLUMNA DE LA MATRIZ QUE QUEREMOS RELLENAR **/	
		
	 	for(int j = 0; j < HojaCalculo[i].length; j++) {
			
	 		HojaCalculo[i][j] = filai[j];
					
		}
	}
	/** Ya tengo la hoja rellena **/

return HojaCalculo;	
}




//SE LEE UNA LINEA QUE SE SPLITEA Y SE CONVIERTE EN LAS DIMENSIONES
//CON ELLAS SE CREA LA MATRIZ Y SE DEVUELVE 

private static String[][] leerDimensiones(Scanner leerTeclado) {
	
	String linea1cadena; //creo el string para leer la linea
	String linea1vector[]; //creo el vector para separar por el espacio
	
	int numeroDeFilasHoja = 0;
	int numeroDeColumnasHoja = 0;
	
	//Voy a leer el numero de columnas y de filas
	linea1cadena = leerTeclado.nextLine();
	linea1vector = linea1cadena.split(" "); //separo por el espacio en las distintas posiciones del vector
	
	//OJO QUE EN LAS ESPECIFICACIONES DEL ENUNCIADO ESTAN PUESTAS AL REVES; el primer numero son las columnas
		//cada una de las 2 posiciones de mi vector (que seran respetivamente columna - fila) las traduzco a entero

	try {
	numeroDeColumnasHoja = Integer.parseInt(linea1vector[0]); 
	numeroDeFilasHoja = Integer.parseInt(linea1vector[1]);
	}catch (Exception NoEsNumero) {
		System.out.println("Entrada Inválida."); //o en la fila o en la columna se ha introducido algo que no es un numero
		Hoja.salir();

	}
	

	
	//CREO LA HOJA
	
	String HojaDeCalculo [][];
	HojaDeCalculo = new String [numeroDeFilasHoja][numeroDeColumnasHoja];
	
	return HojaDeCalculo;
}

///////////////////////////////////////////////////////////////////////////////////////// FUNCIONALIDADES OPCIONALES ///////////////////////////////////////////////////////////////////////////////////////////



private static void sumameLosElementosDeLaTabla(Hoja oHojaExcel) {
	int sumaTodosElementos;
	sumaTodosElementos = 0;
	
	for(int i = 0; i < oHojaExcel.getHojaEntrada().length; i++) {
		for(int j = 0; j < oHojaExcel.getHojaEntrada()[i].length; j++) {
			
			//NO HAY QUE PREOCUPARSE, si meto un string en la de entrada en la de salida no se guarda, queda a 0 con lo cual a efectos de la suma no importa puesto que se suman las casillas que no son string
			sumaTodosElementos = sumaTodosElementos + oHojaExcel.getHojaSalida()[i][j];		
			
			
		}
	}
	System.out.println("La suma de todos los elementos de la matriz es: "+sumaTodosElementos);
	
}




private static void sumaDiagonal(Hoja oHojaExcel) {
	int sumaDiagonal;
	sumaDiagonal = 0;
	
	for(int i = 0; i < oHojaExcel.getHojaEntrada().length; i++) {
		for(int j = 0; j < oHojaExcel.getHojaEntrada()[i].length; j++) {
			
			if(i == j) { /**  La característica principal de la diagonal de una matriz, es que la fila y la columna es la misma **/
				
			//NO HAY QUE PREOCUPARSE, si meto un string en la de entrada en la de salida no se guarda, queda a 0 con lo cual a efectos de la suma no importa puesto que se suman las casillas que no son string
			
			sumaDiagonal = sumaDiagonal + oHojaExcel.getHojaSalida()[i][j];		
			}
			
		}
	}
	System.out.println("La suma de todos los elementos de la diagonal es: "+sumaDiagonal);
	
}




/**  EL I+1 O J+1 ATIENDE A QUE NUESTRA TABLA TOMA LOS ELEMENTOS DESDE EL 0 PERO A EFECTOS DE REALIDAD SE CUENTA DESDE EL UNO **/
/**  CON LO CUAL, LAS FILAS PARES SEGUN LA MATRIZ SERÍAN LA 0 Y LA 2 -la primera y la ultima- PERO EN LA REALIDAD CONTAMOS DESDE EL 1 CON LO CUAL SERÍA SOLO LA 2 -la del medio- (EN UNA 3X3) **/


private static void sumaColumnasImpares(Hoja oHojaExcel) {
	int sumaColumnasImpares;
	sumaColumnasImpares = 0;
	
	for(int i = 0; i < oHojaExcel.getHojaEntrada().length; i++) {
		for(int j = 0; j < oHojaExcel.getHojaEntrada()[i].length; j++) {
			
			if((j+1)%2 != 0) { /**  Un número es impar si al dividirlo entre 2 no da resto cero **/
				
			//NO HAY QUE PREOCUPARSE, si meto un string en la de entrada en la de salida no se guarda, queda a 0 con lo cual a efectos de la suma no importa puesto que se suman las casillas que no son string
			
				sumaColumnasImpares = sumaColumnasImpares + oHojaExcel.getHojaSalida()[i][j];		
			}
			
		}
	}
	System.out.println("La suma de todos los elementos de las Columnas Impares es: "+sumaColumnasImpares);
	
}







private static void sumaFilasImpares(Hoja oHojaExcel) {
	int sumaFilasImpares;
	sumaFilasImpares = 0;
	
	for(int i = 0; i < oHojaExcel.getHojaEntrada().length; i++) {
		for(int j = 0; j < oHojaExcel.getHojaEntrada()[i].length; j++) {
			
			if((i+1)%2 != 0) { /**  Un número es impar si al dividirlo entre 2 no da resto cero **/
				
			//NO HAY QUE PREOCUPARSE, si meto un string en la de entrada en la de salida no se guarda, queda a 0 con lo cual a efectos de la suma no importa puesto que se suman las casillas que no son string
			
				sumaFilasImpares = sumaFilasImpares + oHojaExcel.getHojaSalida()[i][j];		
			}
			
		}
	}
	System.out.println("La suma de todos los elementos de las Filas Impares es: "+sumaFilasImpares);
	
}







private static void sumaColumnasPares(Hoja oHojaExcel) {
	int sumaColumnasPares;
	sumaColumnasPares = 0;
	
	for(int i = 0; i < oHojaExcel.getHojaEntrada().length; i++) {
		for(int j = 0; j < oHojaExcel.getHojaEntrada()[i].length; j++) {
			
			if((j+1)%2 == 0) { /**  Un número es par si al dividirlo entre 2 da resto cero **/
				
			//NO HAY QUE PREOCUPARSE, si meto un string en la de entrada en la de salida no se guarda, queda a 0 con lo cual a efectos de la suma no importa puesto que se suman las casillas que no son string
			
				sumaColumnasPares = sumaColumnasPares + oHojaExcel.getHojaSalida()[i][j];		
			}
			
		}
	}
	System.out.println("La suma de todos los elementos de las Columnas Pares es: "+sumaColumnasPares);
	
}








private static void sumaFilasPares(Hoja oHojaExcel) {
	int sumaFilasPares;
	sumaFilasPares = 0;
	
	for(int i = 0; i < oHojaExcel.getHojaEntrada().length; i++) {
		for(int j = 0; j < oHojaExcel.getHojaEntrada()[i].length; j++) {
			
			if((i+1)%2 == 0) { /**  Un número es par si al dividirlo entre 2 da resto cero **/
				
			//NO HAY QUE PREOCUPARSE, si meto un string en la de entrada en la de salida no se guarda, queda a 0 con lo cual a efectos de la suma no importa puesto que se suman las casillas que no son string
			
				sumaFilasPares = sumaFilasPares + oHojaExcel.getHojaSalida()[i][j];		
			}
			
		}
	}
	System.out.println("La suma de todos los elementos de las Filas Pares es: "+sumaFilasPares);
	
}



}