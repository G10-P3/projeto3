package br.com.g10.BEM.student;

import br.com.g10.BEM.classes.ClassesModel;
import br.com.g10.BEM.user.UserModel;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class StudentModel {

    @Id
    @Column(name = "user_cpf", nullable = false)
    private String userCpf;

    @NotBlank(message = "O nome do aluno é obrigatório")
    private String name;

    @NotBlank(message = "O sobrenome do aluno é obrigatório")
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotBlank(message = "O nome do responsável é obrigatório")
    private String guardianName;

    private String financialName;

    private String phone;

    // Relação ManyToMany - um estudante pode pertencer a várias turmas
    @ManyToMany(mappedBy = "students")
    private List<ClassesModel> classes;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_cpf", referencedColumnName = "cpf", nullable = false)
    private UserModel user;
}
