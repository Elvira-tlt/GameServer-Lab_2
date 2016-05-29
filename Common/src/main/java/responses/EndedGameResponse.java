package responses;
import move.TypeValueCurrentStateGame;
import user.User;

public class EndedGameResponse implements Action {
	private TypeValueCurrentStateGame typeValueCurrentStateGame;

    private String nameWinUser;

	public EndedGameResponse(TypeValueCurrentStateGame typeValueCurrentStateGame) {
        this.typeValueCurrentStateGame = typeValueCurrentStateGame;
    }

    public void setWinUser(String nameWinUser) {
    	this.nameWinUser = nameWinUser;
    }


    public String getNameWinUser() {
        return nameWinUser;
    }

    public TypeValueCurrentStateGame getTypeValueCurrentStateGame() {
    	return typeValueCurrentStateGame;
    }

    @Override
    public String toString() {
        return "EndedGameResponse{" +
                "typeValueCurrentStateGame=" + typeValueCurrentStateGame +
                ", nameWinUser='" + nameWinUser + '\'' +
                '}';
    }
}
