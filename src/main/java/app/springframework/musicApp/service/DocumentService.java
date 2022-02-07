package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getDocuments(){
        List<Document> documents = new ArrayList<>();
        this.documentRepository.findAll().forEach(documents::add);
        return  documents;
    }

    public List<String> getNames(){
        return  this.documentRepository.getNames();

    }

    public void add(Document document){
        this.documentRepository.save(document);
    }

    public void updateByName(String name,String newName){

        List<Document> documents = this.documentRepository.findByName(name);
        if(documents.size() > 0){
            Document d = documents.get(0);
            d.setName(newName);
            this.documentRepository.save(d);
            System.out.println("Document updated JSON body");
        }

    }

    public void deleteByName(String name){
        List<Document> documents = this.documentRepository.findByName(name);
        if(documents.size() > 0){
            Document d = documents.get(0);
            this.documentRepository.deleteById(d.getId());
            System.out.println("Document deleted JSON body");
        }

    }
}
