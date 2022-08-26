package ua.nure.tkp.trainingday.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.tkp.trainingday.entity.Program;
import ua.nure.tkp.trainingday.entity.dto.ProgramDto;
import ua.nure.tkp.trainingday.repository.ProgramRepo;

import java.util.Optional;

@Service("programService")
public class ProgramService {
    private final ProgramRepo programRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramService.class);

    @Autowired
    public ProgramService(ProgramRepo programRepo) {
        this.programRepo = programRepo;
    }

    public Program getInfoAboutProgram(Integer id) {
        Optional<Program> prog = programRepo.findById(id);
        if (prog.isPresent()) {
            return prog.get();
        }
        return new Program();
    }

    public Program createUsersProgram(String name, Integer duration, String muscleGroup, String description) {
        ProgramDto program = new ProgramDto(name, duration, muscleGroup, description);
        Program toVerify = new Program(
                program.getName(), program.getDuration(), program.getGroup(), null, program.getDescription());
        programRepo.save(toVerify);
        LOGGER.info("Users program added {}", toVerify.getName());
        return toVerify;
    }

    public Program addNewProgram(Program program) {
        Program entity = new Program(program.getName(), program.getDuration(), program.getGroup(), program.getTrainer(), program.getDescription());
        entity.setStatus("ACCEPTED");
        programRepo.save(entity);
        LOGGER.info("Program from trainer added {}", entity.getName());
        return entity;
    }

    public Iterable<Program> getByStatus(String status) {
        return programRepo.findProgramsByStatusName(status);
    }

    public void changeStatus(Program program, String status, Integer id) {
        Program prog = programRepo.findById(id).orElseThrow();
        prog.setTrainer(program.getTrainer());
        prog.setStatus(status);
        programRepo.save(prog);
    }

    public void updateProgram(Integer id, String name, Integer duration, String description) {
        Program program = programRepo.findById(id).orElseThrow();
        program.setName(name);
        program.setDuration(duration);
        program.setDescription(description);
        programRepo.save(program);
    }

    public void deleteProgram(Integer id) {
        Program program = programRepo.findById(id).orElseThrow();
        programRepo.delete(program);
        LOGGER.info("Program deleted {}", program.getName());
    }

}
