package BankingApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class UserActions extends ObjectsPageFactory {

	private static final Path USER_DETAILS_DIR = Path.of(
			"/Users/anishkoppisetty/Desktop/Framework/BankingAppDemo/src/main/java/BankingApp/OfficialUserInfoJSON");

	public static class UserProfile {
		public String username;
		public String firstName;
		public String lastName;
		public String dob;
		public String id;
		public String email;
		public String address;
		public String phone;
		public double balance;

		public UserProfile() {
		}

		public UserProfile(String username, String firstName, String lastName, String dob, String id, String email,
				String address, String phone, double balance) {
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dob = dob;
			this.id = id;
			this.email = email;
			this.address = address;
			this.phone = phone;
			this.balance = balance;
		}
	}

	private static void saveUserAsJson(UserProfile profile) throws IOException {

		Files.createDirectories(USER_DETAILS_DIR);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		Path jsonPath = USER_DETAILS_DIR.resolve(profile.id + ".json");

		mapper.writeValue(jsonPath.toFile(), profile);

		System.out.println("JSON written to: " + jsonPath.toAbsolutePath());
	}

	public static void signUp(String username, String firstName, String lastName, String dob, String email,
			String address, String phone, double balance) throws Exception {

		String id = UUID.randomUUID().toString();

		UserProfile profile = new UserProfile(username, firstName, lastName, dob, id, email, address, phone, balance);

		saveUserAsJson(profile);
	}

	public static UserProfile login(String username) throws Exception {

		if (!Files.exists(USER_DETAILS_DIR))
			return null;

		ObjectMapper mapper = new ObjectMapper();

		for (Path file : Files.list(USER_DETAILS_DIR).toList()) {
			UserProfile user = mapper.readValue(file.toFile(), UserProfile.class);

			if (user.username.equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}
}
