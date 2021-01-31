import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
*   PROBLEM:
*   Read text from file and count the frequency
*   of different chars.
*
 */

public class Frequence {

    public String readFile(String filename){
        String data = "";
        try  {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                data += scanner.nextLine();
            }

            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("Error");
            e.printStackTrace();
        }

        return data;
    }

    public void frequencyCalc(String filename){
        String data = readFile(filename);
        int [] ascii = new int[127];
        char [] chArr = data.toCharArray();
        for(char ch : chArr){
            ascii[(int)ch] += 1;
        }
        int arrlen = 0;
        for(int i = 0; i < 127; i++){
            if(ascii[i] > 0){
                arrlen++;
            }
        }

        float [] freqArr = new float[arrlen];
        for(int i = 0; i < 127; i++){
            if(ascii[i] > 0){
                float perc = ascii[i]/(float)data.length() * 100;
                System.out.printf((char)i + " Frequency: ");
                System.out.printf("%.2f", perc);
                System.out.println("%");

            }
        }

    }

    public static void main(String[] args) {
        Frequence freq = new Frequence();
        freq.frequencyCalc("file.txt");

    }

}
