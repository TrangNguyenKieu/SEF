package properties;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.portable.InputStream;

import properties.*;
import startUp.RealEstate;
import users.*;
import SystemExceptions.*;
import Utilities.*;
import payment.*;

class SalebyAuctionTest {
	RealEstate re;
	SalebyAuction sale;
	Auction auc1;
	Auction auc2;
	
	@BeforeEach
	public void setup() {
		re= new RealEstate();
		sale= new SalebyAuction("BUYER1", "Coburg", "house for sale", "Coburg", 2, 2, 2,"flat");
		auc1= new Auction(sale.getPropertyID(), RealEstate.currentDate, 3000);
		auc2= new Auction(sale.getPropertyID(), RealEstate.currentDate, 4000);
	}
	@Test
	//positive test
	void testCreateAuction() {
		String data = "24/06/2020\r\n3000\r\n";
		java.io.InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertEquals(true, sale.createAuction());
	}
	
	//negative test
	@Test
	void testCannotCreateAuction() {
		sale.getAllAuctions().add(auc1);
		String data = "24/06/2020\r\n3000\r\n";
		java.io.InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertEquals(false, sale.createAuction());
	}
}
