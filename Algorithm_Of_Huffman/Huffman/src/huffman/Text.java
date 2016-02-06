package huffman;

/**
 * @author Mario Giraldo Restrepo
 * @author Alejandro Taborda
 * @version 0.0.3
 */

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;


public class Text {
    
	public void write(String nameFile, String content) {
		File f; //crea un objeto de tipo archivo;
		f = new File(nameFile);

		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);

                        wr.write(content);
			wr.close();
			bw.close();
		}catch (Exception e) {
			
		}
	}
        
	public String read(String nameFile, String type) {
            String file = "";
		try {
			FileReader r = new FileReader(nameFile);
			BufferedReader buffer = new BufferedReader(r);

                        String temp = "";
			while (temp != null) {
				temp = buffer.readLine();
				if(temp == null) {
					break;
				}
                                switch(type) {
                                    case "Compress":
                                        file += temp;
                                        break;
                                    case "keys":
                                        if(!temp.equals("")) {
                                            file += temp + "~"; 
                                        }
                                        break;
                                    default:
                                        file += temp + "\n";
                                }
			};
		}catch(Exception e) {
			System.out.println(e.getMessage());
		};
                return file;
	}        
}
