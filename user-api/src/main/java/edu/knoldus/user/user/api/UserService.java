package edu.knoldus.user.user.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.Service.topic;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.List;
import java.util.Optional;

/**
 * The Hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Hello.
 */
public interface UserService extends Service {

  /**
   * Example: curl http://localhost:9000/api/hello/Alice
   */


  /**
   * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
   * "Hi"}' http://localhost:9000/api/hello/Alice
   */

  ServiceCall<NotUsed, User> getUser(int id);
  ServiceCall<NotUsed, String> deleteUsers(int id);
  ServiceCall<User, String> updateUsers(int id);
  ServiceCall<User, String> addUser();
  ServiceCall<NotUsed, List<User>> getAllUser();
  /**
   * This gets published to Kafka.
   */


  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("hello").withCalls(
            Service.restCall(Method.GET, "/crud/readAll", this::getAllUser),
            Service.restCall(Method.GET, "/crud/read/:id", this::getUser),
            Service.restCall(Method.POST, "/crud/write", this::addUser),
            Service.restCall(Method.PUT, "/crud/update/:id", this::updateUsers),
            Service.restCall(Method.DELETE, "/crud/delete/:id", this::deleteUsers)
      ).withAutoAcl(true);
    // @formatter:on
  }
}
