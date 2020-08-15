package com.example.sd1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sd1.UserModel.InvoiceItem;

public interface InvoiceItemRepo extends JpaRepository<InvoiceItem,Integer> {
	List<InvoiceItem> findAllByInvoiceid(String invoiceid);
}
