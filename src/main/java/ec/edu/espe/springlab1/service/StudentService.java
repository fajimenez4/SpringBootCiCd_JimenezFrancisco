package ec.edu.espe.springlab1.service;

import ec.edu.espe.springlab1.dto.StudentCreateRequest;
import ec.edu.espe.springlab1.dto.StudentResponse;
import ec.edu.espe.springlab1.dto.StudentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    //Crear un estudiante
    StudentResponse create(StudentCreateRequest request);

    //Buscar estudiante por ID
    StudentResponse getById(Long id);

    //Listar todos los estudiantes
    List<StudentResponse> list();

    //Cambiar el estado
    StudentResponse deactivate(Long id);

    Page<StudentResponse> list(String name, Pageable pageable);

    StudentResponse update(Long id, StudentUpdateRequest request);

}
