package mt.springframework.spring5webapp.bootstrap;

import mt.springframework.spring5webapp.model.Author;
import mt.springframework.spring5webapp.model.Book;
import mt.springframework.spring5webapp.model.Publisher;
import mt.springframework.spring5webapp.repository.AuthorRepository;
import mt.springframework.spring5webapp.repository.BookRepository;
import mt.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher pub1 = new Publisher("Harper Collins","3rd Street, NYC");
        Book  ddd = new Book("Domain Driven Design", "1234", pub1);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(pub1);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher pub2 = new Publisher("Wrox","4th Street, NYC");
        Book noEJB = new Book("J2EE Development without EJB", "23444", pub2);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(pub2);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
