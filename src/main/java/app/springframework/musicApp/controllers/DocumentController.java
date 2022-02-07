package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.repositories.DocumentRepository;
import app.springframework.musicApp.service.DocumentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public List<Document> getDocuments(){
        //model.addAttribute("genres",this.genreRepository.findAll());
        return this.documentService.getDocuments();

    }
    @GetMapping("/documents/names")
    public List<String> getDocumentsNames(){
        //model.addAttribute("countries",this.countryRepository.findAll());
        return  this.documentService.getNames();

    }
    @PostMapping(value = "/documents/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGenre(@RequestBody Document document){
        //System.out.println(country.getName()+" ----name");
        this.documentService.add(document);
    }
    @PostMapping(value="/documents/update",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public void updateGenreJson(@RequestBody Map<String,String> json){//json that contains 2 strings
        this.documentService.updateByName(json.get("name"),json.get("newName"));
    }

    @PostMapping(value = "/documents/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGenre(@RequestBody Map<String,String> json){
        this.documentService.deleteByName(json.get("name"));
    }

}
