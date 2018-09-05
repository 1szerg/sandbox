package com.gmail.user0abc.sandbox.tests;

import org.junit.Test;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPv6Test
{

    @Test
    public void IPv6Test() throws UnknownHostException
    {
        InetAddress address = Inet6Address.getByName("1000::");
        System.out.println(">"+address.getCanonicalHostName());
        System.out.println(">"+address.getHostAddress());
        System.out.println(">"+address.toString());
    }

}
