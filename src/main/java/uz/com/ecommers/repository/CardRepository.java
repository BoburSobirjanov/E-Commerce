package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.dto.card.CardForUser;
import uz.com.ecommers.model.entity.card.CardEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    @Query("select u from cards as u where u.isDeleted=false and u.type=?1")
    List<CardForUser> findCardEntityByType(String type);
    @Query("select u from cards as u where u.isDeleted=false and u.cardNumber=?1")
    CardEntity findCardEntityByCardNumber(String number);
    @Query("select u from cards as u where u.isDeleted=false")
    Optional<List<CardForUser>> getAll();
}
