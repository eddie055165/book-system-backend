package com.booksystemback.book_system_back.service;

import com.booksystemback.book_system_back.model.BorrowingRecord;
import com.booksystemback.book_system_back.model.Inventory;
import com.booksystemback.book_system_back.model.User;
import com.booksystemback.book_system_back.repository.BorrowingRecordRepository;
import com.booksystemback.book_system_back.repository.InventoryRepository;
import com.booksystemback.book_system_back.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;




    @Transactional
    public void borrowBook(Long userId, Long inventoryId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(inventoryId);
        if (!inventoryOpt.isPresent()) {
            throw new RuntimeException("Inventory not found for id: " + inventoryId);
        }
        Inventory inventory = inventoryOpt.get();
        System.out.println("Inventory status: " + inventory.getStatus());

        if (inventory.getStatus() != Inventory.Status.AVAILABLE) {
            throw new RuntimeException("Inventory is not available for borrowing");
        }

        BorrowingRecord.BorrowingRecordId recordId = new BorrowingRecord.BorrowingRecordId();
        recordId.setUserId(userId);
        recordId.setInventoryId(inventoryId);
        Optional<BorrowingRecord> existingRecord = borrowingRecordRepository.findById(recordId);
        if (inventory.getStatus() == Inventory.Status.AVAILABLE) {
            inventory.setStatus(Inventory.Status.BORROWED);
            inventoryRepository.save(inventory);
        } else if (inventory.getStatus() == Inventory.Status.BORROWED) {
            throw new RuntimeException("Inventory is not available for borrowing");
        }

        inventory.setStatus(Inventory.Status.BORROWED);
        inventoryRepository.save(inventory);

        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found for id: " + userId);
        }
        User user = userOpt.get();
        BorrowingRecord record = new BorrowingRecord();
        record.setId(recordId);
        record.setUser(user);
        record.setInventory(inventory);
        record.setBorrowingTime(LocalDateTime.now());

        // 儲存借閱記錄
        borrowingRecordRepository.save(record);
    }


    @Transactional
    public void returnBook(Long userId, Long inventoryId) {
        BorrowingRecord.BorrowingRecordId recordId = new BorrowingRecord.BorrowingRecordId();
        recordId.setUserId(userId);
        recordId.setInventoryId(inventoryId);

        Optional<BorrowingRecord> recordOpt = borrowingRecordRepository.findById(recordId);
        if (!recordOpt.isPresent()) {
            throw new RuntimeException("Borrowing record not found for userId: " + userId + " and inventoryId: " + inventoryId);
        }

        BorrowingRecord record = recordOpt.get();
        record.setReturnTime(LocalDateTime.now());
        borrowingRecordRepository.save(record);

        Optional<Inventory> inventoryOpt = inventoryRepository.findById(inventoryId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.setStatus(Inventory.Status.AVAILABLE);
            inventoryRepository.save(inventory);
        }
    }
}