package br.com.fiap.basepet.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pet")
public class PetController {
    
    @Autowired
    PetService service;

    @Autowired
    MessageSource message;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("pets", service.findAll());
        return "pet/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", message.getMessage("pet.delete.success", null, LocaleContextHolder.getLocale()));
        }else{
            redirect.addFlashAttribute("error", "Tarefa não encontrada");
        }
        return "redirect:/pet";
    }

    @GetMapping("new")
    public String form(Pet pet, Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        return "pet/form";
    }



    @PostMapping
    public String create(@Valid Pet pet, BindingResult binding, RedirectAttributes redirect){
        if (binding.hasErrors()) return "/pet/form";

        service.save(pet);
        redirect.addFlashAttribute("success", "Tarefa cadastrada com sucesso");
        return "redirect:/pet";
    }
}
