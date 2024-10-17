package br.com.g10.BEM.classes;import br.com.g10.BEM.student.StudentModel;import jakarta.persistence.*;import lombok.Data;import java.util.List;import java.util.UUID;import java.time.LocalDateTime;import jakarta.validation.constraints.NotBlank;import jakarta.validation.constraints.Size;import org.hibernate.annotations.CreationTimestamp;@Data@Entity@Table(name = "classes")public class ClassesModel {    @Id    @GeneratedValue(generator = "UUID")    private UUID id;    @NotBlank(message = "A turma precisa ter um nome")    @Size(min = 3, max = 50, message = "Nome da turma entre 3 a 50 caracteres")    @Column(unique = true, nullable  = false)    private String className;    @Column(nullable = true)    private String description;    @CreationTimestamp    private LocalDateTime createdAt;        @ManyToMany    @JoinTable(            name = "class_students",            joinColumns = @JoinColumn(name = "class_id"),            inverseJoinColumns = @JoinColumn(name = "student_cpf")    )    private List<StudentModel> students;}