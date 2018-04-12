package com.explore.web.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;

/**
 * Created by xiaohb on 2018/1/8.
 */
public class MockServer {

    public static void main(String[] args) {
        WireMock.configureFor(8602);
        WireMock.removeAllMappings();
    }


}
