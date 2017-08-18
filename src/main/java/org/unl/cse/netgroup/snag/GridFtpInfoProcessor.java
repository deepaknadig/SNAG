package org.unl.cse.netgroup.snag;

import com.google.common.collect.HashMultimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Deepak Nadig Anantha <deepnadig@gmail.com> on 8/15/17.
 */
public class GridFtpInfoProcessor {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static HashMultimap<String, GridFtpConnectionString> usCmsPoolMap = HashMultimap.create();
    private static HashMultimap<String, GridFtpConnectionString> cmsProdlMap = HashMultimap.create();
    private static HashMultimap<String, GridFtpConnectionString> lcgAdminMap = HashMultimap.create();
    private static HashMultimap<String, GridFtpConnectionString> cmsPhedexMap = HashMultimap.create();
    private static HashMultimap<String, GridFtpConnectionString> otherMap = HashMultimap.create();
    private static HashMultimap<String, GridFtpConnectionString> ligoMap = HashMultimap.create();
}
