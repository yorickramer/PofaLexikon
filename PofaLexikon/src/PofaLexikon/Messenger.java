package PofaLexikon;
import java.io.*;
import java.util.ArrayList;

public class Messenger {

    Messenger(){}


    public void NewMessage(String from, String to, String message) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Messenger.txt", true));
        output.append(from);
        output.append("\t");
        output.append(to);
        output.append("\t");
        output.append(message);
        output.append("\n");
        output.close();
    }


    public ArrayList<String> GetChat(String Person1, String Person2) throws IOException {
        ArrayList<String> line = new ArrayList<String>();
        File file = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Messenger.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            String[] string = st.split("\t");
            if (string[0].equals(Person1) && string[1].equals(Person2))
                line.add(string[2]);
            else if(string[0].equals(Person2) && string[1].equals(Person1))
                line.add("$"+string[2]);
        }
        br.close();
        return line;
    }
}



