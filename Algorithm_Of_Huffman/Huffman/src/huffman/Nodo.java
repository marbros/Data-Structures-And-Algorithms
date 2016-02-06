/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 * @author Jhon Mario
 * @author Mario Giraldo Restrepo
 * @version 0.0.3
 */
public class Nodo implements Comparable<Nodo> {
    
	private String key;
	private int value;
	Nodo hijoIzq;
	Nodo hijoDer;

	public Nodo (String key, int value) {
		this.key = key;
		this.value = value;
		this.hijoIzq = null;
		this.hijoDer = null;
	}

	public Nodo getHijoDer() {
		return hijoDer;
	}

	public void setHijoDer(Nodo hijoDer) {
		this.hijoDer = hijoDer;
	}	

	public Nodo getHijoIzq() {
		return hijoIzq;
	}

	public void setHijoIzq(Nodo hijoIzq) {
		this.hijoIzq = hijoIzq;
	}

	public String getkey() {
		return key;
	}

	public void setkey(String key) {
            this.key = key;
	}

    public int getValue() {
        return value;
    }

    public void setvalue(int value) {
        this.value = value;
    }

     /**
     * Cuando se realiza el pool
     * Ordena de menor a mayor:
     * Ordena por clave --> this.getClave() - otro.getClave();
     */
        @Override
    public int compareTo(Nodo other) {
        return this.getValue() - other.getValue();
    }

    /**
     * Este metodo es el que visualiza los datos de cada elemento cuando
     * se llama cola.poll()
     * @return
     */
    @Override
    public String toString() {
        return "Nodo{" + "key=" + key + ",value=" + value + '}';    	
    }    
}
