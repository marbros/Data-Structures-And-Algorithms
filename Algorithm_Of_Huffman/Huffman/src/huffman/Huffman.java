package huffman;

/**
 * @author Mario Giraldo Restrepo
 * @version 0.0.3
 */

import java.util.TreeMap;
import java.util.PriorityQueue;

public class Huffman {
    
    static Nodo treeD;
    static Nodo tree;
    private static String keys = "";
    private static String k = "";
    private static Text t = new Text();
    private static String keyC = "";
    private static String Separator = "~";
        /**
	 *  Este método se encarga de retirar cada par de elementos de menor
	 *  frecuencia en la cola, y sumar su peso con el fin de crear 
	 *  pequeños subArboles, que seran nuevamente agregados a la cola
	 *  hasta obtener un unico arbol.
	 *
	 *  @param cola   Cola de prioridades ordenada por frecuencia de 
	 *				  cada caracter en el mapa.	 
	 *  @param arbol  Es una instancia de la clase Arbol.
	 */
	private static void createTree(PriorityQueue cola, Arbol arbol, 
                huffmanGui UI) {

            Nodo father = null; 
            Nodo oneAux;

            if(cola.size() == 1) {
                oneAux = (Nodo) cola.poll();
                Integer oneOnly = oneAux.getValue();
                father = Arbol.insertarNodo(oneOnly, oneAux, father);
                cola.add(father);
            }
            while(cola.size() > 1) {  
                Nodo one = (Nodo) cola.poll();
                Nodo two = (Nodo) cola.poll();               
                Integer onePlusTwo = one.getValue() + two.getValue(); 
                father = Arbol.insertarNodo(onePlusTwo,one,two,father);
                cola.add(father);    	
            }
            tree = (Nodo)cola.peek();
            getKeys((Nodo)cola.poll());
	}

	/**
	 *  Este método realiza un recorrido en orden de un arbol  
	 *
	 *  @param arbol  Es una instancia de la clase Nodo.
	 *	@param key    Es el binario 0 ó 1 asociado a cada arco del arbol
	 */
        public static void order(Nodo arbol, String key){
            keys += key;
            if (arbol != null){
                order(arbol.getHijoIzq(), "0");
                if(!(arbol.getkey().equals("")))
                    k += (arbol.getkey() + " : " + keys + Separator);
                order(arbol.getHijoDer(), "1");
            }
            keys = keys.substring(0,keys.length()-1);
        }

	/**
	 *  Este método realiza un recorrido a medias, en Pos Orden de un arbol,
	 *  con el fin de asociar un valor binario a cada arco de este.
	 *
	 *  @param arbol  Es una instancia de la clase Nodo.
	 */
        public static String getKeys(Nodo arbol) {
            if(arbol != null) {
                order(arbol.getHijoIzq(), "0");
                order(arbol.getHijoDer(), "1");   		
            }
            return k;
        }

	/**
	 *  Este método imprime las claves asociadas a cada hoja del arbol.
	 */
	public static void printKeys(huffmanGui UI) {	
		String Keys;
                if(k.contains("\n")) {
                    Keys = k.replace("\n","º");
                    Keys = Keys.replace(Separator, "\n");
                }else {
                    Keys = k.replace(Separator, "\n"); 
                }
                
                UI.txaKeys.setText("-Keys- \n" + Keys);
		System.out.println("-Keys- \n" + Keys);
                Keys = Keys.replace(" ", "");
                t.write("claves.txt", Keys);
	}

	/**
	 *  Esta función realiza un reemplazo de cada caracter del texto
	 *  por su respectiva key asignada al arbol; luego imprime el nuevo
	 *  texto resultante del anterior proceso. 
	 *
	 *	@param T  Texto
	 */
	public static String prinTextCompress(String T) {
		char[] Keys;
                Keys = T.toCharArray();
		char comp;
		String keyComplete = "";
                String _k = k.replaceAll(" : ","");
		for(int i = 0; i <= Keys.length-1; i++) {
			comp = Keys[i]; 
			if(Keys[i] == comp) {
                            int x = _k.indexOf(comp)+1;
                            keyComplete += _k.substring(_k.indexOf(comp)+1,
                                    _k.indexOf(Separator,_k.indexOf(comp)));
			}
		}
                t.write("comprimido.txt", keyComplete);
                return keyComplete;
	}

