package com.booksystemback.book_system_back.repository;

import com.booksystemback.book_system_back.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, BorrowingRecord.BorrowingRecordId> {
}