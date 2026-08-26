package Model;

import java.time.LocalDate;

public class Propietario {
    private String name;
    private String lastname;
    private String identification;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String role;


    public Propietario(String name, String lastname, String identification, String phone, LocalDate birthdate, String email, String password) {
    this.name = name;
    this.lastname = lastname;
    this.identification = identification;
    this.phone = phone;
    this.birthdate = birthdate;
    this.email = email;
    this.password = password;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getIdentification() {return identification;}

    public void setIdentification() {this.identification = identification;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public LocalDate getBirthdate() {return birthdate;}

    public void setBirthdate(LocalDate birthdate) {this.birthdate = birthdate;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    @Override
    public String toString() {
        return "Propietario{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", identification=" + identification +
                ", phone=" + phone +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