	/**
	 *  Esta Función convierte cada caracter del texto a un binario, luego
	 *  imprime el nuevo texto resultante del anterior proceso.
	 *
	 *	@param T  Texto
	 */
	public static String printTextDescompress(String T) {
		char[] Keys;
                Keys = T.toCharArray();

		String keyBinary[] = new String[T.length()];
		String keyBin = "";
		for (int i = 0; i <= Keys.length-1; ++i) {
			keyBinary[i] = String.format("%s", 
                                Integer.toBinaryString(Keys[i]));
			keyBin += keyBinary[i] + " ";
		}
                return keyBin;
	}

	/**
	 *  Este método es una extencion de treeStruct que realiza el
	 *  recorrido de un arbol hasta llegar asus hojas.
	 *
	 *	@param root   Nodo del arbol actual
         *      @param key[]  keys del texto comprimido
         *      @param pos    Posición del char actual
         *      @param len    Tamaño del arreglo key[]
	 */        
        public static void orderT(Nodo root, char[] key, int pos, int len) {
            if(pos <= len && len != 1) {
                Nodo node = new Nodo("",0);
                if(key[pos] == '0') {
                    if(root.getHijoIzq() == null){
                        root.setHijoIzq(node);
                    }
                    orderT(root.getHijoIzq(),key,pos+1,len);
                }else {
                    if(root.getHijoDer() == null){
                        root.setHijoDer(node);                
                    }
                    orderT(root.getHijoDer(),key,pos+1,len);
                }
            }
            if(root.getHijoIzq() == null && root.getHijoDer() == null){
                root.setkey(Character.toString(key[0]));                
            }
        }

        /**
	 *  Este método completa la estructura del arbol de huffman sumando
         *  las frecuencias de cada valor encontrado del texto cifrado.
	 *
	 *	@param root   Nodo actual del Árbol
         *      @param bi[]   Texto Cifrado
         *      @param posi   Posición inicial
         *      @param posf   Posición Final
	 */        
        public static int frecuence(Nodo root, String [] bi, int posi, int posf)
        {
            if(root.getHijoIzq() != null || root.getHijoDer() != null){
                root.setvalue(root.getValue()+1);
                switch (bi[posf]) {
                    case "0":
                        posf = frecuence(root.getHijoIzq(),bi,posi,posf+1);
                        break;
                    
                    case "1":
                        posf= frecuence(root.getHijoDer(),bi,posi,posf+1);
                        break;
                }
            }else {
                root.setvalue(root.getValue()+1);
            }
            if(root.getkey().equals("º")) {
                root.setkey("\n");
            }
            keyC += root.getkey();
            return posf;
        } 

        /**
	 *  Este método crea la estructura del arbol de huffman a partir de
	 *  unas keys dadas.
	 *
	 *	@param root   Nodo raiz
         *      @param key    keys del texto comprimido
	 */
        public static Nodo treeStruct(Nodo root, String key){
            char [] charac;
            charac = key.toCharArray();
            char firtsChar = charac[0];
            Nodo aux,node;
            node = new Nodo("",0);
            int pos = 3;
            int i = 2;
            if(firtsChar == ':') {
                i = 1;
                pos = 2;
            }
            if(charac[i]=='0'){
                if(root.getHijoIzq() != null){
                    root.setvalue(root.getValue());
                }else {
                    root.setHijoIzq(node); 
                }
                orderT(root.getHijoIzq(),charac,pos,charac.length-1);                        
            }else {
                if(root.getHijoDer()!= null){
                    root.setvalue(root.getValue());
                }else {                
                    root.setHijoDer(node);
                }
                orderT(root.getHijoDer(),charac,pos,charac.length-1);                        
            }
            return root;
        }
        
