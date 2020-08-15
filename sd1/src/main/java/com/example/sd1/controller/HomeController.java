package com.example.sd1.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.sd1.dao.ItemRepo;
import com.example.sd1.dao.AlertRepo;
import com.example.sd1.dao.EdibleRepo;
import com.example.sd1.dao.InvoiceItemRepo;
import com.example.sd1.dao.InvoiceRepo;
import com.example.sd1.dao.ShelfRepo;
import com.example.sd1.dao.UpdateRepo;
import com.example.sd1.UserModel.Alert;
import com.example.sd1.UserModel.Edible;
import com.example.sd1.UserModel.Invoice;
import com.example.sd1.UserModel.InvoiceItem;
import com.example.sd1.UserModel.Item;
import com.example.sd1.UserModel.Shelf;
import com.example.sd1.UserModel.Update;

@Controller
public class HomeController {
	@Autowired
	ItemRepo repo;
	@Autowired
	InvoiceItemRepo irepo;
	@Autowired
	UpdateRepo Urepo;
	@Autowired
	ShelfRepo srepo;
	@Autowired
	EdibleRepo erepo;
	@Autowired
	AlertRepo arepo;
	@Autowired
	InvoiceRepo inrepo;
	
	
	@RequestMapping("inventory")
	public String inventory() { 
		return "inventory";
	}
	@RequestMapping("aitem")
	public String additem() {
		return "aitem";
	}
	@RequestMapping("getinventory")
	ResponseEntity<String> hello() {
		List<Item> list = repo.findAll();
		String out = "";
		int x=list.size();
		if(x>0) {
				for(int i=0;i<x-1;i++) {
					if(list.get(i).getCategory().equals("Edible")) {
						List<Edible> edlist=erepo.findAllByName(list.get(i).getName());
						Edible item =new Edible();
						item.setName(list.get(0).getName());
						for(Edible j:edlist) {
							item.setQuantity(item.getQuantity()+j.getQuantity());
						}
						out+="{ \"id\":\""+(i+1)+"\",\"name\":\""+list.get(i).getName()+"\", \"Category\":\"Edible\", \"Quantity\":\""+list.get(i).getQuantity()+" Kg"+"\", \"Shelf\":\""+item.getQuantity()+" Kg"+"\", \"DuringTransport\":\""+list.get(i).getTransportDamage()+" Kg"+"\", \"DuringShopping\":\""+list.get(i).getShopppingDamage()+" Kg"+"\", \"Expired\":\""+list.get(i).getExpired()+" Kg"+"\"}+";
					}
					else {
						Shelf j=srepo.findByName(list.get(i).getName());
						out+="{ \"id\":\""+(i+1)+"\",\"name\":\""+list.get(i).getName()+"\", \"Category\":\"Non-Edible\", \"Quantity\":\""+list.get(i).getQuantity()+"\", \"Shelf\":\""+j.getQuantity()+"\", \"DuringTransport\":\""+list.get(i).getTransportDamage()+"\", \"DuringShopping\":\""+list.get(i).getShopppingDamage()+"\", \"Expired\":\""+list.get(i).getExpired()+"\"}+";				
					}
				}
			if(list.get(x-1).getCategory().equals("Edible")) {
				List<Edible> edlist=erepo.findAllByName(list.get(x-1).getName());
				Edible item =edlist.get(0);
				item.setQuantity(0);
				for(Edible j:edlist) {
					item.setQuantity(item.getQuantity()+j.getQuantity());
				}
				out+="{ \"id\":\""+x+"\",\"name\":\""+list.get(x-1).getName()+"\", \"Category\":\"Edible\", \"Quantity\":\""+list.get(x-1).getQuantity()+" Kg"+"\", \"Shelf\":\""+item.getQuantity()+" Kg"+"\", \"DuringTransport\":\""+list.get(x-1).getTransportDamage()+" Kg"+"\", \"DuringShopping\":\""+list.get(x-1).getShopppingDamage()+" Kg"+"\", \"Expired\":\""+list.get(x-1).getExpired()+" Kg"+"\"}";
			}
			else {
				Shelf j=srepo.findByName(list.get(x-1).getName());
				out+="{ \"id\":\""+x+"\",\"name\": \""+list.get(x-1).getName()+"\", \"Category\":\"Non-Edible\", \"Quantity\":\""+list.get(x-1).getQuantity()+"\", \"Shelf\":\""+j.getQuantity()+"\", \"DuringTransport\":\""+list.get(x-1).getTransportDamage()+"\", \"DuringShopping\":\""+list.get(x-1).getShopppingDamage()+"\", \"Expired\":\""+list.get(x-1).getExpired()+"\"}";
			}
		}
		return new ResponseEntity<>(out, HttpStatus.OK);
	}	
	
	
	@PostMapping("addtotable")
	public String addtotable(String name,int quantity,int threshold,int edibility,int damage,int bprice,int sprice) {
		if(edibility!=0) {
			Edible ed=new Edible();
			Item item=new Item();
			item.setCategory("Edible");
			item.setName(name);
			item.setQuantity(0);
			item.setThreshold(0);
			item.setBuyingprice(bprice);
			item.setSellingprice(sprice);
			item.setTransportDamage(damage);
			item.setShopppingDamage(0);
			item.setExpired(0);
			item.setEdibility(edibility);
			ed.setName(name);
			ed.setQuantity(quantity);
			ed.setThreshold(threshold);
			ed.setEdibility(edibility);
//			ed.setEdibilitythreshold(edibility);
			erepo.save(ed);
			repo.save(item);
		}else {
			Item item=new  Item();
			item.setName(name);
			item.setQuantity(quantity);
			item.setThreshold(threshold);
			item.setBuyingprice(bprice);
			item.setSellingprice(sprice);
			item.setCategory("Non-Edible");
			item.setTransportDamage(damage);
			Shelf shelf=new Shelf();
			shelf.setName(name);
			shelf.setQuantity(0);
			shelf.setThresholddaily(threshold);
			srepo.save(shelf);
			repo.save(item);
		}
		return "aitem";
	}
	
