import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookRepository repository;

    @POST
    public Response addBook(Book book) {
        Book created = repository.create(book);
        return Response.status(201).entity(created).build();
    }

    @GET
    @Path("/borrowed/{readerId}")
    public Response getBorrowedBooks(@PathParam("readerId") Integer readerId) {
        List<Book> books = repository.findBorrowedByReader(readerId);
        return Response.ok(books).build();
    }
}
