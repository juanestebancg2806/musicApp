package app.springframework.musicApp.controllers;

import app.springframework.musicApp.repositories.DocumentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentController {
    private final DocumentRepository documentRepository;

    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @RequestMapping("/documents")
    public String getDocuments(Model model){
        model.addAttribute("documents",this.documentRepository.findAll());
        return "documents/index";

    }
}
