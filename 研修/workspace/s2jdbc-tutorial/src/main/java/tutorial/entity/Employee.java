package tutorial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    @Column
    public Integer id;

    @Column
    public String name;

    @Column
    public Integer salary;

    @Column(name = "department_id")
    public Integer departmentId;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    public Department department;

    @Column(name = "address_id")
    public Integer addressId;

    @OneToOne
    public Address address;

    @Version
    public Integer version;
}