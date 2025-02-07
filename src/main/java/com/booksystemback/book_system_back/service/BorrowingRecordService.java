package com.booksystemback.book_system_back.service;

import com.booksystemback.book_system_back.model.BorrowingRecord;
import com.booksystemback.book_system_back.model.Inventory;
import com.booksystemback.book_system_back.repository.BorrowingRecordRepository;
import com.booksystemback.book_system_back.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void borrowBook(Long userId, Long inventoryId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(inventoryId);
        if (!inventoryOpt.isPresent()) {
            throw new RuntimeException("Inventory not found for id: " + inventoryId);
        }

        Inventory inventory = inventoryOpt.get();
        if (inventory.getStatus() != Inventory.Status.AVAILABLE) {
            throw new RuntimeException("Inventory is not available for borrowing");
        }

        inventory.setStatus(Inventory.Status.BORROWED);
        inventoryRepository.save(inventory);

        BorrowingRecord.BorrowingRecordId recordId = new BorrowingRecord.BorrowingRecordId();
        recordId.setUserId(userId);
        recordId.setInventoryId(inventoryId);

        BorrowingRecord record = new BorrowingRecord();
        record.setId(recordId);
        record.setBorrowingTime(LocalDateTime.now());
        borrowingRecordRepository.save(record);
    }

    @Transactional
    public void returnBook(Long userId, Long inventoryId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(inventoryId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            if (inventory.getStatus() == Inventory.Status.BORROWED) {
                inventory.setStatus(Inventory.Status.AVAILABLE);
                inventoryRepository.save(inventory);

                BorrowingRecord.BorrowingRecordId recordId = new BorrowingRecord.BorrowingRecordId();
                recordId.setUserId(userId);
                recordId.setInventoryId(inventoryId);

                Optional<BorrowingRecord> recordOpt = borrowingRecordRepository.findById(recordId);
                if (recordOpt.isPresent()) {
                    BorrowingRecord record = recordOpt.get();
                    record.setReturnTime(LocalDateTime.now());
                    borrowingRecordRepository.save(record);
                }
            }
        }
    }
}