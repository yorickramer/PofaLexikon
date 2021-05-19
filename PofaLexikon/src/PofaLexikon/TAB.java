package PofaLexikon;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TAB extends JFrame{



    public TAB() throws IOException {
        Login();
    }


    public void Login() throws IOException {

        JFrame frame = new JFrame("PofaLexikon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 200));
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel login_panel = new JPanel();
        JPanel registration_panel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();

        //BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\design\\head.jpg"));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        //login_panel.add(picLabel);
        login_panel.add(new JLabel("Profile name:"));
        JTextField profilename_login = new JTextField("",20);
        login_panel.add(profilename_login);
        login_panel.add(new JLabel("Password:"));
        JTextField password_login = new  JTextField("",10);
        login_panel.add(password_login);
        JButton login_button = new JButton("LOGIN");
        login_panel.add(login_button);
        JLabel login_respond = new JLabel("Please write your profile name and password");
        login_panel.add(login_respond, BorderLayout.CENTER);

        registration_panel.add(new JLabel("Profile name:"));
        JTextField profilename_reg = new JTextField("", 20);
        registration_panel.add(profilename_reg);
        registration_panel.add(new JLabel("Password:"));
        JTextField password_reg = new JTextField("", 10);
        registration_panel.add(password_reg);
        JButton reg_button = new JButton("REGISTRATION");
        registration_panel.add(reg_button);
        JLabel reg_respond = new JLabel("Please write your profile name and password");
        registration_panel.add(reg_respond, BorderLayout.CENTER);

        login_button.setFont(new Font("Arial", Font.BOLD, 15));
        reg_button.setFont(new Font("Arial", Font.BOLD, 15));
        login_button.setBackground(Color.BLACK);
        reg_button.setBackground(Color.BLACK);
        login_button.setForeground(Color.white);
        reg_button.setForeground(Color.white);
        login_panel.setBackground(Color.ORANGE);
        registration_panel.setBackground(Color.ORANGE);
        tabbedPane.setBackground(Color.ORANGE);

        tabbedPane.addTab("Login", login_panel);
        tabbedPane.addTab("Registration", registration_panel);
        frame.add(tabbedPane);
        frame.pack();

        login_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(!profilename_login.getText().equals("") && !password_login.getText().equals("")){
                    Profile p = null;
                    try {
                        p = new Profile(profilename_login.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        if(p.LoginSuccess(password_login.getText())){
                            login_respond.setText("Login successfully");
                            frame.dispose();
                            Site(p);
                        }
                        else{
                            login_respond.setText("Invalid Profilename or Password");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    login_respond.setText("Invalid profile name or password");
                }

            }
        });

        reg_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(!profilename_reg.getText().equals("") && !password_reg.getText().equals("")){
                    Profile p = null;
                    try {
                        p = new Profile(profilename_reg.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if(p.CheckProfileAvailability()){
                            reg_respond.setText("Successfully registrated");
                            p.CreateProfile(password_reg.getText());
                            frame.dispose();
                            Site(p);
                        }
                        else{
                            reg_respond.setText("The profilname is already exists");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    reg_respond.setText("Invalid profile name or password");
                }
            }
        });
    }


    public void Site(Profile profile) throws IOException {

        profile.LoadProfile();
        JFrame frame = new JFrame("Lexikon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(700, 700));

        JPanel feed = new JPanel();
        JPanel search = new JPanel();
        JPanel messages = new JPanel();
        JPanel settings = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("FEED",feed);
        tabbedPane.add("Search",search);
        tabbedPane.add("Messages",messages);
        tabbedPane.add("Settings",settings);
        frame.add(tabbedPane);
        frame.pack();
        frame.setVisible(true);


        //feed
        Feed f = new Feed();
        ArrayList<String> posts = new ArrayList(f.GetFeed(profile.GetName()));

        JTextField feed_newpost = new JTextField("", 20);
        JButton feed_post_button = new JButton("Post");
        feed_post_button.setFont(new Font("Arial", Font.BOLD, 15));
        feed_post_button.setBackground(Color.BLACK);
        feed_post_button.setForeground(Color.white);
        JLabel[] feed_posts_label = new JLabel[posts.size()];
        JPanel post_panel = new JPanel();
        post_panel.add(feed_newpost);
        post_panel.add(feed_post_button);
        post_panel.setBackground(Color.ORANGE);
        Box box = Box.createVerticalBox();
        box.add(post_panel);

        for (int i = 0; i < posts.size(); i++ ){
            feed_posts_label[i] = new JLabel(posts.get(i));
            box.add(feed_posts_label[i]);
        }
        feed.add(box);

        feed_post_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!feed_newpost.getText().equals("")){
                    try {
                        f.NewPost(profile.GetName(), feed_newpost.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                box.removeAll();
                box.add(post_panel);
                ArrayList<String> posts2 = null;
                try {
                    posts2 = new ArrayList(f.GetFeed(profile.GetName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabel[] feed_posts_label = new JLabel[posts2.size()];

                for (int i = 0; i < posts2.size(); i++ ){
                    feed_posts_label[i] = new JLabel(posts2.get(i));
                    box.add(feed_posts_label[i],BorderLayout.CENTER);
                }
                feed.revalidate();
            }
        });


        //search
        JTextField searchbar = new JTextField("",20);
        JComboBox<String> search_chose;
        String[] searchby = new String[]{"Name", "Country", "City"};
        search_chose = new JComboBox<String>(searchby);
        search_chose.setBackground(Color.BLACK);
        search_chose.setForeground(Color.white);

        JButton name_search_button = new JButton("Search");
        name_search_button.setFont(new Font("Arial", Font.BOLD, 15));
        name_search_button.setBackground(Color.BLACK);
        name_search_button.setForeground(Color.white);
        JPanel search_panel_name = new JPanel();
        search_panel_name.setBackground(Color.ORANGE);
        Box info_box = Box.createVerticalBox();
        Box search_box = Box.createVerticalBox();

        name_search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(search_chose.getSelectedItem().equals("Name")){
                    try {
                        info_box.removeAll();
                        if(!new Profile(searchbar.getText()).CheckProfileAvailability()){
                            Profile result = new Profile(searchbar.getText());
                            result.LoadProfile();
                            ArrayList<String> friends = profile.GetFriends();
                            boolean already_follow = false;
                            for(int i=0;i<friends.size();i++){
                                if(friends.get(i).equals(searchbar.getText()))
                                    already_follow = true;
                            }

                            if(!already_follow){

                                JButton follow_button = new JButton("Follow");
                                follow_button.setFont(new Font("Arial", Font.BOLD, 15));
                                follow_button.setBackground(Color.BLACK);
                                follow_button.setForeground(Color.white);

                                info_box.add(new JLabel("Name:  "+ result.GetName()));
                                if(result.GetBorn()!=0)
                                    info_box.add(new JLabel("Born:  "+ result.GetBorn()/10000 + " - " + result.GetBorn()%10000/100 + " - " + result.GetBorn()/1000000));
                                if(!String.valueOf(result.GetGender()).equals("$"))
                                    info_box.add(new JLabel("Gender:  " + String.valueOf(result.GetGender())));
                                JPanel address_panel = new JPanel();
                                address_panel.setBackground(Color.ORANGE);
                                ArrayList<String> address_temp = result.GetAddress();

                                if(!address_temp.get(0).equals("$"))
                                    address_panel.add(new JLabel("Country: " + address_temp.get(0)));
                                if(!address_temp.get(1).equals("$"))
                                    address_panel.add(new JLabel("City: " + address_temp.get(1)));
                                if(!address_temp.get(2).equals("$"))
                                    address_panel.add(new JLabel("ZIP: " + address_temp.get(2)));
                                if(!address_temp.get(3).equals("$"))
                                    address_panel.add(new JLabel("Street: " + address_temp.get(3)));
                                if(!address_temp.get(4).equals("$"))
                                    address_panel.add(new JLabel("House Number: " + address_temp.get(4)));

                                info_box.add(address_panel);
                                info_box.add(follow_button);
                                feed.revalidate();
                                follow_button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        try {
                                            profile.AddFriend(result.GetName());
                                            info_box.removeAll();
                                            info_box.add(new JLabel("Name:  "+ result.GetName()));
                                            if(result.GetBorn()!=0)
                                                info_box.add(new JLabel("Born:  "+ result.GetBorn()/10000 + " - " + result.GetBorn()%10000/100 + " - " + result.GetBorn()/1000000));
                                            if(!String.valueOf(result.GetGender()).equals("$"))
                                                info_box.add(new JLabel("Gender:  " + String.valueOf(result.GetGender())));
                                            JPanel address_panel = new JPanel();
                                            address_panel.setBackground(Color.ORANGE);
                                            ArrayList<String> address_temp = result.GetAddress();

                                            if(!address_temp.get(0).equals("$"))
                                                address_panel.add(new JLabel("Country: " + address_temp.get(0)));
                                            if(!address_temp.get(1).equals("$"))
                                                address_panel.add(new JLabel("City: " + address_temp.get(1)));
                                            if(!address_temp.get(2).equals("$"))
                                                address_panel.add(new JLabel("ZIP: " + address_temp.get(2)));
                                            if(!address_temp.get(3).equals("$"))
                                                address_panel.add(new JLabel("Street: " + address_temp.get(3)));
                                            if(!address_temp.get(4).equals("$"))
                                                address_panel.add(new JLabel("House Number: " + address_temp.get(4)));
                                            info_box.add(address_panel);
                                            info_box.add(new JLabel("You already follow her/him"));
                                            feed.revalidate();
                                            frame.revalidate();
                                            messages.revalidate();
                                            feed.revalidate();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }

                            else{
                                info_box.removeAll();
                                info_box.add(new JLabel("Name:  "+ result.GetName()));
                                if(result.GetBorn()!=0)
                                    info_box.add(new JLabel("Born:  "+ result.GetBorn()/10000 + " - " + result.GetBorn()%10000/100 + " - " + result.GetBorn()/1000000));
                                if(!String.valueOf(result.GetGender()).equals("$"))
                                    info_box.add(new JLabel("Gender:  " + String.valueOf(result.GetGender())));
                                JPanel address_panel = new JPanel();
                                address_panel.setBackground(Color.ORANGE);
                                ArrayList<String> address_temp = result.GetAddress();

                                if(!address_temp.get(0).equals("$"))
                                    address_panel.add(new JLabel("Country: " + address_temp.get(0)));
                                if(!address_temp.get(1).equals("$"))
                                    address_panel.add(new JLabel("City: " + address_temp.get(1)));
                                if(!address_temp.get(2).equals("$"))
                                    address_panel.add(new JLabel("ZIP: " + address_temp.get(2)));
                                if(!address_temp.get(3).equals("$"))
                                    address_panel.add(new JLabel("Street: " + address_temp.get(3)));
                                if(!address_temp.get(4).equals("$"))
                                    address_panel.add(new JLabel("House Number: " + address_temp.get(4)));

                                info_box.add(address_panel);
                                info_box.add(new JLabel("You already follow her/him"));
                            }
                            feed.revalidate();
                        }
                        else{
                            info_box.add(new JLabel("No profile found"));
                            feed.revalidate();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                else if(search_chose.getSelectedItem().equals("Country")){
                    info_box.removeAll();
                    try {
                        ArrayList<String> people = profile.SearchByCountryorCity(searchbar.getText(),true);
                        for(int i = 0; i < people.size(); i++){
                            info_box.add(new JLabel(people.get(i)));
                        }
                        if(people.size() == 0)
                            info_box.add(new JLabel("Nobody lives in "+searchbar.getText()));
                        else{
                            JTextField person_to_follow = new JTextField("",20);
                            JButton follow_button = new JButton("Follow");
                            follow_button.setFont(new Font("Arial", Font.BOLD, 15));
                            follow_button.setBackground(Color.BLACK);
                            follow_button.setForeground(Color.white);
                            info_box.add(person_to_follow);
                            info_box.add(follow_button);

                            follow_button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    for(int i = 0; i < people.size(); i++){
                                        if (people.get(i).equals(person_to_follow.getText())){
                                            try {
                                                profile.AddFriend(people.get(i));
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            info_box.add(new JLabel("You started to follow: " + person_to_follow.getText()));
                                            break;
                                        }
                                    }
                                    feed.revalidate();

                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    feed.revalidate();
                }
                else{
                    info_box.removeAll();
                    try {
                        ArrayList<String> people = profile.SearchByCountryorCity(searchbar.getText(),false);
                        for(int i = 0; i < people.size(); i++){
                            info_box.add(new JLabel(people.get(i)));
                        }
                        if(people.size() == 0)
                            info_box.add(new JLabel("Nobody lives in "+searchbar.getText()));
                        else{
                            JTextField person_to_follow = new JTextField("",20);
                            JButton follow_button = new JButton("Follow");
                            follow_button.setFont(new Font("Arial", Font.BOLD, 15));
                            follow_button.setBackground(Color.BLACK);
                            follow_button.setForeground(Color.white);
                            info_box.add(person_to_follow);
                            info_box.add(follow_button);

                            follow_button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    for(int i = 0; i < people.size(); i++){
                                        if (people.get(i).equals(person_to_follow.getText())){
                                            try {
                                                profile.AddFriend(people.get(i));
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            info_box.add(new JLabel("You started to follow: " + person_to_follow.getText()));
                                            break;
                                        }
                                    }
                                    feed.revalidate();

                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    feed.revalidate();
                }
            }
        });

        search_panel_name.add(searchbar);
        search_panel_name.add(search_chose);
        search_panel_name.add(name_search_button);
        search_box.add(search_panel_name);
        search_box.add(info_box);
        search.add(search_box);

        feed.setBackground(Color.ORANGE);
        messages.setBackground(Color.ORANGE);
        settings.setBackground(Color.ORANGE);
        search.setBackground(Color.ORANGE);
        tabbedPane.setBackground(Color.ORANGE);

        //Messenger
        ArrayList<String> friends = profile.GetFriends();
        JPanel messenger_panel = new JPanel();
        messenger_panel.setBackground(Color.ORANGE);
        Box messenger_box = Box.createVerticalBox();
        Box messenger_respond = Box.createVerticalBox();
        JTextField chat_with = new JTextField("",20);
        JButton chat_open_button = new JButton("Open chat");
        chat_open_button.setFont(new Font("Arial", Font.BOLD, 15));
        chat_open_button.setBackground(Color.BLACK);
        chat_open_button.setForeground(Color.white);

        messenger_box.add(new JLabel("People you follow: "));
        for(int i = 0; i< friends.size();i++){
            messenger_box.add(new JLabel(friends.get(i)));
        }

        chat_open_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                messenger_respond.removeAll();
                try {

                    Profile temp = new Profile(chat_with.getText());
                    if(!temp.CheckProfileAvailability())
                        Messenger(profile.GetName(),chat_with.getText());
                    else
                        messenger_respond.add(new JLabel("No profile found, try another one"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                messages.revalidate();
            }
        });

        messenger_panel.add(chat_with);
        messenger_panel.add(chat_open_button);
        messenger_box.add(messenger_panel);
        messenger_box.add(messenger_respond);
        messages.add(messenger_box);

        //settings
        JTextField settings_name_modify = new JTextField(profile.GetName(),10);
        JTextField settings_born_modify = new JTextField(String.valueOf(profile.GetBorn()),20);
        JTextField settings_gender_modify = new JTextField(String.valueOf(profile.GetGender()),20);
        JTextField settings_country_modify = new JTextField(profile.GetAddress().get(0),10);
        JTextField settings_city_modify = new JTextField(profile.GetAddress().get(1),10);
        JTextField settings_zip_modify = new JTextField(profile.GetAddress().get(2),10);
        JTextField settings_street_modify = new JTextField(profile.GetAddress().get(3),10);
        JTextField settings_housenum_modify = new JTextField(profile.GetAddress().get(4),10);

        JButton settings_apply = new JButton("Apply");
        settings_apply.setFont(new Font("Arial", Font.BOLD, 15));
        settings_apply.setBackground(Color.BLACK);
        settings_apply.setForeground(Color.white);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        p1.setBackground(Color.ORANGE);
        p2.setBackground(Color.ORANGE);
        p3.setBackground(Color.ORANGE);
        p4.setBackground(Color.ORANGE);

        p1.add(new JLabel("Name: "));
        p1.add(settings_name_modify);
        p2.add(new JLabel("Born Date: "));
        p2.add(settings_born_modify);
        p3.add(new JLabel("Gender: "));
        p3.add(settings_gender_modify);
        p4.add(new JLabel("Address: "));
        p4.add(settings_country_modify);
        p4.add(settings_city_modify);
        p4.add(settings_zip_modify);
        p4.add(settings_street_modify);
        p4.add(settings_housenum_modify);

        Box box_settings = Box.createVerticalBox();
        Box box_setting_responde = Box.createVerticalBox();
        add(box_settings);

        box_settings.add(p1);
        box_settings.add(p2);
        box_settings.add(p3);
        box_settings.add(p4);
        box_settings.add(settings_apply);


        JButton logout = new JButton("Log Out");
        logout.setFont(new Font("Arial", Font.BOLD, 15));
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.white);
        box_settings.add(logout, BorderLayout.CENTER);

        BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\Yorick Kramer\\Desktop\\EGYETEM\\3. felev\\prog3\\PofaLexikon\\design\\head.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        box_settings.add(picLabel, BorderLayout.CENTER);

        settings_apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                box_setting_responde.removeAll();
                try {
                    if(!settings_name_modify.getText().equals("") && !settings_name_modify.getText().equals(profile.GetName()) && new Profile(settings_name_modify.getText()).CheckProfileAvailability()){
                        profile.SetName(settings_name_modify.getText());
                        box_setting_responde.add(new JLabel("The name have been modified"));
                    }

                    else{
                        box_setting_responde.add(new JLabel("The name have not been modified"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(!settings_born_modify.getText().equals(profile.GetBorn()) && !settings_born_modify.getText().equals("")) {
                    try {
                        profile.SetBorn(Integer.parseInt(settings_born_modify.getText()));
                        box_setting_responde.add(new JLabel("The date of born have been modified"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    box_setting_responde.add(new JLabel("The date of born not have been modified"));

                if(!settings_gender_modify.getText().equals(profile.GetGender()) && (settings_gender_modify.getText().equals("women") || settings_gender_modify.getText().equals("men"))){
                    try {
                        profile.SetGender(settings_gender_modify.getText());
                        box_setting_responde.add(new JLabel("The gender have been modified"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    box_setting_responde.add(new JLabel("The gender have not been modified"));

                ArrayList <String> address_list = profile.GetAddress();

                if(!settings_country_modify.getText().equals(address_list.get(0)) && !settings_country_modify.getText().equals("")) {
                    try {
                        profile.SetAddress_Country(settings_country_modify.getText());
                        box_setting_responde.add(new JLabel("Country have been modified"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                box_setting_responde.add(new JLabel("Country have not been modified"));

                if(!settings_city_modify.getText().equals(address_list.get(1)) && !settings_city_modify.getText().equals("")) {
                    try {
                        profile.SetAddress_City(settings_city_modify.getText());
                        box_setting_responde.add(new JLabel("City have been modified"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                    box_setting_responde.add(new JLabel("City have not been modified"));

                if(!settings_zip_modify.getText().equals(address_list.get(2)) && !settings_zip_modify.getText().equals("")) {
                    try {
                        profile.SetAddress_ZIP(settings_zip_modify.getText());
                        box_setting_responde.add(new JLabel("ZIP code have been modified"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                    box_setting_responde.add(new JLabel("ZIP code have not been modified"));


                if(!settings_street_modify.getText().equals(address_list.get(3)) && !settings_street_modify.getText().equals("")) {
                    try {
                        profile.SetAddress_Street(settings_street_modify.getText());
                        box_setting_responde.add(new JLabel("Street have been modified"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                    box_setting_responde.add(new JLabel("Street have not been modified"));

                if(!settings_housenum_modify.getText().equals(address_list.get(4)) && !settings_housenum_modify.getText().equals("")) {
                    try {
                        profile.SetAddress_Number(settings_housenum_modify.getText());
                        box_setting_responde.add(new JLabel("House number have been modified"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                    box_setting_responde.add(new JLabel("House number have not been modified"));


                settings.revalidate();
                frame.revalidate();
            }

        });


        logout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                frame.dispose();
                try {
                    Login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        });

        settings.add(box_settings);
        settings.add(box_setting_responde);
    }


    public void Messenger(String me, String friend) throws IOException {
        JFrame frame = new JFrame("Messenger");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setResizable(false);
        frame.setBackground(Color.ORANGE);


        JTextField message_field = new JTextField("", 30);
        JButton return_button = new JButton("return");
        return_button.setFont(new Font("Arial", Font.BOLD, 15));
        return_button.setBackground(Color.BLACK);
        return_button.setForeground(Color.white);
        JButton send_button = new JButton("Send");
        send_button.setFont(new Font("Arial", Font.BOLD, 15));
        send_button.setBackground(Color.BLACK);
        send_button.setForeground(Color.white);
        JPanel messenger_panel =  new JPanel();
        messenger_panel.setBackground(Color.ORANGE);
        messenger_panel.add(message_field,BorderLayout.SOUTH);
        messenger_panel.add(send_button,BorderLayout.SOUTH);

        Box box = Box.createVerticalBox();
        Messenger m = new Messenger();
        ArrayList<String> chat = m.GetChat(me,friend);
        box.setBackground(Color.ORANGE);
        box.add(new JLabel("Chat with "+ friend));
        box.add(new JLabel(" "));

        for(int i= 0; i< chat.size();i++){
            if(chat.get(i).charAt(0) == '$'){
                box.add(new JLabel(friend+"  :  "+ chat.get(i).substring(1)), BorderLayout.WEST);
            }
            else{
                box.add(new JLabel("Me  :  "+ chat.get(i)), BorderLayout.EAST);
            }
        }
        box.add(messenger_panel);
        box.add(return_button);

        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

        send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    m.NewMessage(me, friend, message_field.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                box.removeAll();
                ArrayList<String> chat = null;
                try {
                    chat = m.GetChat(me,friend);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                box.add(new JLabel("Chat with "+ friend));
                box.add(new JLabel(" "));

                for(int i= 0; i< chat.size();i++){
                    if(chat.get(i).charAt(0) == '$'){
                        String valasz = chat.get(i);
                        valasz = valasz.substring(1);
                        box.add(new JLabel(friend+"  :  "+ valasz), BorderLayout.WEST);
                    }
                    else{
                        box.add(new JLabel("Me  :  "+ chat.get(i)), BorderLayout.EAST);
                    }
                }
                box.add(messenger_panel);
                box.add(return_button);
                frame.revalidate();
            }
        });
        frame.add(box);
        frame.pack();
        frame.setVisible(true);
    }
}
