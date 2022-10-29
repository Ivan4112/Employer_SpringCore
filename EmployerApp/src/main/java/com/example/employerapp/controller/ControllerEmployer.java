package com.example.employerapp.controller;

import com.example.employerapp.entities.Employer;
import com.example.employerapp.repository.EmployerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ControllerEmployer {
    EmployerRepository employerRepository;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/employers")
    public String showEmployer(Model model){
        List<Employer> employerList = employerRepository.findAll();
        model.addAttribute("hires", employerList);
        return "employers";
    }

    @GetMapping("/employers/addEmployer")
    public String pageAdd(){
        return "addEmployer";
    }

    @PostMapping("/employers/addEmployer")
    public String addEmployer(@RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam String patronimic, @RequestParam String phoneNumber,
                              @RequestParam String adressCompany) {

        Employer employer = new Employer();
        employer.setFirstName(firstName);
        employer.setLastName(lastName);
        employer.setPatronimic(patronimic);
        employer.setPhoneNumber(phoneNumber);
        employer.setAdressCompany(adressCompany);
        employerRepository.save(employer);
        return "redirect:/employers/addEmployer";
    }

    @GetMapping("/delete_employer/{id}")
    public String deleteEmployer(@PathVariable("id") int id){
        employerRepository.deleteById(id);
        return "redirect:/employers";
    }

    @GetMapping("/employers/my_workers/{id}")
    public String viewWorkersByEmployer(@PathVariable("id") int id, Model model){
        Optional<Employer> employer = employerRepository.findById(id);
        model.addAttribute("empl",employer.get());
        return "my_workers";
    }

    @GetMapping("/employers/search_employers")
    public String searchEmpl(){
        return "search_employers";
    }

    @PostMapping("/employers/search_employers")
    public String findEmployer(String lastName, Model model) {
        List<Employer> employer = employerRepository.findByLastName(lastName);

        if(employer.isEmpty()){
            model.addAttribute("message_search",  "employer with last name " + lastName + " doesn't exist");
            return "error_emp";
        }else {
            model.addAttribute("srcEmp",employer);
            return "search_employers";
        }

    }
}
