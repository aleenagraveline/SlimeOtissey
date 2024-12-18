package Level;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

public class FlagManager {
    protected HashMap<String, Boolean> flags = new HashMap<>();

    public void addFlag(String flagName) {
        flags.put(flagName, false);
    }

    public void addFlag(String flagName, boolean startingValue) {
        flags.put(flagName, startingValue);
    }

    public boolean hasFlag(String flagName) {
        return flags.containsKey(flagName);
    }

    public void setFlag(String flagName) {
        if (flags.containsKey(flagName)) {
            flags.put(flagName, true);
        }
    }

    public void unsetFlag(String flagName) {
        if (flags.containsKey(flagName)) {
            flags.put(flagName, false);
        }
    }

    public void reset() {
        for (Entry<String, Boolean> entry : flags.entrySet()) {
            entry.setValue(false);
        }
    }

    public boolean isFlagSet(String flagName) {
        if (flags.containsKey(flagName)) {
            return flags.get(flagName);
        }
        return false;
    }

    public int totalFlags() {
        return flags.size();
    }

    public String flagsToString() {
        String strFlags = "";

        for (String key: flags.keySet()) {
            strFlags = strFlags + key + ":" + flags.get(key) + " ";
        }

        return strFlags;
    }
}
