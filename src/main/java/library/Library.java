package library;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private static final int MAX_LOANS = 5;

    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Map<String, Copy> copies = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void addCopy(String isbn, String inventoryNumber) {
        Book book = books.get(isbn);
        if (book == null) throw new IllegalArgumentException("Unknown ISBN: " + isbn);
        copies.put(inventoryNumber, new Copy(inventoryNumber, book));
    }

    public void registerMember(Member member) {
        members.put(member.getNumber(), member);
    }

    public Copy findCopy(String inventoryNumber) {
        return copies.get(inventoryNumber);
    }

    public Loan lend(String memberNumber, String inventoryNumber, LocalDate today) {
        Member member = members.get(memberNumber);
        if (member == null) throw new IllegalArgumentException("Unknown member: " + memberNumber);
        Copy copy = copies.get(inventoryNumber);
        if (copy == null) throw new IllegalArgumentException("Unknown copy: " + inventoryNumber);
        if (!copy.isAvailable()) throw new IllegalStateException("Copy not available");
        if (openLoansOf(memberNumber) >= MAX_LOANS)
            throw new IllegalStateException("Too many loans in progress");

        Loan loan = new Loan(member, copy, today);
        copy.setAvailable(false);
        loans.add(loan);
        return loan;
    }

    public Loan returnCopy(String inventoryNumber, LocalDate today) {
        Copy copy = copies.get(inventoryNumber);
        if (copy == null) throw new IllegalArgumentException("Unknown copy: " + inventoryNumber);
        for (Loan loan : loans) {
            if (loan.isOpen() && loan.getCopy().equals(copy)) {
                loan.close(today);
                copy.setAvailable(true);
                return loan;
            }
        }
        throw new IllegalStateException("Copy is not on loan");
    }

    private int openLoansOf(String memberNumber) {
        int count = 0;
        for (Loan loan : loans) {
            if (loan.isOpen() && loan.getMember().getNumber().equals(memberNumber)) count++;
        }
        return count;
    }
}