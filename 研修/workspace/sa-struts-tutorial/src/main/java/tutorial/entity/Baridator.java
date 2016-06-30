package tutorial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "baridators")
public class Baridator {

    @Id
    @GeneratedValue
    @Column
    public Integer id;

    @Column
    public String image;

    @Column
    public String name;

    @Column
    public String question1;

    @Column
    public String question2;

    @Column
    public String question3;

    @Column
    public String question4;
}