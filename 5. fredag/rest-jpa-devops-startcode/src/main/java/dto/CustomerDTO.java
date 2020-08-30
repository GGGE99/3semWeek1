/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author marcg
 */
public class CustomerDTO {

    long customerID;
    String fullName;
    String accountNumber;
    double balance;

    public CustomerDTO(BankCustomer b) {
        this.customerID = b.getId();
        this.fullName = b.getFirstName() + " " + b.getLastName();
        this.accountNumber = b.getAccountNumber();
        this.balance = b.getBalance();
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + "customerID=" + customerID + ", fullName=" + fullName + ", accountNumber=" + accountNumber + ", balance=" + balance + '}';
    }

    
}
