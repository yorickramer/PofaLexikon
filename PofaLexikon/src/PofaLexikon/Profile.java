package PofaLexikon;
import java.io.*;
import java.util.ArrayList;

enum Gender {
    men,
    women,
    $
}

public class Profile{

    private String profilname;
    private String password;
    private Gender gender;
    private int born;
    private ArrayList<String> address = new ArrayList<String>();
    private ArrayList<String> friends = new ArrayList<String>();

    Profile(String n)throws IOException {
        profilname = n;
    }


    public boolean LoginSuccess(String pass) throws IOException {
        File file = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Registration.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            String[] string = st.split("\t");
            if (string[0].equals(profilname)&&(string[1].equals(pass))){
                br.close();
                password = pass;
                return true;
            }
        }
        br.close();
        return false;
    }


    public boolean CheckProfileAvailability() throws IOException {
        String s = ReadInData();
        if (s.equals("$")){
            return true;
        }
        return false;
    }


    public void CreateProfile(String pass) throws IOException {
        Writer output = new BufferedWriter(new FileWriter("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt", true));
        output.append(profilname+"\t0\t$\t$\t$\t$\t$\t$\t\n");
        output.close();
        Writer output2 = new BufferedWriter(new FileWriter("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Registration.txt", true));
        output2.append(profilname+"\t"+pass+"\n");
        output2.close();
        password = pass;
    }


    public String StringMaker(){
        StringBuffer sb = new StringBuffer();
        sb.append(profilname);
        sb.append("\t");
        sb.append(born);
        sb.append("\t");
        sb.append(gender);
        sb.append("\t");
        for (String s : address) {
            sb.append(s);
            sb.append("\t");
        }
        for (String s : friends) {
            sb.append(s);
            sb.append("\t");
        }
        String str = sb.toString();
        return str;
    }


    private void RemoveLine() throws IOException {
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt");
        File tempFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\myTempFile.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String lineToRemove = StringMaker();
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            if (currentLine.equals(lineToRemove)){
            }
            else{
                writer.write(currentLine + "\n");
            }
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt"));
    }


    private void Registration_file_update(String name) throws IOException {
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Registration.txt");
        File tempFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\myTempFilereg.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String[] string = currentLine.split("\t");
            if (!string[0].equals(profilname)){
                writer.append(string[0]+"\t"+string[1]);
            }
            else{
                writer.append(name+"\t"+string[1]);
            }
            writer.append("\n");
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Registration.txt"));
    }

    private void Feed_file_update(String name) throws IOException {
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Feed.txt");
        File tempFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\myTempFilefeed.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String[] string = currentLine.split("\t");
            if (!string[0].equals(profilname)){
                writer.append(string[0]+"\t"+string[1]);
            }
            else{
                writer.append(name+"\t"+string[1]);
            }
            writer.append("\n");
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Feed.txt"));
    }

    private void Messenger_file_update(String name) throws IOException {
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Messenger.txt");
        File tempFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\myTempFilemessenger.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String[] string = currentLine.split("\t");
            if(string[0].equals(profilname)&&!string[1].equals(profilname)){
                writer.append(name+"\t"+string[1]+"\t"+string[2]);
            }
            else if(!string[0].equals(profilname)&&string[1].equals(profilname)){
                writer.append(string[0]+"\t"+name+"\t"+string[2]);
            }
            else{
                writer.append(string[0]+"\t"+string[1]+"\t"+string[2]);
            }
            writer.append("\n");
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Messenger.txt"));
    }



    public void LoadProfile() throws IOException {
        String st = ReadInData();
        String[] string = st.split("\t");
        born = Integer.parseInt(string[1]);
        gender = Gender.valueOf(string[2]);
        address.add(string[3]);
        address.add(string[4]);
        address.add(string[5]);
        address.add(string[6]);
        address.add(string[7]);
        for(int i = 8; i < string.length; i++){
            friends.add(string[i]);
        }
    }


    private void SaveProfile() throws IOException {
        String str = StringMaker();
        Writer output = new BufferedWriter(new FileWriter("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt", true));
        output.append(str);
        output.append("\n");
        output.close();
    }


    private String ReadInData() throws IOException {
        File file = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null){
            String[] string = st.split("\t");
            if (string[0].equals(profilname)){
                br.close();
                return st;
            }
        }
        br.close();
        return "$";
    }


    public ArrayList<String> SearchByCountryorCity(String countryOrCity, boolean country) throws IOException {
        ArrayList<String> names = new ArrayList<String>();
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String[] string = currentLine.split("\t");
            if(country && string[3].equals(countryOrCity)){
                names.add(string[0]);
            }
            else if(!country && string[4].equals(countryOrCity))
                names.add(string[0]);
        }
        reader.close();
        return names;
    }


    public int GetBorn(){return born;}
    public Gender GetGender(){return gender;}
    public String GetName(){return profilname;}
    public ArrayList<String> GetAddress(){ return address; }
    public ArrayList<String> GetFriends() throws IOException { return friends; }


    public void SetBorn(int n) throws IOException {RemoveLine(); born=n; SaveProfile();}
    public void AddFriend(String friend) throws IOException {RemoveLine(); friends.add(friend); SaveProfile();}
    public void RemoveFriend(String friend) throws IOException {RemoveLine(); friends.remove(friend); SaveProfile();}
    public void SetGender(String g) throws IOException {RemoveLine(); gender = Gender.valueOf(g); SaveProfile();}
    public void SetAddress_Country(String s) throws IOException {RemoveLine(); address.set(0,s); SaveProfile();}
    public void SetAddress_City(String s) throws IOException {RemoveLine(); address.set(1,s); SaveProfile();}
    public void SetAddress_ZIP(String s) throws IOException {RemoveLine(); address.set(2,s);SaveProfile();}
    public void SetAddress_Street(String s) throws IOException {RemoveLine(); address.set(3,s);SaveProfile();}
    public void SetAddress_Number(String num) throws IOException {RemoveLine(); address.set(4,num);SaveProfile();}
    public void SetName(String s) throws IOException {
        File inputFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt");
        File tempFile = new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\myTempFile3.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String[] string = currentLine.split("\t");
            for(int i = 8; i<string.length;i++){
                if (string[i].equals(profilname)){
                    string[i] = s;
                }
            }
            if(string[0].equals(profilname))
                string[0] = s;
            for(int i = 0; i<string.length;i++){
                writer.append(string[i]+"\t");
            }
            writer.append("\n");
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\source\\Profiles.txt"));
        Registration_file_update(s);
        Feed_file_update(s);
        Messenger_file_update(s);
        profilname=s;
    }
}
