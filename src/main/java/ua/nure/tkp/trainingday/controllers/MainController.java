package ua.nure.tkp.trainingday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.nure.tkp.trainingday.entity.Program;
import ua.nure.tkp.trainingday.service.ProgramService;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private final ProgramService programService;

    @Autowired
    public MainController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(value = {"/", "/home"})
    public String main() {
        return "index";
    }

    @GetMapping(value = "/catalog")
    public String catalog(Model model) {
        Iterable<Program> catalog = programService.getByStatus("ACCEPTED");
        model.addAttribute("programs", catalog);
        return "programs";
    }

    @GetMapping(value = "/suggest_program")
    @PreAuthorize("hasAuthority('read')")
    public String suggestProgram() {
        return "suggestProgram";
    }

    @PostMapping(value = "/suggest_program")
    @PreAuthorize("hasAuthority('read')")
    public String createProgram(@RequestParam String name, @RequestParam String description,
                                @RequestParam Integer duration, @RequestParam String muscleGroup) {
        programService.createUsersProgram(name, duration, muscleGroup, description);
        return "redirect:/home";
    }

    @GetMapping(value = "/catalog/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String programInfo(@PathVariable(value = "id") Integer id, Model model) {
        Program program = programService.getInfoAboutProgram(id);
        model.addAttribute("name", program.getName());
        model.addAttribute("program", program);
        return "details";
    }

}
