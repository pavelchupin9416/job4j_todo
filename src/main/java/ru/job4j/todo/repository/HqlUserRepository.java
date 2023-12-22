package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlUserRepository implements UserRepository  {

    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        return crudRepository.run(session -> session.save(user)) ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional("from User WHERE login = :fLog and password = :fPas", User.class,
                Map.of("fLog", login, "fPas", password));
    }
}
