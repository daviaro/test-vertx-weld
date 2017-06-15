package vertest4;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle
{
    @Inject
    public  Service service;
    private List<String> listID;

    @Override
    public void start() throws Exception {

        listID = new ArrayList<>();

        //vertx.createHttpServer().websocketHandler(
        //       serverWebSocket -> serverWebSocket.handler(serverWebSocket::writeBinaryMessage)).
        //     requestHandler(httpServerRequest -> {httpServerRequest.response().write("dsfs");}).listen(8080);
        vertx.createHttpServer().websocketHandler(websocket -> {
                    System.out.println("conectado");
                    listID.add(websocket.textHandlerID());
                    websocket.handler((Buffer buffer) -> {
                        System.out.println(websocket.headers().size());
                        for (String id : listID) {
                            if (!id.equals(websocket.textHandlerID())) {
                                vertx.eventBus().send(id, buffer.toString());
                            }
                        }
                    });
                    websocket.closeHandler(aVoid -> {
                                System.out.println(listID.remove(websocket.textHandlerID()));
                                System.out.println("list size "+listID.size());
                            }
                    );
                    /*System.out.println(config().getInteger("tiempo.render", 5000));
                    vertx.setPeriodic(config().getInteger("tiempo.render", 5000), id2 -> {
                        for (String id : listID) {
                            vertx.eventBus().send(id, String.valueOf(Math.random()));
                        }
                    });*/
                }
        ).requestHandler(req -> {
            if (req.uri().equals("/")) req.response().sendFile("ws.html");
        }).listen(8080);

    }
}
