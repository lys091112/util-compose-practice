package com.github.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class MockServer {
    private WireMockServer server;

    /**
     * server 的标识
     */
    private String tenant;

    /**
     * wiremock file locations
     */
    private String path;

    private int port;

    public MockServer(String tenant, int port) {
        this.port = port;
        this.tenant = tenant;
        this.path = "response/" + tenant;
    }

    public MockServer(MockConfig.Config config) {
        this(config.getTenant(), config.getPort());
    }

    /**
     * maxRequestJournalEntries 如果不进行设置，那么在InMemoryRequestJournal会不断累积历史记录，造成内存NoSpace
     */
    public void start() {
        server = new WireMockServer(WireMockConfiguration.options().port(port).maxRequestJournalEntries(100).usingFilesUnderClasspath(path));
        server.start();
    }

    public void stop() {
        if (null != server) {
            server.stop();
        }

    }
}
