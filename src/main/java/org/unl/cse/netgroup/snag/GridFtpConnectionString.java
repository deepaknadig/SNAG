package org.unl.cse.netgroup.snag;

import org.onlab.packet.IpAddress;
import org.onosproject.net.PortNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Deepak Nadig Anantha <deepnadig@gmail.com> on 8/15/17.
 */
public class GridFtpConnectionString {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private IpAddress srchost, dsthost;
    private PortNumber srcport, dstport;
    private String username, event, filename;

    public GridFtpConnectionString(String username,
                                   IpAddress srchost,
                                   IpAddress dsthost,
                                   PortNumber srcport,
                                   PortNumber dstport,
                                   String event,
                                   String filename) {
        this.srchost = srchost;
        this.dsthost = dsthost;
        this.srcport = srcport;
        this.dstport = dstport;
        this.username = username;
        this.event = event;
        this.filename = filename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public IpAddress getSrchost() {
        return srchost;
    }

    public void setSrchost(IpAddress srchost) {
        this.srchost = srchost;
    }

    public IpAddress getDsthost() {
        return dsthost;
    }

    public void setDsthost(IpAddress dsthost) {
        this.dsthost = dsthost;
    }

    public PortNumber getSrcport() {
        return srcport;
    }

    public void setSrcport(PortNumber srcport) {
        this.srcport = srcport;
    }

    public PortNumber getDstport() {
        return dstport;
    }

    public void setDstport(PortNumber dstport) {
        this.dstport = dstport;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void logStats(GridFtpConnectionString connectionString) {
    }
}