	/**
         *  Este método crea el arbol de codificación huffman por medio de
         *  métodos de apoyo, reemplazando cada caracter del texto a un binario,
         *  después retorna el nuevo texto resultante del anterior proceso.
	 *
	 *	@param TC  Texto Comprimido
         *      @param key Claves Del Texto
	 */
	public static String printTextDescompress(String TC, String key,
                huffmanGui UI) {
            Nodo root = new Nodo("",0);
            //key = key.replace(" : ", "");
            //key = key.replace(":", "");
            String [] Keys;
            Keys = key.split(Separator);
            
            String [] code;
            code = TC.split("");
            
            for(int i = 0; i <= Keys.length-1; ++i) {
                root = treeStruct(root,Keys[i]);
            }
            for(int i = 1; i < code.length-1; ++i) {
                i = frecuence(root,code,0,i)-1;               
            }
            treeD = root;
            UI.txaText.setText(keyC);
            return keyC;
        }                          
        
	/**
	 *  Esta función retorna un cola de prioridades ordenada por   
	 *  frecuencia de cada caracter en el mapa.
	 *
	 *  @param map    Es el mapa de caracteres asociado con su frecuencia.
	 *  @param arbol  Es una instancia de la clase Arbol
	 *  @Retun cola
	 */
	private static PriorityQueue col_priority(TreeMap map, Arbol arbol) {
            PriorityQueue<Nodo> cola = new PriorityQueue<>();			
            for (Object key : map.descendingKeySet()) {
                cola.add(new Nodo((String)key,(int)map.get(key)));
            }         
            return cola;
	}
        
	/**
	 *  Esta función retorna un map <k,v> con cada caracter  
	 *  y el número de veces con que este se repite en un texto.
	 *
	 *  @param T     Es el Texto
         *  @param type  Es el identificador de la función de llamado
	 *  @Retun map
	 */
	private static TreeMap cont_Letters(String T, String type) {
                String[] letters;
                if(type.equals("letters")) {
                    letters = T.split("");
                }else{
                    letters = T.split(" ");                    
                }
		int len = letters.length;
		TreeMap <String, Integer> map = new TreeMap <>();

		for (int i=0; i < len; ++i) {
                    String key = letters[i];
                    if (letters[i].length() > 0) {
                        if(map.get(key) == null) {
                            map.put(key, 1);
                        }else {
                            int value = map.get(key).intValue();
                            value++;
                            map.put(key,value);
                        }
                    }
		}
		
		return map;	
	}
        
        /**
         *  Este método imprime el texto comprimido y descomprimido
         *  en la UI del programa.
         * 
         *  @param type  Comprimido | Descomprimido
         *  @param Text  Texto Comprimido | Descomprimido
         *  @param UI    Interfaz Gráfica
         */
        public static void printText(String type, String Text, huffmanGui UI) {
            if(Text.length() > 47) {
                UI.txaText.setText(UI.txaText.getText() + "\n" + "Texto " + type + ": " + Text + "\n");
            }else {
                if(type.equals("Comprimido")) {
                    UI.lblcTextCompress.setText(Text);
                }else {
                    UI.lblcTextDesCompress.setText(Text);
                }
            }            
        }

	/**
	 *  Este método realiza la tarea de contenedor de cada funcíon y  
	 *  método del programa y los ejecuta en un orden establecido.
	 *
	 *  @param T      Es el Texto
	 *  @Retun arbol  Es una instancia de la clase Arbol.
	 */
	public static void huffman (String T, String K, Arbol arbol, 
                huffmanGui UI, String action) {
            if(action.equals("Compress")) {
		createTree(col_priority(cont_Letters(T,"letters"),arbol),arbol
                        ,UI);
                printKeys(UI);
                String textCompress = prinTextCompress(T);
		System.out.println("Texto Comprimido: " + textCompress);
                
                printText("Comprimido",textCompress,UI);
                        
                k = "";
                String textDesCompress = printTextDescompress(T);
		System.out.println("Texto Sin Comprimir: " +
                printTextDescompress(T));
                
                printText("Sin Comprimir",textDesCompress,UI);
                
            }else {
                String comprenssion = printTextDescompress(T,K,UI);
                System.out.println("Texto DesCifrado:   " + comprenssion);
            }  
	}

        /**
         *	Metodo de inicio.
         */
        public static void main(String[] args) {
                huffmanGui view = new huffmanGui();
                view.setVisible(true);
                view.setLocationRelativeTo(null);
	}
}
