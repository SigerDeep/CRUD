package web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="nickname")
    private String nickname;

    @Column(name="email")
    private String email;

    @Override
    public String toString(){
        return String.valueOf(this.id) + ": " + this.name + " " + this.surname + " " + this.nickname + " " + this.email;
    }

}
