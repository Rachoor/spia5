package tacos.repos;

import org.springframework.data.repository.CrudRepository;
import tacos.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
}
