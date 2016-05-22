package user;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable{
    private String name;
    private String password;
    private int idUser;
    private boolean statusPlayer = false;

    public User(int idUser){
        this.idUser = idUser;
    }

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

    public int getidUser() {
        return idUser;
    }

    public void setStatusPlayer() {
        this.statusPlayer= true;
    }

    public boolean getStatusUser() {
        return statusPlayer;
    }

    @Override
    public int hashCode() {
        int prime = 1111;
        Random random = new Random(prime);
        int randomInt = random.nextInt();
        prime = idUser * prime + randomInt;
        return prime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name.equals(other.name))
            return true;
        if (password.equals(other.password))
            return true;
        if (idUser == other.idUser)
            return true;
        return false;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", idUser=" + idUser +
                ", statusPlayer=" + statusPlayer +
                '}';
    }
}