package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void dropUserById(int id) {
        userDAO.dropUserById(id);
    }

    @Override
    public void updateUserNameById(int id, User user) {
        userDAO.updateUserNameById(id, user);
    }

}
