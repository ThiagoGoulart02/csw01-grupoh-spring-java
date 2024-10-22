package csw.t1.csw.repositories;

import csw.t1.csw.entities.Ticket;
import csw.t1.csw.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTicket(Ticket ticket);

    void deleteByTicket(Ticket ticket);
}