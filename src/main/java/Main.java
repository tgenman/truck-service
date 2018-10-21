import com.mpoznyak.configuration.Constants;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Manager;
import com.mpoznyak.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Max Poznyak
 * on 21/10/2018  at 21:07
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction= manager.getTransaction();
        transaction.begin();
        Driver man = new Driver();
        man.setFirstName("Максим");
        man.setLastName("Максимов");
        User user = new User();
        user.setCompanyId(Long.valueOf(456));
        user.setPassword("456");
        user.setDriver(man);
        manager.persist(man);
        manager.persist(user);
        transaction.commit();
        manager.close();
        factory.close();

    }
}
