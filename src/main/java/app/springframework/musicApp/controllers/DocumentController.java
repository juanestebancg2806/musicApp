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

        return this.documentService.getDocuments();

    }
    @GetMapping("/documents/names")
    public List<String> getDocumentsNames(){

        return  this.documentService.getNames();

    }
    @PostMapping(value = "/documents",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Document createDocument(@RequestBody Document document){
        return this.documentService.add(document);
    }

    @PutMapping(value="/documents/{id}",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public Document updateDocument(@RequestBody Document document,@PathVariable Long id){//json that contains 2 strings

        return this.documentService.updateById(document,id);
    }

    @DeleteMapping(value = "/documents/{id}")
    public void deleteDocument(@PathVariable Long id){
        this.documentService.deleteById(id);

    }

}
