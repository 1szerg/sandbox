package java.resttest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ericsson.granite.oss.core.physical.dto.Site;
import com.ericsson.granite.oss.core.physical.service.SiteService;
import com.telcordia.granite.sdk.object.dto.ManagedObjectFetchSpec;
import com.telcordia.granite.sdk.service.GraniteServiceFactory;
import com.telcordia.granite.sdk.service.RestGraniteServiceFactory;
import com.telcordia.granite.sdk.session.GraniteCredentials;

public class SiteClient
{

    private final static String USERNAME = "sysadm";
    private final static String PASSWORD = "";
    private final static String DATABASE_HOST = "hammond";
    private final static String DATABASE_NAME = "install";

    private static GraniteServiceFactory serviceFactory;
    private static SiteService siteService;

    @BeforeClass
    public static void startUp() throws Exception
    {
        final GraniteCredentials graniteCredentials = new GraniteCredentials();
        graniteCredentials.setUserName(USERNAME);
        graniteCredentials.setPassword(PASSWORD);
        graniteCredentials.setDatabaseHostName(DATABASE_HOST);
        graniteCredentials.setDatabaseName(DATABASE_NAME);

        serviceFactory = new RestGraniteServiceFactory("https://hammond.cc.telcordia.com:7243/oss-core-ws", graniteCredentials);

        siteService = serviceFactory.getService(SiteService.class);
    }

    @AfterClass
    public static void shutDown() throws Exception
    {
        serviceFactory.logout();
    }

    @Test
    public void testSite() throws Exception
    {
        final Site[] sites = siteService.queryByExample(new Site(), new ManagedObjectFetchSpec());
        System.out.println(sites.length);
    }
}
