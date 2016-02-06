package huffman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario Giraldo R
 */
public class HuffmanTest {
    
    public HuffmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of getKeys method, of class Huffman.
     */
    @Test
    public void testGetKeys() {
        System.out.println("getKeys");       
        Nodo arbol = new Nodo("",4);
        Nodo item = new Nodo("a",2);
        Nodo item1 = new Nodo("r",1);
        Nodo item2 = new Nodo("m",1);
        Nodo item3 = new Nodo("",2);
        item3.setHijoDer(item1);
        item3.setHijoIzq(item2);
        arbol.setHijoDer(item);
        arbol.setHijoIzq(item3);
        String expResult = "m : 00~r : 01~a : 1~"; 
        String result = Huffman.getKeys(arbol);
        assertEquals(expResult, result);
    }

    /**
     * Test of prinTextCompress method, of class Huffman.
     */
    @Test
    public void testPrinTextCompress() {
        System.out.println("prinTextCompress");
        /*m : 00
          r : 01
          a : 1*/
        String T = "maar";
        String expResult = "001101";
        String result = Huffman.prinTextCompress(T);
        assertEquals(expResult,result);
    }

    /**
     * Test of printTextDescompress method, of class Huffman.
     */
    @Test
    public void testPrintTextDescompress() {
        System.out.println("printTextDescompress");
        String T = "maar";
        String expResult = "1101101 1100001 1100001 1110010 ";
        String result = Huffman.printTextDescompress(T);
        assertEquals(expResult,result);
    }
    
    /**
     * Test del método printTextDescompress, dado un texto comprimido y sus
     * claves.
     */
    @Test
    public void testPrintTextDescompressFull() {
        System.out.println("printTextDescompressFull");
        String TC = "001101";
        String keys = "m : 00~r : 01~a : 1~";
        String expResult = "maar";
        huffmanGui UI = new huffmanGui();
        String result = Huffman.printTextDescompress(TC, keys, UI);
        assertEquals(expResult,result);
    }
}