package br.com.pizzosoft.service;

import br.com.pizzosoft.entity.Todo;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.hibernate.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TodoService {

    public Uni<Todo> findById(long id) {
        return Todo.findById(id);
    }
    public Uni<List<Todo>> listAllTodos(String sort) {
        if (sort == null) {
            return Todo.listAll();
        }
        return Todo.listAll(Sort.by(sort));
    }

    @ReactiveTransactional
    public Uni<Todo> create(Todo todo) {
        return todo.persistAndFlush();
    }

    @ReactiveTransactional
    public Uni<Todo> update(Todo todo) {
        return findById(todo.id)
                .chain(t -> Todo.getSession())
                .chain(s -> s.merge(todo));
    }

    @ReactiveTransactional
    public Uni<Void> delete(long id) {
        return findById(id)
                .chain(Todo::delete);
    }
}
