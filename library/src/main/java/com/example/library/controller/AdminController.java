package com.example.library.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.entity.Reader;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;
import com.example.library.service.ReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private LoanService loanService;

    @PostMapping("/add/authors")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PostMapping("/add/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/get/users")
    public List<Reader> getReader() {
        return readerService.findAll();
    }

    @GetMapping("/reader/{readerId}")
    public List<Loan> getLoansByReader(@PathVariable Long readerId) {
        return loanService.getLoansByReader(readerId);
    }

    @GetMapping("/book/{bookId}")
    public List<Loan> getLoansByBook(@PathVariable Long bookId) {
        return loanService.getLoansByBook(bookId);
    }
    
}