	@PostMapping("updateinventory")
	public String updateinventoryprocess(Update item) {
		Urepo.save(item);
		return "update";
	}
	
	@RequestMapping("update")
	public String updateShelf() {
		return "Update";
	}
	
	@PostMapping("updatedamage")
	public String updateDamage(String name,int expired,int shoppingdamage) {
		
		Item item=repo.findByName(name);
		if(item.getCategory().equals("Edible")) {
			List<Edible> list=erepo.findAllByName(name);
			while(shoppingdamage>0) {
				list.get(0).setQuantity(list.get(0).getQuantity()-1);
				shoppingdamage--;
				if(list.get(0).getQuantity()==0) {
					erepo.delete(list.get(0));
					list.remove(0);
				}
			}
			erepo.save(list.get(0));
		}
		else {
			Shelf sitem=srepo.findByName(name);
			sitem.setQuantity(sitem.getQuantity()-expired-shoppingdamage);		
			srepo.save(sitem);
		}
		item.setShopppingDamage(item.getShopppingDamage()+shoppingdamage);
		item.setExpired(item.getExpired()+expired);
		repo.save(item);
		return "Update";
	}

	@RequestMapping("confirm")
	public String confirmation(Model model) {
		model.addAttribute("obj",Urepo.findAll());
		return "confirmation";
	}
	
	@RequestMapping("confirmupdate")
	public String confirmupdate() {
		List<Update> list=Urepo.findAll();
		if(!list.get(0).getCategory().equals("Edible")) {
			Item item=repo.findByName(list.get(0).getName());
			item.setQuantity(item.getQuantity()+list.get(0).getQuantity());
			item.setTransportDamage(item.getTransportDamage()+list.get(0).getDamage());
			repo.save(item);
		}
		else {
			Item item=repo.findByName(list.get(0).getName());
			Edible ed=new Edible();
			item.setTransportDamage(item.getTransportDamage()+list.get(0).getDamage());
			ed.setEdibility(item.getEdibility());
			ed.setName(list.get(0).getName());
			ed.setQuantity(list.get(0).getQuantity());
			ed.setThreshold(item.getThreshold());
			erepo.save(ed);
		}
 		Urepo.deleteById(list.get(0).getId());
 		return "confirmation";
	}
	
