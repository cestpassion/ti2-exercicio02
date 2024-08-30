package model;

import java.util.Random;

import dao.MusicDAO;

public class Music {
    private int code;
    private String name;
    private String artist;
    private String gender;

    private Random gerador = new Random();

    public Music() {
        this.code = 0;
        this.name = "";
        this.artist = "";
        this.gender = "";
    }

    public Music(String name, String artist, String gender) {
        setCode();
        this.name = name;
        this.artist = artist;
        this.gender = gender;
    }

    public Music(int code) {
        this.code = code;
    }

    public Music(int code, String name, String artist, String gender) {
        this.code = code;
        this.name = name;
        this.artist = artist;
        this.gender = gender;
    }

    public void setCode() {
        int newCode;
        gerador.setSeed(System.currentTimeMillis());

        do {
            newCode = gerador.nextInt(90000) + 10000; // Garante que o código tenha 5 dígitos
        } while (verificaRepeticao(newCode) == 1);

        this.code = newCode;
    }

    public int verificaRepeticao(int newCode) {
        MusicDAO musicDao = new MusicDAO();
        musicDao.connect();

        int[] codes = musicDao.getCode();
        musicDao.close();

        for (int i : codes) {
            if (newCode == i) {
                return 1;
            }
        }
        return 0;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Music [código: " + code + ", nome: " + name + ", artista: " + artist + ", gênero: " + gender + "]";
    }
}
