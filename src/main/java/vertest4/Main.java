package vertest4;

import io.vertx.core.Vertx;
import org.jboss.weld.vertx.WeldVerticle;

/**
 * Created by leydi on 15/06/17.
 */
public class Main {

    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        final WeldVerticle weldVerticle = new WeldVerticle();
        vertx.deployVerticle(weldVerticle, result -> {
            if (result.succeeded()) {
                // Deploy Verticle instance produced by Weld
                vertx.deployVerticle(weldVerticle.container().select(App.class).get());
            }
        });
    }
}
