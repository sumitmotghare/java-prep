package com.example.prep.repository;

import com.example.prep.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    // Derived query — Spring Data generates SQL automatically
    List<Position> findBySymbol(String symbol);

    List<Position> findBySide(Position.Side side);

    // JPQL — explicit query for more control
    @Query("SELECT p FROM Position p WHERE p.quantity >= :minQty ORDER BY p.symbol")
    List<Position> findByMinQuantity(int minQty);
}
