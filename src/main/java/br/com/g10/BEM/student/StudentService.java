package br.com.g10.BEM.student;

import br.com.g10.BEM.user.UserModel;
import br.com.g10.BEM.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;


    public StudentModel createStudent(StudentModel studentModel) {
        if (studentModel == null) {
            throw new IllegalArgumentException("O modelo de estudante não pode ser nulo.");
        }
        if (studentModel.getName() == null || studentModel.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do estudante não pode ser vazio.");
        }

        UserModel existingUser = userService.findByCpf(studentModel.getUser().getCpf())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com CPF " + studentModel.getUser().getCpf() + " não encontrado"));
        studentModel.setUser(existingUser);

        return studentRepository.save(studentModel);
    }

    // Lendo todos os estudantes
    public List<StudentModel> listAllStudents() {
        return studentRepository.findAll();
    }

    // Lendo estudante por CPF
    public StudentModel findStudentByCPF(String userCpf) {
        return studentRepository.findById(userCpf)
                .orElseThrow(() -> new EntityNotFoundException("Estudante com CPF " + userCpf + " não encontrado."));
    }

    // Atualizando estudante pelo CPF
    public StudentModel updateStudentByCPF(String userCpf, StudentModel studentModel) {
        if (!studentRepository.existsById(userCpf)) {
            throw new EntityNotFoundException("Estudante com CPF " + userCpf + " não existe.");
        }

        StudentModel toUpdateStudent = findStudentByCPF(userCpf);

        toUpdateStudent.setName(studentModel.getName());
        toUpdateStudent.setLastName(studentModel.getLastName());
        toUpdateStudent.setBirthDate(studentModel.getBirthDate());
        toUpdateStudent.setGuardianName(studentModel.getGuardianName());
        toUpdateStudent.setFinancialName(studentModel.getFinancialName());
        toUpdateStudent.setPhone(studentModel.getPhone());
        toUpdateStudent.setClasses(studentModel.getClasses());

        return studentRepository.save(toUpdateStudent);
    }

    // Deletando estudante pelo CPF
    public void deleteStudentByCPF(String userCpf) {
        if (!studentRepository.existsById(userCpf)) {
            throw new EntityNotFoundException("Estudante com CPF " + userCpf + " não encontrado.");
        }
        studentRepository.deleteById(userCpf);
    }
}
