package user;

import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String password; //������� ���������������
    private int idUser;
    private boolean statusPlayer = false;


    public void setNameUser(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return name;
    }

    public void setPasswordUser(String password) {
        this.password = password;
    }

    public String getPassword() {
        String passwordUser = password;
        return passwordUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getidUser() {
        return idUser;
    }

    public void setStatusPlayer() {
        this.statusPlayer= true;
    }

    public boolean getStatusUser() {
        return statusPlayer;
    }
}
