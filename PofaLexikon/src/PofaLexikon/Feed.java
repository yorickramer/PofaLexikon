package PofaLexikon;
import java.io.*;
import java.util.ArrayList;

public class Feed {

    public void NewPost(String person, String post) throws IOException {
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Feed.txt");
        File tempFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\myTempFile3.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        writer.write(person + "\t" + post);
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
                writer.write("\n" + currentLine);
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Feed.txt"));
    }


    public ArrayList<String> PostofProfile(String person) throws IOException {
        ArrayList<String> posts = new ArrayList<String>();
        File file = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Feed.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            String[] string = st.split("\t");
            if (string[0].equals(person)){
                posts.add(string[1]);
            }
        }
        return posts;
    }



    public ArrayList<String> GetFeed(String Person1) throws IOException {
        ArrayList<String> posts = new ArrayList<String>();
        Profile profile_temp = new Profile(Person1);
        profile_temp.LoadProfile();
        ArrayList<String> friends = profile_temp.GetFriends();

        File file = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Feed.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null){
            String[] string = st.split("\t");
            for(int i = 0 ; i < friends.size();i++){
                if (string[0].equals(friends.get(i))){
                    posts.add(friends.get(i) + "                                                             " + string[1]);
                }
            }
            if (string[0].equals(Person1)){
                posts.add("Én                                                              " + string[1]);
            }
        }
        br.close();
        return posts;
    }
}


