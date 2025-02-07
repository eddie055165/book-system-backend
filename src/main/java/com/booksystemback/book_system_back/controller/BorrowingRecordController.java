package com.booksystemback.book_system_back.controller;

import com.booksystemback.book_system_back.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long userId, @RequestParam Long inventoryId) {
        System.out.printf("Borrowing book for user %d inventory %d\n", userId, inventoryId);
        borrowingRecordService.borrowBook(userId, inventoryId);
        return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long inventoryId) {
        borrowingRecordService.returnBook(userId, inventoryId);
        return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
    }
}