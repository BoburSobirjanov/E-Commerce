package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.entity.card.CardEntity;
import uz.com.ecommers.model.entity.order.OrderEntity;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query("select u from orders as u where u.isDeleted=false and u.id=?1")
    OrderEntity findOrderEntityById(UUID id);
}
