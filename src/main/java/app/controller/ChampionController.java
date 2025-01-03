package app.controller;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class ChampionController {

    public static void addRoutes(Javalin app, ConnectionPool dbConnection) {
        app.get("/champions/", ctx -> ctx.render("champions.html"));
        app.get("/champions/{name}", ctx -> showChampion(ctx, dbConnection));
    }

    private static void showChampion(Context ctx, ConnectionPool dbConnection) throws Exception {
        String name = ctx.pathParam("name");
        ctx.attribute("name", name);
        ctx.render("champion.html");
    }

}
