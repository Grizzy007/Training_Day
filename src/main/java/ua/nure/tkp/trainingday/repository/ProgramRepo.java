package ua.nure.tkp.trainingday.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.nure.tkp.trainingday.entity.Program;

public interface ProgramRepo extends CrudRepository<Program, Integer> {
    Iterable<Program> findProgramsByStatusName(String s);
    Page<Program> findAll(Pageable pageable);
}
