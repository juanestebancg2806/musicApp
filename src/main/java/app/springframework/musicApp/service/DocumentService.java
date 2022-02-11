package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Document add(Document document){
        return this.documentRepository.save(document);
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

    public Document updateById(Document document,Long id){
        Optional<Document> documentOptional = this.documentRepository.findById(id);
        Document oldDocument = null;
        if(documentOptional.isPresent()){
            oldDocument = documentOptional.get();
            oldDocument.setName(document.getName());
            oldDocument = this.documentRepository.save(oldDocument);
            System.out.println("Document updated PUT");
        }

        return  oldDocument;

    }



    public void deleteByName(String name){
        List<Document> documents = this.documentRepository.findByName(name);
        if(documents.size() > 0){
            Document d = documents.get(0);
            this.documentRepository.deleteById(d.getId());
            System.out.println("Document deleted JSON body");
        }

    }

    public void deleteById(Long id){
        this.documentRepository.deleteById(id);
    }
}
