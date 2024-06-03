package com.exam.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.exam.course.entity.Coureur;
import com.exam.course.entity.Equipe;
import com.exam.course.entity.Etape;
import com.exam.course.repository.EtapeRepository;
import com.exam.course.service.CoureurService;
import com.exam.course.service.EquipeService;
import com.exam.course.service.EtapeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EquipeController {
    @Autowired 
    EquipeService equipeService;

    @Autowired
    EtapeService etapeService;

    @Autowired
    CoureurService coureurService;

    @GetMapping("equipe/login")
    public String EquipeLogin(String loginEquipe,String pwdEquipe,
                                HttpSession session){
        Equipe equipe = equipeService.findByLoginAndPwd(loginEquipe, pwdEquipe);
        if(equipe == null){
            return "redirect:/";
        }
        session.setAttribute("equipe", equipe);
        return "redirect:/equipe/home";
    }   

    @GetMapping("/equipe/home")
    public String EquipeHomePage(Model model,HttpSession session){
        List<Etape> listEtape = etapeService.getAllEtape();
        model.addAttribute("listEtape", listEtape);
        return "equipe_home";
    }

    @GetMapping("/equipe/etape/{idEtape}")
    public String EquipeEtapeAddCoureurPage(Model model,HttpSession session,
                                            @PathVariable(name = "idEtape") int idEtape){
        Equipe equipe = (Equipe) session.getAttribute("equipe");
        List<Coureur> listCoureurEtape = coureurService.getCoureurByEtapeByEquipe(idEtape,equipe.getIdEquipe().intValue());
        Etape etape = etapeService.findByIdEtape(idEtape);
        model.addAttribute("listCoureurEtape", listCoureurEtape);
        model.addAttribute("equipe", equipe);
        model.addAttribute("etape", etape);
        return "equipe_etape_coureur_form";
    }

    @GetMapping("/equipe/etape/affecter")    
    public String Affecter(Model model,HttpSession session,
                            int idEtape,int idCoureur){
        etapeService.insertEtapeCoureur( idEtape,  idCoureur);
        return "redirect:/equipe/etape/"+idEtape;
    }

}