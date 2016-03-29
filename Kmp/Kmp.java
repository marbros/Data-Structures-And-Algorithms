/**
 * @author Mario Giraldo Restrepo
 * @deprecated Algorithm of String Maching 
 * @version 0.0.1
 */

import java.util.Scanner;


public class Kmp {

	/**
	 *  Esta función retorna un arreglo f de longitud P+1 de pre_busqueda
	 *	de coincidencias del patrón.
	 *
	 *  @param P  Es el string a Buscar
	 *  @Retun f  
	 */
	private static int [] Kmp_Table (String P) {
		int i = 2; int j = 0; 
		char[] p = P.toCharArray();
		int m = p.length;		
		int [] f = new int[m+1];
		f[0] = -1; 
		f[1] = 0;
		while(i <= m) {
			if(p[i-1] == p[j]) {
				f[i++] = ++j;
			}else {
				if(j > 0) {
					j = f[j];
				}else {
					f[i] = 0;
					i++;
				}				
			} 
		}
		return f;
	}		

	/**
	 * Este método realiza una busqueda lineal de comparación entre cada caracter P[q] y T[i]
 	 * donde q,i son indices. En cada iteración del ciclo se guarda en una variable la
 	 * cantidad de ocurrencias en el texto; de lo contrario la cadena mas aproximada al 
 	 * patrón de busqueda de T.
 	 * 
 	 * 
 	 * @param T  Texto
 	 * @param P  Es el patrón del texto a buscar
	 */
	private static void kmp (String T, String P) {
		int [] aux = (int []) Kmp_Table(P).clone();
		int n = T.length() -1, m = P.length() - 1; 
		char[] t = T.toCharArray();
		char[] p = P.toCharArray();
		int q = 0, i = 0, contP = 0, max = 0;

		while(q < n) {
			System.out.println("i = " + i);
			if(p[i] == t[i+q]) {
				if(i >= m) {
					contP++;
					q = q + i;
					i = -1;
				}
				i++;				
				if(i > max) max = i;				
			}else {
				q = (q + i) - aux[i];
				if(i > 0) {
					i = aux[i];
				}
			}
		}
		System.out.println(print(P,max,contP));
	}

	/**
	 *	Esta función retorna el mensaje de salida del programa.
	 *
	 *  @param P     El patrón a buscar  
	 *  @param valq  cantidad de ocurrencias no completas del patrón en el texto
	 *  @param cont  cantidad de ocurrencias completas en el texto
	 *  @Return message
	 */	
	private static String print(String P, int valq, int cont) {
		String message;		
		if(cont > 0) {
			String n = " veces";
			if(cont == 1) n = " ves";
			return message = "El patron " + P + " se encuentra " + cont + n + " en el texto.";
		}

		if(valq <= 0) {
			message = "El patron " + P + " no se encuentra en el texto y no se hallo una subcadena " + 
							   "\n \t \t \t del patron que hiciera Maching con el texto.";			
		}else {
			message = "El patron " + P + " esta parcialmente en el texto y la subcadena " +
								"\n \t \t \t \t \t mas aproximada es " + P.substring(0,valq); //pat
		}
		return message;				
	}

	/**
	 *	Metodo De inicio.
	 */
	public static void main(String[] args) {
		Scanner Read = new Scanner(System.in);      
		String text;
		String pattern;
		System.out.print("Ingrese el texto : ");
		text = Read.nextLine();
		System.out.print("\n");		
		System.out.print("Ingrese el patron : ");
		pattern = Read.nextLine();
		System.out.println("");
		if("".equals(pattern)) {
			System.out.print("El pátron no fue ingresado");			
		}else {
			kmp(text,pattern); 
		}
	}
	
}