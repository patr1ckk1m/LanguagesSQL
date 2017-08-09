package com.patrick.languagessql.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.patrick.languagessql.models.Language;
import com.patrick.languagessql.services.LanguageService;

@Controller
public class Languages {

		private final LanguageService languageService;
		
		public Languages(LanguageService languageService) {
			this.languageService = languageService;
		}
		@RequestMapping("/languages")
		public String languages(@ModelAttribute("language") Language language, Model model, @ModelAttribute("errors") String errors) {
			List<Language> languages = languageService.allLanguages();
			model.addAttribute("errors", errors);
			model.addAttribute("languages", languages);
			return "index.jsp";
		}
	    @PostMapping("/languages/new")
	    public String createLanguage(@Valid @ModelAttribute("language") Language language, BindingResult result, RedirectAttributes redirectAttributes) {
	        if (result.hasErrors()) {
	        	redirectAttributes.addFlashAttribute("errors", "You have errors");
	            return "redirect:/languages";
	        }else{
	        	languageService.addLanguage(language);
	            return "redirect:/languages";
	        }
	    }
	    @RequestMapping("/languages/{id}")
	    public String findLanguageById(Model model, @PathVariable("id") Long id) {
	        Language language = languageService.findLanguageById(id);
	        model.addAttribute("language", language);
	        return "showLanguage.jsp";
	    }
	    
	    @RequestMapping("/languages/edit/{id}")
	    public String editLanguage(@PathVariable("id") Long id, Model model) {
	        Language language = languageService.findLanguageById(id);
	        if (language != null){
	            model.addAttribute("language", language);
	            return "editPage.jsp";
	        }else{
	            return "redirect:/languages";
	        }
	    }
	    
	    @PostMapping("/languages/edit/{id}")
	    public String editLanguage(@Valid @ModelAttribute("language") Language language, BindingResult result, @PathVariable("id") int id) {
	        if (result.hasErrors()) {
	            return "editLanguage.jsp";
	        }else{
	            languageService.editLanguage(language);
	            return "redirect:/languages";
	        }
	    }
	    
	    @RequestMapping("/languages/delete/{id}")
	    public String destroyLanguage(@PathVariable("id") Long id) {
	        languageService.destroyLanguage(id);
	        return "redirect:/languages";
	    }
}
