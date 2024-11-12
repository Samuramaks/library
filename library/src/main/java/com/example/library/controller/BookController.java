package com.example.library.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.entity.Reader;
import com.example.library.repository.LoanRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.ReaderService;




@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        Optional<Book> bookOpt = bookService.findById(id);
        return bookOpt.get();
    }


    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @GetMapping("/author/all")
    public List<Author> getAllAuthor() {
        return authorService.getAllAuthor();
    }


    @GetMapping("/get")
    public Loan getBookLoan(Long readerId, Long bookId) {
        Optional<Reader> readerOpt = readerService.findById(readerId);
        Optional<Book> bookOpt = bookService.findById(bookId);

        if (readerOpt.isPresent() && bookOpt.isPresent()) {
            Reader reader = readerOpt.get();
            Book book = bookOpt.get();

           
            Loan loan = new Loan();
            loan.setReader(reader);
            loan.setBook(book);
            loan.setLoanDate(new Date());

            
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 14);
            Date returnDate = calendar.getTime();

            loan.setReturnDate(returnDate);

            
            Loan savedLoan = loanRepository.save(loan);

            return savedLoan;
        }
        return null;
    }
    
}
