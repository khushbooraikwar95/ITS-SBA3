package  com.wellsfargo.fsd.its.services;



import java.util.List;
import java.util.Optional;

import com.wellsfargo.fsd.its.entities.User;
import com.wellsfargo.fsd.its.exceptions.NotFoundException;

public interface UserService {
    List<com.wellsfargo.fsd.its.entities.User> getAllUsers();
    User getUserById(int userId) throws NotFoundException;
    User addUser(User user);
    void deleteUser(int userId);
    User saveUser(User user) throws NotFoundException;
}
