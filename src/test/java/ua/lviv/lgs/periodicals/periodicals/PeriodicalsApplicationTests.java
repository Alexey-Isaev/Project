package ua.lviv.lgs.periodicals.periodicals;

import java.util.List;

import org.apache.catalina.realm.JNDIRealm.User;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.lviv.lgs.periodicals.dao.BucketRepository;
import ua.lviv.lgs.periodicals.dao.PeriodicalRepository;
import ua.lviv.lgs.periodicals.dao.UserRepository;
import ua.lviv.lgs.periodicals.domain.Bucket;
import ua.lviv.lgs.periodicals.domain.Periodical;
import ua.lviv.lgs.periodicals.domain.UserRole;
import ua.lviv.lgs.periodicals.service.BucketService;
import ua.lviv.lgs.periodicals.service.PeriodicalsService;
import ua.lviv.lgs.periodicals.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
class PeriodicalsApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PeriodicalsService periodicalsService;
	
	@Autowired
	private BucketService bucketService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PeriodicalRepository periodicalRepository;
	
	@Autowired
	private BucketRepository bucketRepository;
	
	@Test
 	 void testSaveUser() {
	
		List<User> users = userRepository.findAll();
		assertThat(users, HasSize(0));
	
		ua.lviv.lgs.periodicals.domain.User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);
	
		userService.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hsSize(1));
		
		ua.lviv.lgs.periodicals.domain.User userFromOb =  users.get(0);
		assertTrue(userFromOb.getEmail().equals(user.getEmail()));
		assertTrue(userFromOb.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromOb.getLastName().equals(user.getLastName()));
		assertTrue(userFromOb.getRole().equals(user.getRole()));
		
	}

	@Test
	 void testFindByEmail() {
	
		List<org.springframework.boot.autoconfigure.security.SecurityProperties.User> users = userRepository.findAll();
		assertThat(users, HasSize(0));
	
		ua.lviv.lgs.periodicals.domain.User user = new User();
		user.setEmail("custom@gmail.com");
		user.setFirstName("2");
		user.setLastName("2");
		user.setPassword("2");
		user.setPasswordConfirm("2");
		user.setRole(UserRole.ROLE_USER);
	
		UserRepository.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hsSize(1));
		
		User FindByEmail = userService.FindByEmail(user.getEmail());
		assertTrue(FindByEmail.getEmail().equals(user.getEmail()));
		assertTrue(FindByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(FindByEmail.getLastName().equals(user.getLastName()));
		assertTrue(FindByEmail.getRole().equals(user.getRole()));
		
	}
	
	public void testSavePeriodical() {
		List<Periodical> periodicals = PeriodicalRepository.findAll();
		assertThat(periodicals, HasSize(0));
	
		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);
	
		periodicalRepository.save(periodical);
		
		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hsSize(1));
		
		ua.lviv.lgs.periodicals.domain.Periodical periodicalFromOb =  periodicals.get(0);
		assertTrue(periodicalFromOb.getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromOb.getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromOb.getId().equals(periodical.getId()));
		assertTrue(periodicalFromOb.getName().equals(periodical.getName()));
		assertTrue(periodicalFromOb.getPrice().equals(periodical.getPrice()));
		
	}

	public void testFindById() {
		List<Periodical> periodicals = PeriodicalRepository.findAll();
		assertThat(periodicals, HasSize(0));
	
		Periodical periodical = new Periodical();
		periodical.setId(1000);
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);
	
		periodicalRepository.save(periodical);
		
		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hsSize(1));
		
		ua.lviv.lgs.periodicals.domain.Periodical periodicalFromOb =  periodicals.get(0);
		assertTrue(periodicalFromOb.getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromOb.getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromOb.getName().equals(periodical.getName()));
		assertTrue(periodicalFromOb.getPrice().equals(periodical.getPrice()));
		
	}

	public void testGetAllPeriodicals() {
		List<Periodical> periodicals = PeriodicalRepository.findAll();
		assertThat(periodicals, HasSize(0));
	
		Periodical periodical = new Periodical();
		periodical.setId(1000);
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(Id);
		
		Periodical periodical2 = new Periodical();
		periodical2.setId(1000);
		periodical2.setName("12");
		periodical2.setDescription("12");
		periodical2.setEncodedImage("12");
		periodical2.setPrice(12d);
	
		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2 ));

		
		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hsSize(1));
		
		List<ua.lviv.lgs.periodicals.domain.Periodical> periodicalFromOb =  periodicals.getAllPeriodicals();
		assertTrue(periodicalFromOb.get(0).getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromOb.get(0).getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromOb.get(0).getName().equals(periodical.getName()));
		assertTrue(periodicalFromOb.get(0).getPrice().equals(periodical.getPrice()));
		
		assertTrue(periodicalFromOb.get(1).getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromOb.get(1).getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromOb.get(1).getName().equals(periodical.getName()));
		assertTrue(periodicalFromOb.get(1).getPrice().equals(periodical.getPrice()));
		
	}

	public void testAddToBucket() {
		
		ua.lviv.lgs.periodicals.domain.User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);
		
		userService.save(user);
		
		User userFromOb.findAll().stream().findFirst();
		
		List<Periodical> periodicals = PeriodicalRepository.findAll().get();
		assertThat(periodicals, hasSize(0));
	
		Periodical periodical = new Periodical();
		periodical.setId(1000);
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(Id);
		
		periodicalsService.save(periodical);
		
		Periodical periodicalFromOb = periodicalRepository.findAll().stream().findFirst().get();
		
		Date now = new Date();
		ua.lviv.lgs.periodicals.domain.Bucket busket = new Bucket();
		busket.setPeriodical(periodicalFromOb);
		busket.setUser(userFromOb);
		Bucket.setPurchaseDate(new);
		
		List<Bucket> buckets = bucketRepository.findAll();
		asssertthat(Bucket, hasSive(0));
		
		bucketService.add(busket);
		
		Bucket bucketFromOb = buskets.get(0);
		assertTrue(bucketFromOb.getPeriodical().equals(periodicalFromOb));
		assertTrue(bucketFromOb.geUser().equals(userFromOb));
		assertTrue(bucketFromOb.getPurchaseDate().equals(now));
	}

}
