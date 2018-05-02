package edu.knoldus.user.user.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.List;
import java.util.Optional;

import com.lightbend.lagom.javadsl.api.transport.NotFound;
import edu.knoldus.user.user.api.UserService;
import edu.knoldus.user.user.api.User;
import edu.knoldus.user.user.impl.repository.UserRepository;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Implementation of the UserService.
 */
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    @Inject
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ServiceCall<NotUsed, List<User>> getAllUser() {
        return (ids) -> completedFuture(userRepository.getAllUser());
    }

    @Override
    public ServiceCall<NotUsed, User> getUser(int id) {
       return request -> {
           // UserRepository userdata = new UserRepository();
            return completedFuture(userRepository.getUser(id).orElseGet(() -> {throw new NotFound("user not found with this id "+id);}));
          // return null;
        };
    }

    @Override
    public ServiceCall<User, String> addUser() {
        return request -> completedFuture(userRepository.addUser(request));
    }

    @Override
    public ServiceCall<NotUsed, String> deleteUsers(int id) {
            return (ids) -> completedFuture(userRepository.deleteUser(id));
    }

    @Override
    public ServiceCall<User, String> updateUsers(int id) {
           return (user) -> completedFuture(userRepository.updateUser(id, user));
    }
}
