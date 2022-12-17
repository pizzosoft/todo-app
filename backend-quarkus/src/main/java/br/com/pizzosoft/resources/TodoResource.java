package br.com.pizzosoft.resources;

import br.com.pizzosoft.entity.Todo;
import br.com.pizzosoft.service.TodoService;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/todos")
public class TodoResource {

    private final TodoService todoService;

    @Inject
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    public Uni<List<Todo>> get(@QueryParam("sort") String queryParam) {
        System.out.println(queryParam);
        return todoService.listAllTodos(queryParam);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Uni<Todo> create(Todo todo) {
        return todoService.create(todo);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Uni<Todo> update(@PathParam("id") long id, Todo todo) {
        todo.id = id;
        return todoService.update(todo);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") long id) {
        return todoService.delete(id);
    }

}
