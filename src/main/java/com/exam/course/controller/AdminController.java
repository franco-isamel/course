package com.exam.course.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.exam.course.entity.Admin;
import com.exam.course.entity.Coureur;
import com.exam.course.entity.Equipe;
import com.exam.course.entity.Etape;
import com.exam.course.repository.AdminRespotirory;
import com.exam.course.service.AdminService;
import com.exam.course.service.CoureurService;
import com.exam.course.service.EquipeService;
import com.exam.course.service.EtapeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    
    @Autowired
    EtapeService etapeService;

    @Autowired
    CoureurService coureurService;

    @GetMapping("/admin/{login}/{pwd}")
    public String AdminLog(HttpSession session,
                            @PathVariable(name = "login") String loginAdmin,
                            @PathVariable(name = "pwd") String pwdAdmin){
        Admin admin = adminService.findByLoginAndPwd( loginAdmin,  pwdAdmin);
        if(admin== null){
            return "redirect:/";
        }
        session.setAttribute("admin", admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/home")
    public String EquipeHomePage(Model model,HttpSession session){
        List<Etape> listEtape = etapeService.getAllEtape();
        model.addAttribute("listEtape", listEtape);
        return "admin_home";
    }

    @GetMapping("/admin/etape/{idEtape}")
    public String EquipeEtapeAddCoureurPage(Model model,HttpSession session,
                                            @PathVariable(name = "idEtape") int idEtape){
        List<Coureur> listCoureurEtape = coureurService.getCoureurByEtape(idEtape);
        Etape etape = etapeService.findByIdEtape(idEtape);
        model.addAttribute("listCoureurEtape", listCoureurEtape);
        model.addAttribute("etape", etape);
        return "admin_etape_form";
    }

    @GetMapping("/admin/etape/modifier")
    public String timeForm(Model model,HttpSession session,
                            int idEtape, int idCoureur){
        Coureur coureur = coureurService.findByIdCoureur(((Number) idCoureur).longValue());
        coureur.setIdCoureur(((Number) idCoureur).longValue());
        model.addAttribute("coureur", coureur);
        model.addAttribute("idEtape", idEtape);
        return "admin_coureur_time";
    }

    @GetMapping("/admin/coureur/insertTime")
    public String insertTime(Model model,HttpSession session,
                            int idEtape, @ModelAttribute Coureur coureur,
                            String depart,String arrivee){
        coureur.setDateArrivee(arrivee);
        coureur.setDateDepart(depart);
        coureurService.insertTime(coureur.getDateDepart(),coureur.getDateArrivee(), idEtape, coureur.getIdCoureur().intValue());
        return "redirect:/admin/etape/"+idEtape;
    }

    @GetMapping("/admin/classement/etape")
    public String classemntParEtape(Model model){
        List<Etape> listEtape = etapeService.getAllEtape();
        model.addAttribute("listEtape", listEtape);
        return"admin_classement_etape";
    }

    @ResponseBody
    @GetMapping("/admin/classement/etape/{idEtape}")
    public List<Object[]> classementParEtape(@PathVariable(name = "idEtape") int idEtape){
        return etapeService.getClassementParEtape(idEtape);
    }

    @GetMapping("/admin/classement/general")
    public String classemntGeneral(Model model){
        List<Object[]> listResult = etapeService.getClassementGenerle();
        model.addAttribute("listResult", listResult);
        return"admin_classement_general";
    }
    
}
