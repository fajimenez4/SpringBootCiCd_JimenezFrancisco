package ec.edu.espe.springlab1.repository;

import ec.edu.espe.springlab1.domain.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {
    //Buscar un estudiante por email exacto
    boolean existsByEmail(@NotBlank @Size(max = 120) String email);

    //Paginación y búsqueda por nombre
    Page<Student> findByFullNameContainingIgnoreCase(String name, Pageable pageable);

}
