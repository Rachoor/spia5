package tacos.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import tacos.models.User;
import tacos.repos.UserRepository;

@Component
public class DatabaseSeeder implements ApplicationRunner {

	private PasswordEncoder encoder;
	private UserRepository userRepo;
	
	@Autowired
	public DatabaseSeeder(UserRepository userRepo) {
		this.userRepo = userRepo;
		this.encoder = new StandardPasswordEncoder("53cr3t");
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = new User("brubble",
				encoder.encode("dino"),
				"Barney Rubble",
				"1 Stoney Way",
				"Bedrock",
				"FL",
				"12345",
				"1231231234");
		userRepo.save(user);
	}

}
