package com.example.sd1.scheduler;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.sd1.dao.AlertRepo;
import com.example.sd1.dao.ItemRepo;
import com.example.sd1.dao.EdibleRepo;
import com.example.sd1.dao.ShelfRepo;
import com.example.sd1.UserModel.Alert;
import com.example.sd1.UserModel.Edible;
import com.example.sd1.UserModel.Item;
import com.example.sd1.UserModel.Shelf;

@Service
public class UserService {
	@Autowired
	ItemRepo repo; 
	@Autowired
	EdibleRepo erepo;
	@Autowired
	ShelfRepo srepo;
	@Autowired
	AlertRepo arepo;
	
	
//	@Scheduled(cron="0/5 * * * * *")
//	public void edible() {
//		List<Item> item= repo.findAllByEdible("yes");
//		System.out.print(item);
//		
//	}
//	@Scheduled(initialDelay=5000,fixedRate=5000)
//	public void edible() {
//		List<Edible> old=erepo.findAll();
//		for(Edible i:old) {
//			i.setEdibility(i.getEdibility()-1);
//			erepo.save(i);
//			if(i.getEdibility()==0) {
//				erepo.delete(i);
//				Item item=repo.findByName(i.getName());
//				item.setExpired(item.getExpired()+i.getQuantity());
//				repo.save(item);
//			}
//			if(i.getEdibility()<=2) {
//				Alert msg=new Alert();
//				String alertmessage="The item "+i.getName()+" will rott in a few days";
//				msg.setAlertmsg(alertmessage);
//				msg.setTime(new Date().toString());
//				msg.setReadreceipt("unread");
//				arepo.save(msg);
//			}
//		}
//	}
	
	
	@Scheduled(fixedRate=5000)
	public void checkshelf() {
		List<Shelf> item=srepo.findAll();
		for(Shelf i:item) {
			if(i.getQuantity()<=1&&repo.findByName(i.getName()).getQuantity()>repo.findByName(i.getName()).getThreshold()) {
				Item a=repo.findByName(i.getName());
				a.setQuantity(a.getQuantity()-(i.getThresholddaily()-i.getQuantity()));
				repo.save(a);
				i.setQuantity(i.getThresholddaily());
				srepo.save(i);
			}
		}
	}
	@Scheduled(cron="0 0/5 * * * *")
	public void chekshelfdaily() {
		List<Shelf> item=srepo.findAll();
		for(Shelf i:item) {
			if(i.getQuantity()<=(i.getThresholddaily())&&repo.findByName(i.getName()).getQuantity()>repo.findByName(i.getName()).getThreshold()) {
				Item a=repo.findByName(i.getName());
				a.setQuantity(a.getQuantity()-(i.getThresholddaily()-i.getQuantity()));
				repo.save(a);
				i.setQuantity(i.getThresholddaily());
				srepo.save(i);
				
			}
		}
	}
	public void checkwarehouse() {
		List<Item> item=repo.findAll();
		for(Item i:item) {
			if(i.getQuantity()<=i.getThreshold()) {
				Alert msg=new Alert();
				String alertmessage="The item "+i.getName()+" is runnig low on stock";
				msg.setAlertmsg(alertmessage);
				msg.setTime(new Date().toString());
				msg.setReadreceipt("unread");
				arepo.save(msg);
				
			}
		}
	}
	
}
