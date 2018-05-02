package edu.knoldus.user.user.impl.repository;

import com.lightbend.lagom.javadsl.api.deser.ExceptionMessage;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import com.lightbend.lagom.javadsl.api.transport.TransportErrorCode;
import edu.knoldus.user.user.api.User;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserRepository {
    static List<User> userList = new ArrayList<User>();
    static {
        userList.add(User.builder().id(1).name("Priyanka").build());
    }

    public String addUser(User user) {
        User newUser = User.builder().id(user.getId()).name(user.getName()).build();
        userList.add(newUser);
        return "user added";
    }

    public Optional<User> getUser(int id) {
        return userList.stream().filter(user -> user.getId() == id).findFirst();
        /*for(User user : userList) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;*/
    }

    public List<User> getAllUser() {
        return userList;
    }
    public String deleteUser(int id) {
        Optional<User> optionalUser = userList.stream().filter(user -> user.getId() == id).findFirst();
        User deletedUser = optionalUser.orElseThrow(() ->  new NotFound(TransportErrorCode.NotFound,
                new ExceptionMessage("gh","user does not exists")));
        userList.remove(deletedUser);
        /*for(User user : userList) {
            if(user.getId() == id) {
                userList.remove(user);
                return "Deleted";
            }
        }*/
        return "User deleted";
    }
    public String updateUser(int id, User newUser)  {
            Optional<User> optionalUser = userList.stream().filter((user -> user.getId() == id)).findFirst();
            User updatedUser = optionalUser.orElseGet(() -> {
                throw new NotFound("user does not exists");
            });
            userList.remove(updatedUser);
            userList.add(newUser);
        /*for(User user : userList) {
            if(user.getId() == id) {
                userList.remove(user);
                userList.add(newUser);
                return "updated";
            }
        }*/
            return "User updated";
    }
}
