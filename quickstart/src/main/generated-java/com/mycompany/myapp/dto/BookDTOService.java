/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-vaadin:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.mycompany.myapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Book_;
import com.mycompany.myapp.dto.support.PageRequestByExample;
import com.mycompany.myapp.dto.support.PageResponse;
import com.mycompany.myapp.repository.AuthorRepository;
import com.mycompany.myapp.repository.BookRepository;

/**
 * A simple DTO Facility for Book.
 */
@Service
public class BookDTOService {

    @Inject
    private BookRepository bookRepository;
    @Inject
    private AuthorDTOService authorDTOService;
    @Inject
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public BookDTO findOne(Integer id) {
        return toDTO(bookRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<BookDTO> complete(String query, int maxResults) {
        List<Book> results = bookRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<BookDTO> findAll(PageRequestByExample<BookDTO> req) {
        Example<Book> example = null;
        Book book = toEntity(req.example);

        if (book != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(Book_.title.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(Book_.summary.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(book, matcher);
        }

        Page<Book> page;
        if (example != null) {
            page = bookRepository.findAll(example, req.toPageable());
        } else {
            page = bookRepository.findAll(req.toPageable());
        }

        List<BookDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public BookDTO save(BookDTO dto) {
        if (dto == null) {
            return null;
        }

        Book book;
        if (dto.isIdSet()) {
            book = bookRepository.findOne(dto.id);
        } else {
            book = new Book();
        }

        book.setTitle(dto.title);
        book.setSummary(dto.summary);
        book.setPublicationDate(dto.publicationDate);
        book.setBestSeller(dto.bestSeller);
        book.setPrice(dto.price);

        if (dto.author == null) {
            book.setAuthor(null);
        } else {
            Author author = book.getAuthor();
            if (author == null || (author.getId().compareTo(dto.author.id) != 0)) {
                book.setAuthor(authorRepository.findOne(dto.author.id));
            }
        }

        if (dto.coAuthor == null) {
            book.setCoAuthor(null);
        } else {
            Author coAuthor = book.getCoAuthor();
            if (coAuthor == null || (coAuthor.getId().compareTo(dto.coAuthor.id) != 0)) {
                book.setCoAuthor(authorRepository.findOne(dto.coAuthor.id));
            }
        }

        return toDTO(bookRepository.save(book));
    }

    /**
     * Converts the passed book to a DTO.
     */
    public BookDTO toDTO(Book book) {
        return toDTO(book, 1);
    }

    /**
     * Converts the passed book to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param book
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public BookDTO toDTO(Book book, int depth) {
        if (book == null) {
            return null;
        }

        BookDTO dto = new BookDTO();

        dto.id = book.getId();
        dto.title = book.getTitle();
        dto.summary = book.getSummary();
        dto.extractFileName = book.getExtractFileName();
        dto.extractContentType = book.getExtractContentType();
        dto.extractSize = book.getExtractSize();
        dto.publicationDate = book.getPublicationDate();
        dto.bestSeller = book.getBestSeller();
        dto.price = book.getPrice();
        if (depth-- > 0) {
            dto.author = authorDTOService.toDTO(book.getAuthor(), depth);
            dto.coAuthor = authorDTOService.toDTO(book.getCoAuthor(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a Book.
     * Convenient for query by example.
     */
    public Book toEntity(BookDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Book.
     * Convenient for query by example.
     */
    public Book toEntity(BookDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Book book = new Book();

        book.setId(dto.id);
        book.setTitle(dto.title);
        book.setSummary(dto.summary);
        book.setPublicationDate(dto.publicationDate);
        book.setBestSeller(dto.bestSeller);
        book.setPrice(dto.price);
        if (depth-- > 0) {
            book.setAuthor(authorDTOService.toEntity(dto.author, depth));
            book.setCoAuthor(authorDTOService.toEntity(dto.coAuthor, depth));
        }

        return book;
    }
}