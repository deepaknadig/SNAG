/*
 * Copyright 2017-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.unl.cse.netgroup.snag.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.onlab.packet.IpAddress;
import org.onosproject.net.PortNumber;
import org.onosproject.rest.AbstractWebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unl.cse.netgroup.snag.GridFtpConnectionString;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 * SNAG web resource.
 */
@Path("")
public class SnagWebResource extends AbstractWebResource {

    private final ObjectNode root = mapper().createObjectNode();
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static GridFtpConnectionString connectionString;
    /**
     * Get SNAG Info.
     *
     * @return 200 OK
     */
    @GET
    @Path("info")
    public Response getGreeting() {
        ObjectNode node = mapper().createObjectNode().put("SNAG", "Information");
        return ok(node).build();
    }

    /**
     * Obtains GridFTP transfer information
     * Instructions description:
     * <br>
     * Criteria description:
     *
     * @return status of the request - CREATED if the JSON is correct,
     * BAD_REQUEST if the JSON is invalid
     */
    @POST
    @Path("tcp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postedFlows(InputStream stream) throws IOException {

        connectionString = jsonToConnectionInfo(stream);
        connectionString.logStats(connectionString);
        
        return Response.ok(root).build();
    }

    private GridFtpConnectionString jsonToConnectionInfo(InputStream stream) {
        JsonNode node;
        try {
            node = mapper().readTree(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to parse GridFTP application POST request.", e);
        }

        String username = node.path("username").asText(null);

        IpAddress srchost = IpAddress.valueOf(node.path("srchost").asText(null));
        IpAddress dsthost = IpAddress.valueOf(node.path("dsthost").asText(null));
        PortNumber srcport = PortNumber.portNumber(node.path("srcport").asText(null));
        PortNumber dstport = PortNumber.portNumber(node.path("dstport").asText(null));

        String event = node.path("event").asText(null);
        String filename = node.path("filename").asText(null);

        if (srchost != null && dsthost != null && srcport != null && dstport != null && username != null && event != null && filename !=null) {
            return new GridFtpConnectionString(username, srchost, dsthost, srcport, dstport, event, filename);
        }
        else {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
    }

}
