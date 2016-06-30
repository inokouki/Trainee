package tutorial.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue
    public Integer id;

    public String name;

    @OneToMany(mappedBy = "department")
    public List<Employee> employeeList;

    @Version
    public Integer version;
}