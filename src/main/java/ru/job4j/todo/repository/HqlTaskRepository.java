package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlTaskRepository implements TaskRepository{

    private final SessionFactory sf;


    @Override
    public Task save(Task task) {
        Session session = sf.openSession();
        try {
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Task  WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return  result;
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET description = :fDes, created = :fCreat, done = :fDone WHERE id = :fId")
                    .setParameter("fDes", task.getDescription())
                    .setParameter("fCreat", task.getCreated())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Optional<Task> result = Optional.empty();
        try {
            session.beginTransaction();
            result =Optional.of(session.get(Task.class, id));
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;

        /*Query<Task> query = null;
        try {
            session.beginTransaction();
            query = session.createQuery(
                    "from Task as i where i.id = :fId", Task.class);
            query.setParameter("fId", id);
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return query.uniqueResultOptional();*/
    }

    @Override
    public Collection<Task> findAll() {
        Session session = sf.openSession();
        List<Task> query = null;
        try {
            session.beginTransaction();
            query = session.createQuery(
                    "from Task", Task.class).list();
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return query;
    }

    @Override
    public Collection<Task> findNewOrDone(boolean done) {
        Session session = sf.openSession();
        List<Task> query = null;
        try {
            session.beginTransaction();
            query = session.createQuery(
                    "from Task where done = :fDone", Task.class)
                    .setParameter("fDone", done).list();
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return query;
    }



}
