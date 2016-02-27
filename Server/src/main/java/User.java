
public class User {
    private String name;
    private int idUser;
    private boolean statusPlayer = false;


    public void setNameUser(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return name;
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
