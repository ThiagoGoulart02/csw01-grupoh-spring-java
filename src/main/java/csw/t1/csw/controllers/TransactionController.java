package csw.t1.csw.controllers;

import csw.t1.csw.dto.transaction.RequestTransactionDTO;
import csw.t1.csw.dto.transaction.ResponseTransactionDTO;
import csw.t1.csw.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/create-new-transaction")
    public ResponseEntity<ResponseTransactionDTO> createTransaction(@RequestBody
                                                                        @Valid RequestTransactionDTO dto){
        return service.createTransaction(dto);
    }

    @PatchMapping("/update-transaction-status/{id}/{status}")
    public ResponseEntity<ResponseTransactionDTO> updateTransactionStatus(@PathVariable Long id,
                                                                          @PathVariable String status) {
        return service.updateTransactionStatus(id, status);
    }

}