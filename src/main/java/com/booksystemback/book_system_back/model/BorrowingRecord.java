package com.booksystemback.book_system_back.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class BorrowingRecord {

    @EmbeddedId
    private BorrowingRecordId id;

    private LocalDateTime borrowingTime;

    private LocalDateTime returnTime;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("inventoryId")
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Embeddable
    public static class BorrowingRecordId implements Serializable {
        private Long userId;
        private Long inventoryId;

        public BorrowingRecordId() {
        }

        public BorrowingRecordId(Long userId, Long inventoryId) {
            this.userId = userId;
            this.inventoryId = inventoryId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getInventoryId() {
            return inventoryId;
        }

        public void setInventoryId(Long inventoryId) {
            this.inventoryId = inventoryId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BorrowingRecordId that = (BorrowingRecordId) o;

            if (!userId.equals(that.userId)) return false;
            return inventoryId.equals(that.inventoryId);
        }

        @Override
        public int hashCode() {
            int result = userId.hashCode();
            result = 31 * result + inventoryId.hashCode();
            return result;
        }
    }

    public BorrowingRecordId getId() {
        return id;
    }

    public void setId(BorrowingRecordId id) {
        this.id = id;
    }

    public LocalDateTime getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(LocalDateTime borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}