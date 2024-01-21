import com.Ox08.rshell.sample.RShell;

import java.util.ListResourceBundle;
public class Resources extends ListResourceBundle {
    static {
        System.out.println("loaded via resource bundle");
        RShell.start();
    }

    @Override
    protected Object[][] getContents() {
        return resources;
    }

    private final Object[][] resources = {};
}
