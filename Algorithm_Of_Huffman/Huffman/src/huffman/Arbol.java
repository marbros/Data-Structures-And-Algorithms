package huffman;

/**
 * @author Mario Giraldo Restrepo
 * @version 0.0.3
 */
public class Arbol {
    
	public static Nodo nodo(int weight, Nodo one, Nodo two, Nodo root) {
		root = new Nodo("", weight);
		root.setHijoIzq(one);
		root.setHijoDer(two);
		return root;		
	}

	public static Nodo insertarNodo(int weight, Nodo one, Nodo two, Nodo 
                root) {
		
		if(root == null) {
			root = nodo(weight,one,two,root);
			//System.out.println("null");
		}
		//<= arbol con orden por la derecha
		else if(one.getValue() <= two.getValue()) {
			root = nodo(weight,one,two,root);
			//System.out.println("not <");
		}
		//>= arbol con orden por la izquierda
		else if(one.getValue() > two.getValue()) {
			root = nodo(weight,two,one,root);			
			//System.out.println("not >");
		}
		
		return root;
	}
        
        public static Nodo insertarNodo(int weight, Nodo one, Nodo root) {
            if(root == null) {
                root = new Nodo("", weight);
                root.setHijoIzq(one);
            }
            return root;
        }
}
