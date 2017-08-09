package com.patrick.languagessql.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
