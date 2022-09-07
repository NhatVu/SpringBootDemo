package com.example.demo.resources;

import com.example.demo.domain.Transaction;
import com.example.demo.helper.APIResponseUtils;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {
    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllTransactions(HttpServletRequest request,
                                                                @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), transactions);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Map<String, Object>> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("categoryId") Integer categoryId,
                                                          @PathVariable("transactionId") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), transaction);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addTransaction(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) {
        int userId = (Integer) request.getAttribute("userId");
        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transactionDate = (Long) transactionMap.get("transactionDate");
        Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), transaction);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Object>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId,
                                                                  @RequestBody Transaction transaction) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.updateTransaction(userId, categoryId, transactionId, transaction);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), "update success");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Object>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.removeTransaction(userId, categoryId, transactionId);
        Map<String, Object> res = APIResponseUtils.buildAPISuccess(HttpStatus.OK.value(), "delete success");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
