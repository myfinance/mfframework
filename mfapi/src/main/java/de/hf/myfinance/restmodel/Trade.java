package de.hf.myfinance.restmodel;


import java.time.LocalDate;

public class Trade {

        private String depotBusinessKey;
        private String securityBusinessKey;
        private Double amount;
        private LocalDate tradeDate;
        private String transactionId;
    
        public Trade(String depotBusinessKey, String securityBusinessKey, Double amount, String transactionId) {
            this.depotBusinessKey = depotBusinessKey;
            this.securityBusinessKey = securityBusinessKey;
            this.amount = amount;
            this.transactionId = transactionId;
        }
    
        public String getDepotBusinessKey() {
            return depotBusinessKey;
        }
    
        public void setDepotBusinessKey(String depotBusinessKey) {
            this.depotBusinessKey = depotBusinessKey;
        }
    
        public String getSecurityBusinessKey() {
            return securityBusinessKey;
        }
    
        public void setSecurityBusinessKey(String securityBusinessKey) {
            this.securityBusinessKey = securityBusinessKey;
        }
    
        public Double getAmount() {
            return this.amount;
        }
      
        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public LocalDate getTradeDate() {
            return this.tradeDate;
        }
      
        public void setTradeDate(LocalDate tradeDate) {
            this.tradeDate = tradeDate;
        }

        public String getTransactionId() {
            return this.transactionId;
        }
    
        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
    }
