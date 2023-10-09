package gr.aueb.cf.schoolapppro.dto;

public class LoginDTO {

    private String Username;
    private String password;

    public LoginDTO (){}

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
