package ua.nure.tkp.trainingday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.tkp.trainingday.entity.Program;
import ua.nure.tkp.trainingday.service.ProgramService;
import ua.nure.tkp.trainingday.service.TrainerService;

@Controller
public class AdminController {

    private final ProgramService programService;

    private final TrainerService trainerService;

    @Autowired
    public AdminController(ProgramService programService, TrainerService trainerService) {
        this.programService = programService;
        this.trainerService = trainerService;
    }

    @GetMapping(value = "/catalog/{id}/edit")
    @PreAuthorize("hasAuthority('write')")
    public String programEdit(@PathVariable(value = "id") Integer id, Model model) {
        Program program = programService.getInfoAboutProgram(id);
        model.addAttribute("name", program.getName());
        model.addAttribute("program", program);
        return "edit";
    }

    @PostMapping(value = "/catalog/{id}/edit")
    @PreAuthorize("hasAuthority('write')")
    public String editProgram(@PathVariable(value = "id") Integer id, @RequestParam String name,
                              @RequestParam Integer duration, @RequestParam String description, Model model) {
        programService.updateProgram(id, name, duration, description);
        return "redirect:/home";
    }

    @PostMapping(value = "/catalog/{id}/remove")
    @PreAuthorize("hasAuthority('write')")
    public String removeProgram(@PathVariable(value = "id") Integer id, Model model) {
        programService.deleteProgram(id);
        return "redirect:/catalog";
    }

    @GetMapping(value = "/verify")
    @PreAuthorize("hasAuthority('write')")
    public String programToVerify(Model model) {
        Iterable<Program> all = programService.getByStatus("NEW");
        model.addAttribute("programs", all);
        return "verify";
    }

    @GetMapping(value = "/add-program")
    @PreAuthorize("hasAuthority('write')")
    public String addProgramPage(Model model) {
        model.addAttribute("trainers", trainerService.getAll());
        model.addAttribute("program", new Program());
        return "addProgram";
    }

    @PostMapping("/add-program")
    @PreAuthorize("hasAuthority('write')")
    public String processRegister(Program program) {
        programService.addNewProgram(program);
        return "redirect:/catalog";
    }

    @GetMapping(value = "/catalog/{id}/accept")
    @PreAuthorize("hasAuthority('write')")
    public String programAccept(@PathVariable(value = "id") Integer id, Model model) {
        Program prog = programService.getInfoAboutProgram(id);
        model.addAttribute("trainers", trainerService.getAll());
        model.addAttribute("program", prog);
        return "accept";
    }

    @PostMapping(value = "/catalog/{id}/accept")
    @PreAuthorize("hasAuthority('write')")
    public String acceptProgram(@PathVariable(value = "id") Integer id, Program program) {
        programService.changeStatus(program, "ACCEPTED", id);
        return "redirect:/verify";
    }

}
