package com.example.library.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import com.example.library.entity.Reader;

@Data
public class RegistatrationRequest {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

     
    private String firstName;

    
    private String lastName;



    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() {return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Reader toAccount(){
        Reader reader = new Reader();
        reader.setLogin(login);
        reader.setPassword(password);
        reader.setFirstName(firstName);
        reader.setLastName(lastName);

        return reader;
    }

}
