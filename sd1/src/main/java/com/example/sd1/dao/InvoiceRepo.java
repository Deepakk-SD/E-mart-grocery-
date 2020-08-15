package com.example.sd1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sd1.UserModel.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice ,Integer> {

}
