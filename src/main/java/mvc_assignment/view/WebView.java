package mvc_assignment.view;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import mvc_assignment.model.ModelObserverSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class WebView implements ModelObserver {

    private static final int WEBPAGE_PORT = 10_000;
    private static final int SOCKET_IO_PORT = 10_001;
    private Vertx vertex = Vertx.vertx();
    private HttpServer server = vertex.createHttpServer();
    private final SocketIOServer socketIOServer;
    private final ModelObserverSource model;

    public WebView(ModelObserverSource model) {
        this.model = model;
        this.model.addObserver(this);
        server.requestHandler(request -> {
            var response = request.response();
            response.putHeader("content-type", "text/html");
            response.end(getDashboardHtml());
        });
        server.listen(WEBPAGE_PORT);
        final Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(SOCKET_IO_PORT);
        socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addEventListener("stateRequest", byte[].class, new DataListener<byte[]>() {
            @Override
            public void onData(SocketIOClient client, byte[] data, AckRequest ackSender) throws Exception {
                client.sendEvent("modelUpdate", model.getState());
            }
        });
        socketIOServer.start();
        this.notifyModelUpdated();
    }

    private String getDashboardHtml() {
        var indexURL = getClass().getClassLoader().getResource("index.html");
        try {
            assert indexURL != null;
            var indexFile = new File(indexURL.toURI());
            return Files.readString(indexFile.toPath());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notifyModelUpdated() {
        int state = model.getState();
        socketIOServer.getBroadcastOperations().sendEvent("modelUpdate", state);
    }
}
