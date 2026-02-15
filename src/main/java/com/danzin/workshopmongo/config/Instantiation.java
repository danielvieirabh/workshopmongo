package com.danzin.workshopmongo.config;
import com.danzin.workshopmongo.domain.Post;
import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.dto.AuthorDTO;
import com.danzin.workshopmongo.dto.CommentDTO;
import com.danzin.workshopmongo.repository.PostRepositoy;
import com.danzin.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepositoy postRepositoy;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception { //Instanciar os objetos para salvar no banco
        userRepository.deleteAll(); //lIMPA A COLECAO
        postRepositoy.deleteAll(); //lIMPA A COLECAO

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob)); //salvar primeiro

        Post post1 = new Post(null, LocalDate.parse("15/02/2026", formatter),"Eu sou o melhor", "Vou ser o melhor do mundo Tambem, ASS: Danzin!" , new AuthorDTO(maria)); //E post da Maria
        Post post2 = new Post(null, LocalDate.parse("15/03/2026", formatter),"Ola mundo!!", "Vou viajar hoje" , new AuthorDTO(maria)); //E post da Maria

        CommentDTO commentDTO1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("15/02/2026", formatter),new AuthorDTO(alex)); // E do post 1
        CommentDTO commentDTO2 = new CommentDTO("Olaaa BB!", LocalDate.parse("15/02/2026", formatter),new AuthorDTO(bob)); //E do post 1
        CommentDTO commentDTO3 = new CommentDTO("Bem vindo! ", LocalDate.parse("15/12/2026", formatter),new AuthorDTO(alex)); //E do post 2

        post1.getComments().addAll(Arrays.asList(commentDTO1, commentDTO2));
        post2.getComments().addAll(Arrays.asList(commentDTO3));

        postRepositoy.saveAll(Arrays.asList(post1,post2)); //Salva os posts no banco de dados
        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria); //Incluir os posts na lista da maria
    }
}
