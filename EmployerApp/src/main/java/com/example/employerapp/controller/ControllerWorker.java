package com.example.employerapp.controller;

import com.example.employerapp.entities.Worker;
import com.example.employerapp.repository.WorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ControllerWorker {
    WorkerRepository workerRepository;

    @GetMapping("/workers")
    public String showWorker(Model model){
        List<Worker> workerList = workerRepository.findAll();
        model.addAttribute("work", workerList);
        return "workers";
    }

    @GetMapping("/employers/my_workers/edit/{id}")
    public String updatePageWorker(@PathVariable("id") int id, Model model) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        model.addAttribute("edWork", workerOptional.get());
        return "edit";
    }

    @PostMapping("/edit/update_worker/{id}")
    public String updateWorker(@PathVariable("id") int id, Worker worker){
        Optional<Worker> upDateWorker = workerRepository.findById(id);
        Worker wor = upDateWorker.get();
        wor.setFirstName(worker.getFirstName());
        wor.setLastName(worker.getLastName());
        wor.setPatronimic(worker.getPatronimic());
        wor.setSalary(worker.getSalary());
        wor.setTotalEarn(worker.getTotalEarn());
        workerRepository.save(wor);
        return "redirect:/employers";
    }

    @GetMapping("/delete_workers/{id}")
    public String deleteWorker(@PathVariable("id") int id){
        workerRepository.deleteById(id);
        return "redirect:/employers";
    }

    @GetMapping("/workers/search_worker")
    public String searchWorker(){
        return "search_worker";
    }

    @PostMapping("/workers/search_worker")
    public String findWorker(String lastName, Model model) {
        List<Worker> worker = workerRepository.findByLastName(lastName);
        if(worker.isEmpty()){
            model.addAttribute("message",  "worker with last name " + lastName + " doesn't exist");
            return "error_worker";
        }else {
            model.addAttribute("srcWrk",worker);
        }
        return "search_worker";
    }
}