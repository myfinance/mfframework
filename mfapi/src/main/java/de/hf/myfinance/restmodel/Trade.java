package de.hf.myfinance.restmodel;

public class Trade {

        private String depotBusinessKey;
        private String securityBusinessKey;
        private Double amount;
    
        public Trade(String depotBusinessKey, String securityBusinessKey, Double amount) {
            this.depotBusinessKey = depotBusinessKey;
            this.securityBusinessKey = securityBusinessKey;
            this.amount = amount;
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
    
         public Double getPositionCurve() {
            return this.amount;
         }
      
         public void setPositionCurve(Double amount) {
            this.amount = amount;
         }
    }
