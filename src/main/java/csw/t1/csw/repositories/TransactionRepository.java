package csw.t1.csw.repositories;

import csw.t1.csw.entities.Event;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.entities.Transaction;
import csw.t1.csw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByTicket(Ticket ticket);

    List<Transaction> findByBuyer(User buyer);

    void deleteByTicket(Ticket ticket);
}