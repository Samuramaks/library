package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.entity.Reader;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserEntityRepository readerRepository;

    public Loan loanBook(Long readerId, Long bookId) {
        Optional<Reader> readerOpt = readerRepository.findById(readerId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

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

    
    public List<Loan> getLoansByReader(Long readerId) {
        return loanRepository.findByReaderId(readerId);
    }

    
    public List<Loan> getLoansByBook(Long bookId) {
        return loanRepository.findByBookId(bookId);
    }
}