	@RequestMapping("cancelupdate")
	public String cancelUpdate() {
		List<Update> list=Urepo.findAll();
		Urepo.delete(list.get(0));
		return "confirmation";
	}
	@RequestMapping("billgeneration")
	public String billGeneration() {
		return "billgeneration";
	}
	
	@RequestMapping("additemstobill")
	public String AddItemstoBill(InvoiceItem item) {
		irepo.save(item);
		return "billgeneration";
	}
	
	@RequestMapping("getinvoiceitems")
	ResponseEntity<String> getinvoiceitems() {
		List<InvoiceItem> output = irepo.findAll();
		String out = "";
		int x=output.size();
		if(x!=0) {
				
				for(int i=0;i<x-1;i++) {
					Item item=repo.findByName(output.get(i).getName());
					if(item.getCategory().equals("Edible")) {
						out+="{ \"id\":\""+(i+1)+"\",\"name\":\""+output.get(i).getName()+"\", \"Quantity\":\""+output.get(i).getQuantity()+" Kg"+"\", \"Price\":\""+item.getSellingprice()+" Rs"+"\", \"TotalPrice\":\""+output.get(0).getQuantity()*item.getSellingprice()+" Rs"+"\"}+";
					}else
					out+="{ \"id\":\""+(i+1)+"\",\"name\":\""+output.get(i).getName()+"\", \"Quantity\":\""+output.get(i).getQuantity()+"\", \"Price\":\""+item.getSellingprice()+" Rs"+"\", \"TotalPrice\":\""+output.get(0).getQuantity()*item.getSellingprice()+" Rs"+"\"}+";
				}
			Item item=repo.findByName(output.get(x-1).getName());
			if(item.getCategory().equals("Edible")) {
				out+="{ \"id\":\""+(x)+"\",\"name\":\""+output.get(x-1).getName()+"\", \"Quantity\":\""+output.get(x-1).getQuantity()+" Kg"+"\", \"Price\":\""+item.getSellingprice()+" Rs"+"\", \"TotalPrice\":\""+output.get(x-1).getQuantity()*item.getSellingprice()+" Rs"+"\"}";
			}else
			out+="{ \"id\":\""+(x)+"\",\"name\":\""+output.get(x-1).getName()+"\", \"Quantity\":\""+output.get(x-1).getQuantity()+"\", \"Price\":\""+item.getSellingprice()+" Rs"+"\", \"TotalPrice\":\""+output.get(x-1).getQuantity()*item.getSellingprice()+" Rs"+"\"}";
		}
		return new ResponseEntity<>(out, HttpStatus.OK);
		
	}
	
	@RequestMapping("alert")
	public String alert(Model model) {
		model.addAttribute("obj",arepo.findAll());
		return "alert";
	}
	@RequestMapping("printinvoice")
	public String print(Invoice item) {
		item.setDate(new Date());
		inrepo.save(item);
		List<InvoiceItem> list=irepo.findAllByInvoiceid(item.getInvoiceid());
		for(InvoiceItem i:list) {
			if(i.getCategory().equals("Edible")) {
				int quantity=i.getQuantity();
				List<Edible> elist =erepo.findAllByName(i.getName());
				while(quantity>0) {
					elist.get(0).setQuantity(elist.get(0).getQuantity()-1);
					quantity--;
					if(elist.get(0).getQuantity()==0) {
						erepo.delete(elist.get(0));
						elist.remove(0);
					}
				}
				erepo.save(elist.get(0));
			}
			else {
				Shelf shelfitem=srepo.findByName(i.getName());
				shelfitem.setQuantity(shelfitem.getQuantity()-i.getQuantity());
				srepo.save(shelfitem);
			}
		}
		return "billgeneration";
	}
	
	@RequestMapping("markasread")
	public String markread() {
		List<Alert> msg=arepo.findAllByReadreceipt("unread");
		msg.get(0).setReadreceipt("read");
		arepo.save(msg.get(0));
		return "alert";
	}
}
