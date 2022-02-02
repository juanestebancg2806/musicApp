package app.springframework.musicApp.bootstrap;

import app.springframework.musicApp.domain.*;
import app.springframework.musicApp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final DocumentRepository documentRepository;
    private final GenreRepository genreRepository;
    private  final PlaylistRepository playlistRepository;
    private  final SongRepository songRepository;

    private final UserRepository userRepository;

    public BootStrapData(AuthorRepository authorRepository, CountryRepository countryRepository, DocumentRepository documentRepository, GenreRepository genreRepository, PlaylistRepository playlistRepository, SongRepository songRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.documentRepository = documentRepository;
        this.genreRepository = genreRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Country country1 = new Country("Colombia");

        Country country2 = new Country("Chile");
        //countryRepository.saveAll(List.of(new Country("Colombia"),new Country("Chile")));
        countryRepository.save(country1);
        countryRepository.save(country2);


        System.out.println("Total countries: " +countryRepository.count());


        Document document1 = new Document("Cedula");
        Document document2 = new Document("Tarjeta identidad");
        //documentRepository.saveAll(List.of(new Document("Cedula"),new Document("Tarjeta identidad")));
        documentRepository.save(document1);
        documentRepository.save(document2);
        System.out.println("Total documents: " +documentRepository.count());

        Author author1 = new Author("Luis miguel","Ap");
        Author author2 = new Author("autor2","apAutor2");
        author1.setCountry(country1);
        author2.setCountry(country2);
        authorRepository.save(author1);
        authorRepository.save(author2);

        System.out.println("Total authors: " +authorRepository.count());



        Genre genre1 = new Genre("Genre1");
        Genre genre2 = new Genre("Genre2");
        genreRepository.save(genre1);
        genreRepository.save(genre2);
        System.out.println("Total genres: " +genreRepository.count());



        Song song1 = new Song("Song1",300);
        Song song2 = new Song("song2",150);
        song1.setAuthor(author1);
        song1.setGenre(genre1);
        song2.setAuthor(author2);
        song2.setGenre(genre2);
        songRepository.save(song1);
        songRepository.save(song2);




        User user1 = new User("user1","lastuser1","user1@g.com","admin",new Date(1998,6,28));
        User user2 = new User("user2","lastuser2","user2@g.com","admin",new Date(1990,6,1));
        user1.setDocument(document1);
        user1.setId(1234L);
        user2.setDocument(document2);
        user2.setId(34L);

        userRepository.save(user1);
        userRepository.save(user2);




        //No funciona
        user1.getSongs().add(song1);
        song1.getUsers().add(user1);
        user2.getSongs().add(song2);
        song2.getUsers().add(user2);

        //userRepository.save(user1);
        //userRepository.save(user2);

        try {
            //songRepository.save(song1);
            //songRepository.save(song2);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("Total songs: " +songRepository.count());
        System.out.println("Total users: " +userRepository.count());



















    }
}
