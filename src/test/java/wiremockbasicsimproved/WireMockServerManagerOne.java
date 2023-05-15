package wiremockbasicsimproved;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockServerManagerOne {
    private WireMockServer wireMockServer;

    public void startServer() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
    }

    public void stopServer() {
        wireMockServer.stop();
    }

    public int getServerPort() {
        return wireMockServer.port();
    }
}
