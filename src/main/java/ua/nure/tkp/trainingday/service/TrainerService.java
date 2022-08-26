package ua.nure.tkp.trainingday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.tkp.trainingday.entity.Trainer;
import ua.nure.tkp.trainingday.repository.TrainerRepo;

@Service("trainerService")
public class TrainerService {
    private final TrainerRepo trainerRepo;

    @Autowired
    public TrainerService(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    public Iterable<Trainer> getAll(){
        return trainerRepo.findAll();
    }


}
