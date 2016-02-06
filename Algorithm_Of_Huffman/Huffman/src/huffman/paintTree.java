/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mario Giraldo Restrepo
 */
public class paintTree {
    
    private BufferedImage img;
    public boolean paint = false;
    private int bheight = 50; // Altura de la rama
    private int yoff = 30;  // static y-offset      
    
        
    public paintTree(int x, int y){
        img = new BufferedImage(x, y, BufferedImage.BITMASK);
    }

    public BufferedImage getImagen() {
        return img;
    }
    
    /**
     *  Esta función retorna la máxima profundidad del arbol. 
     * 
     *  @param N  Nodo del árbol
     *  @return   max profundidad del árbol
     */
    public static int depth(Nodo N){
	if (N==null) return 0;
            int left = depth(N.hijoIzq);
            int right = depth(N.hijoDer);
        if (left>right) return left+1; else return right+1;
    }

    /**
     *  Este método recorre el arbol y lo pinta en un contenedor. 
     * 
     *  @param N   Nodo del árbol
     *  @param l   nivel del árbol
     *  @param lb  limite hijo izq 
     *  @param rb  limite hijo der
     */
    private void drawnode(Nodo N,int l, int lb, int rb) {
        Graphics2D display = (Graphics2D)img.getGraphics();
        if (N==null) return;
	try{Thread.sleep(100);} catch(Exception e) {}
        display.setColor(Color.white);

	display.setColor(Color.white);
        if(N.getkey().equals("")) {
            display.draw3DRect(((lb+rb)/2)-11,yoff+(l*bheight)-1,21,21,true);
            display.setColor(Color.blue);
            display.fill3DRect(((lb+rb)/2)-10,yoff+(l*bheight),20,20,true);
            display.setColor(Color.white);
            display.drawString(""+N.getValue(),((lb+rb)/2)-5,yoff+15+(l*bheight));
        }else {
            display.drawRoundRect(((lb+rb)/2)-11,yoff+(l*bheight)-1,27,21,10,10);
            display.setColor(Color.red);
            display.fillRoundRect(((lb+rb)/2)-10,yoff+(l*bheight),26,20,10,10);
            display.setColor(Color.white);
            display.drawString(N.getkey()+":"+N.getValue(),((lb+rb)/2)-5,yoff+15
                    +(l*bheight));
        }
	display.setColor(Color.cyan); 
        int h = (yoff+(l*bheight+bheight))-13;
        if (N.hijoIzq!=null)
	    {
   	       display.drawLine((lb+rb)/2,yoff+10+(l*bheight),
			((3*lb+rb)/4),yoff+(l*bheight+bheight));
               display.setColor(Color.white);
               display.drawString("0",((3*lb+rb)/4),h);
               drawnode(N.hijoIzq,l+1,lb,(lb+rb)/2);
	    }
        display.setColor(Color.cyan);
        if (N.hijoDer!=null)
	    {
               display.drawLine((lb+rb)/2,yoff+10+(l*bheight),
			((3*rb+lb)/4),yoff+(l*bheight+bheight));
               display.setColor(Color.white);
               display.drawString("1",((3*rb+lb)/4),h);
               drawnode(N.hijoDer,l+1,(lb+rb)/2,rb);
	    }
        
    } 
    
    public void drawtree(Nodo T, int XDIM, int YDIM) {
        if (T == null) return;
	int d = depth(T);
	bheight = (YDIM/d);
        drawnode(T,0,0,XDIM);
    }    
     
}
