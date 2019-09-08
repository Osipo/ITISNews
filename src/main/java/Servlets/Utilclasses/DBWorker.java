package Servlets.Utilclasses;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.sql.DriverManager.registerDriver;

import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.*;
import static java.sql.DriverManager.registerDriver;

/**
 * Created by Олег on 17.10.2016.
 */
public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/users?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection getConnection() throws SQLException {
        Properties connInfo = new Properties();
        connInfo.put("user", USERNAME);
        connInfo.put("password", PASSWORD);
        connInfo.put("useUnicode", "true"); // (1)
        connInfo.put("charSet", "UTF-8");

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /*Get user Id.*/
    public static int getUserId(String loginField, String passwordField) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        boolean b = false;
        int id = 0;
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT email,password FROM users.user2_1");
            M1:
            while (rs.next()) {
                if (loginField.equals(rs.getString("email"))) {
                    if (passwordField.equals(rs.getString("password"))) {
                        b = true;
                        break M1;
                    }
                }
            }
            if (b) {
                preparedStatement = connection.prepareStatement("SELECT u_id,email FROM users.user2 WHERE email IN (?)");
                preparedStatement.setString(1, loginField);
                rs = preparedStatement.executeQuery();
                while (rs.next())
                    id = rs.getInt("u_id");
            }
            connection.close();
        } catch (SQLException selectExp) {
        }
        return id;
    }

    public static boolean corlogin(String login) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT email FROM users.user2_1");
            while (rs.next()) {
                if (!login.equals(rs.getString("email"))) {
                    correct = true;
                } else {
                    correct = false;
                    System.out.println("User with this login is already exists!");
                    return correct;
                }
            }
            connection.close();
        } catch (SQLException selectExp) {
        }
        return correct;
    }

    public static boolean corpassword(String password) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT password FROM users.user2_1");
            while (rs.next()) {
                if (!password.equals(rs.getString("password"))) {
                    correct = true;
                } else {
                    correct = false;
                    System.out.println("User with this password is already exists!");
                    return correct;
                }
            }
            connection.close();
        } catch (SQLException selectExp) {
        }
        return correct;
    }

    /*Check,that new login and password are uniq.*/
    public static boolean correg(String login, String pass) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT email,password FROM users.user2_1");
            while (rs.next()) {
                if (!login.equals(rs.getString("email"))) {
                    if (!pass.equals(rs.getString("password"))) {
                        correct = true;
                    } else {
                        correct = false;
                        System.out.println("User with this password is already exists!");
                        return correct;
                    }
                } else {
                    correct = false;
                    System.out.println("User with this login is already exists!");
                    return correct;
                }
            }
            connection.close();
        } catch (SQLException selectExp) {
        }
        return correct;
    }

    /*Registration.*/
    public static boolean regUser(String login, String pass, String nick) {
        Pattern p = Pattern.compile("^[A-Za-z0-9_]{1,30}@[A-Za-z]{1,10}.(ru|com|ua|eu)$");
        Pattern p2 = Pattern.compile("^[A-Za-z0-9]{1,80}$");
        Pattern p3 = Pattern.compile("^[A-Za-z0-9_]{1,50}$");
        Matcher m = p.matcher(login);
        Matcher m2 = p2.matcher(pass);
        Matcher m3 = p3.matcher(nick);
        Boolean correct = correg(login, pass);
        //If user fed in valid data.
        if (m.matches() && m2.matches() && m3.matches() && correct) {
            Connection connection = null;
            String out = null;
            try {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users.user2_1 (email, password,nickname) VALUES(?,?,?)");
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, nick);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("INSERT INTO users.user2 (email) VALUES (?)");
                preparedStatement.setString(1, login);
                preparedStatement.executeUpdate();
                connection.close();
                return true;
                //not protected from SQL-Injection!
                //Statement statement = connection.createStatement();
                //statement.executeUpdate("INSERT INTO users.user VALUES ('"+login+"','"+pass+"')");
            } catch (SQLException insertExp) {
            }
        }
        return false;
    }

    /*Correct, that new title is uniq*/
    public static boolean corlog(String t) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT title FROM users.userlog");
            while (rs.next()) {
                if (!t.equals(rs.getString("title"))) {
                    correct = true;
                } else {
                    correct = false;
                    System.out.println("The new with this title is already exist.");
                    break;
                }
            }
            connection.close();
        } catch (SQLException insertExp) {
        }
        return correct;
    }


    public static boolean deleteUser(String email) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Pattern p = Pattern.compile("^[A-Za-z0-9_]{1,30}@[A-Za-z]{1,10}.(ru|com|ua|eu)$");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            //if login is exists.
            if (!corlogin(email)&&!email.equals("oleg_o97@mail.ru")) {
                try {
                    registerDriver(new com.mysql.jdbc.Driver());
                    connection = getConnection();
                    preparedStatement = connection.prepareStatement("DELETE FROM users.user2_1 WHERE email='" + email + "' ");
                    preparedStatement.executeUpdate();
                    correct = true;
                    connection.close();
                } catch (SQLException insertExp) {
                }
            }
        } else {
            //if title of new is exists.
            if (!corlog(email)) {
                try {
                    registerDriver(new com.mysql.jdbc.Driver());
                    connection = getConnection();
                    preparedStatement = connection.prepareStatement("DELETE  FROM users.userlog WHERE title='" + email + "' ");
                    preparedStatement.executeUpdate();
                    correct = true;
                    connection.close();
                } catch (SQLException insertExp) {
                }
            }
        }
        return correct;
    }
    /*Select News for admin*/
    public static boolean SelectNew(String Title, ArrayList<String> titl, ArrayList<Integer> uid) {
        Boolean correct = false;
        Connection connection = null;
        try {
            if (Title.equals("*")) {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users.userlog");
                while (rs.next()) {
                    uid.add(rs.getInt("u_id"));
                    titl.add(rs.getString("title"));
                }
                correct = true;
                connection.close();
            }
            else if(!corlog(Title)){
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users.userlog WHERE title='"+Title+"'");
                while (rs.next()) {
                    uid.add(rs.getInt("u_id"));
                    titl.add(rs.getString("title"));
                }
                correct=true;
                connection.close();
            }
        } catch (SQLException e) {
        }
        return correct;
    }
    /*SELECT NEWS OF CURRENT(Authorized) USER.*/
    public static boolean SelectNewById(int Uid,String Title, ArrayList<String> titl,ArrayList<Integer> uid){
        Boolean correct = false;
        Connection connection = null;
        try {
            if(Title.equals("*")) {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users.userlog WHERE u_id='" + Uid + "'");
                while (rs.next()) {
                    uid.add(rs.getInt("u_id"));
                    titl.add(rs.getString("title"));
                }
                correct=true;
                connection.close();
            }
            else if(!corlog(Title)){
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users.userlog WHERE u_id='" + Uid + "' AND title='" + Title + "'");
                while (rs.next()) {
                    uid.add(rs.getInt("u_id"));
                    titl.add(rs.getString("title"));
                }
                correct=true;
                connection.close();
            }
        }catch (SQLException e){}
        return correct;
    }

    public static boolean DeleteUserNew(String Title,int u_id){
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(!corlog(Title)) {
            try {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM users.userlog WHERE u_id='"+u_id+"' AND title='"+Title+"' ");
                preparedStatement.executeUpdate();
                correct = true;
                connection.close();
            } catch (SQLException e) {
            }
        }
        return correct;
    }

    public static boolean IsItYours(String Title,int UID){
        Boolean correct = false;
        Connection connection = null;
            try {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement("SELECT u_id,title FROM users.userlog WHERE u_id='"+UID+"'AND title='"+Title+"'");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    correct=true;
                }
                connection.close();
            }catch (SQLException e){}
        return correct;
    }

    public static boolean UpdateNew(String Title, int UID,String NewContent,String NewDescription) {
        Boolean correct = false;
        Connection connection = null;
        String b ="";
        //If exists.
        if (IsItYours(Title,UID)) {
            try {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT describ FROM users.userlog_2 WHERE title='"+Title+"'");
                ResultSet sr = preparedStatement.executeQuery();
                while(sr.next()){
                    b = sr.getString("title");
                }
                b+=NewDescription;
                preparedStatement = connection.prepareStatement("UPDATE users.userlog_2 SET content='"+NewContent+"', describ='"+b+"'");
                preparedStatement.executeUpdate();
                correct=true;
                connection.close();
            } catch (SQLException e) {
            }
        }
        return correct;
    }
    /*Create news.*/
    public static boolean addLog(String t,String c,String d, Object uid) {
        Connection connection = null;
        String out = null;
        int ui = (Integer) uid;
        if(corlog(t)) {
            try {
                registerDriver(new com.mysql.jdbc.Driver());
                connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users.userlog (u_id,title) VALUES(?,?)");
                preparedStatement.setInt(1, ui);
                preparedStatement.setString(2, t);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("INSERT INTO users.userlog_2 VALUES (?,?,?)");
                preparedStatement.setString(1, t);
                preparedStatement.setString(2, c);
                preparedStatement.setString(3, d);
                preparedStatement.executeUpdate();
                connection.close();
                return true;
            } catch (SQLException insertExp) {
            }
        }
        return false;
    }
    /*Find list of strings.*/
    public static ArrayList<String> dBFind(String sql, String select) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        ArrayList<String> results = new ArrayList<String>();
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                results.add(rs.getString(select));
            }
            connection.close();
        } catch (SQLException selectException) {
        }
        return results;
    }
    /*Find list of numbers.*/
    public static  ArrayList<Integer> dBFindco(String sql, String select) {
        Boolean correct = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String out = null;
        ArrayList<Integer> results = new ArrayList<Integer>();
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                results.add(rs.getInt(select));
            }
            connection.close();
        } catch (SQLException selectException) {
        }
        return results;
    }
    /*Searching, which contains our string.*/
    public static ArrayList<String> search(char[] ars) {
        ArrayList<String> ans = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            registerDriver(new com.mysql.jdbc.Driver());
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT title FROM users.userlog");
            while (rs.next()) {
                char[] ex = rs.getString("title").toCharArray();
                boolean b = false;
                for (int j = 0, k = 0; j < ars.length && k < ex.length && j < ex.length; j++, k++) {
                    if (ars[j] == ex[k]) {
                        b = true;
                    } else{ b = false; break;}
                }
                if (b) ans.add(rs.getString("title"));
            }
            connection.close();
        }catch (SQLException selectException){}
        return ans;
    }
}
