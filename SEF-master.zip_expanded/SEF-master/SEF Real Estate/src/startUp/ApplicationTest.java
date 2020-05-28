package startUp;
import properties.*;
import users.*;
import SystemExceptions.*;
import Utilities.*;
import payment.*;


import static org.junit.jupiter.api.Assertions.*;
import org.omg.CORBA.portable.InputStream;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {
	String proID;
	RentalProperty rp;
	Tenant tenant;
	Application app;
	Application app2;
	RealEstate re;

	@BeforeEach
	public void setup() {
		re= new RealEstate();
		rp= new RentalProperty("S1", "Coburg", "A big house", "Coburg", 2, 1, 1, "house", 200, "12 months");
		proID= rp.getPropertyID();
		tenant = new Tenant();
		app= new Application(proID,tenant , 2000, "student", 180, "12 months");
		app2= new Application(proID,tenant , 8000, "student", 200, "12 months");
	}
	
	//positive 

	@Test
	//check pre-condition: application status should be pending before landlord review the applications
	void testCurrentApplicationStatus() {
		
		rp.getAllApplications().add(app);
		rp.getAllApplications().add(app2);
		assertEquals(ApplicationStatus.Pending, app.getAppStatus());
		assertEquals(ApplicationStatus.Pending, app2.getAppStatus());
	}
	
	@Test 
	
	//If Landlord accept application, application status will be Accepted
	void testAcceptedApplicationStatus() throws StatusException {
		
		
		rp.getAllApplications().add(app);
		rp.getAllApplications().add(app2);
		re.setCurrenProp(rp); //selected property rp
		re.setCurrentApp(app);//selected application app 
		String ID=app.getApplicationID();
		re.setAppID(ID);//for comparison
		
		re.acceptApplication();
		assertEquals(ApplicationStatus.Accepted, app.getAppStatus());
	}
	
	@Test 
	//after landlord has accepted 1 app, the other applications will be Rejected
	void testOtherApplicationStatus() throws StatusException{
		
		rp.getAllApplications().add(app);
		rp.getAllApplications().add(app2);
		re.setCurrenProp(rp); //selected property rp
		re.setCurrentApp(app);//selected application app 
		String ID=app.getApplicationID();
		re.setAppID(ID);//for comparison
		
		re.acceptApplication();
		assertEquals(ApplicationStatus.Rejected, app2.getAppStatus());
	}
	
	
	//negative
	
	
	
	@Test 
	void testResponseToRejectedAppException() {
		//respond to a rejected app will through StatusException
		app.rejectApp();
		re.setCurrentApp(app);
		  assertThrows(StatusException.class, () -> {
		      re.acceptApplication();;
		    });
	}
	
	@Test 
	void testRespondToAcceptedAppException() {
		//respond to an accepted app will through StatusException
		app.acceptApp();
		re.setCurrentApp(app);
		  assertThrows(StatusException.class, () -> {
		      re.acceptApplication();;
		    });
	}
}
