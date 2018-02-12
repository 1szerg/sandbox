package com.gmail.user0abc.smartsort;

public class SmartSorterContext
{
    static PluginsManager pluginsManager;

    public static PluginsManager getPluginsManager()
    {
        if (pluginsManager == null) {
            createPluginsManager();
        }
        return pluginsManager;
    }

    private static void createPluginsManager()
    {
        pluginsManager = new PluginsManager();
        pluginsManager.registerPlugin(new TextFileReaderPlugin());
    }
}
