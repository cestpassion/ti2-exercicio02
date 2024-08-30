package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Music;

public class MusicDAO {
    private Connection connect;

    public MusicDAO() {
        connect = null;
    }

    public boolean connect() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "exer02ti2";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "cestpassion";
        String password = "furt4d00";
        boolean status = false;

        try {
            Class.forName(driverName);
            connect = DriverManager.getConnection(url, username, password);
            status = (connect != null);
            System.out.println("\nConexão efetuada com o PostgreSQL!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
                status = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean insertMusic(Music music) {
        boolean status = false;
        try {
            Statement st = connect.createStatement();
            st.executeUpdate("INSERT INTO music (code, name, artist, gender) "
                    + "VALUES (" + music.getCode() + ", '" + music.getName() + "', '"
                    + music.getArtist() + "', '" + music.getGender() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        System.out.println("\n" +getMusicByCode(music.getCode()) + "\n");
        
        return status;
    }

    public boolean updateMusic(Music music) {
        boolean status = false;
        try {
            Statement st = connect.createStatement();
            st.executeUpdate("UPDATE music SET name = '" + music.getName()
                    + "', artist = '" + music.getArtist() + "', gender = '"
                    + music.getGender() + "' WHERE code = " + music.getCode() + ";");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        System.out.println(getMusicByCode(music.getCode()));
        
        return status;
    }

    public boolean deleteMusic(int code) {
        boolean status = false;
        try {
            Statement st = connect.createStatement();
            st.executeUpdate("DELETE FROM music WHERE code = " + code + ";");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Music getMusicByCode(int code) {
        Music music = null;
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM music WHERE code = " + code + ";");

            if (rs.next()) {
                music = new Music(
                        rs.getInt("code"),
                        rs.getString("name"),
                        rs.getString("artist"),
                        rs.getString("gender")
                );
            }
            rs.close();
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return music;
    }

    public List<Music> getMusic() {
        List<Music> musicList = new ArrayList<>();
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM music;");

            while (rs.next()) {
                musicList.add(new Music(
                        rs.getInt("code"),
                        rs.getString("name"),
                        rs.getString("artist"),
                        rs.getString("gender")
                ));
            }
            rs.close();
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return musicList;
    }

    public int[] getCode() {
        List<Integer> codes = new ArrayList<>();
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT code FROM music;");

            while (rs.next()) {
                codes.add(rs.getInt("code"));
            }
            rs.close();
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        int[] codeArray = new int[codes.size()];
        for (int i = 0; i < codes.size(); i++) {
            codeArray[i] = codes.get(i);
        }
        return codeArray;
    }
}
