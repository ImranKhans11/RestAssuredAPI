package faker;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerObject {

    @Test
    public void faker() {

        Faker faker = new Faker();

        String randomCity = faker.address().city();
        System.out.println(randomCity);
    }
}
