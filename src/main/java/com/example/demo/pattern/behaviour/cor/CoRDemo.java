package com.example.demo.pattern.behaviour.cor;

import java.io.IOException;

/**
 * Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers.
 * Upon receiving a request, each handler decides either to process the request
 * or to pass it to the next handler in the chain.
 *
 * https://refactoring.guru/design-patterns/chain-of-responsibility
 */
public class CoRDemo {
    private static Server server;
    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // All checks are linked. Client can build various chains using the same
        // components.
        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server)
        );

        middleware.add(new RoleCheckMiddleware());

        // print chain
        Middleware it = middleware;
        while (it != null){
            System.out.print(it.getClass().getSimpleName() + "  --->  ");
            it = it.getNext();
        }
        System.out.print("  NULL.");
        System.out.println();

        // Server gets a chain from client code.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
//        do {
//            String email = "admin@example.com";
//            String password = "admin_pass";
//            success = server.logIn(email, password);
//
//        } while (!success);

        success = false;
        do {
            //
            String email = "wrong@example.com";
            String password = "wrong_pass";
            success = server.logIn(email, password);
        } while (!success);
    }
}
