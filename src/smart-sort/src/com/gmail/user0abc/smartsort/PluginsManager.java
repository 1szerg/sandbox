package com.gmail.user0abc.smartsort;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PluginsManager
{
    Set<FileReaderPlugin> registeredPlugins = new HashSet<>();

    void registerPlugin(FileReaderPlugin plugin)
    {
        registeredPlugins.add(plugin);
    }

    public FileReaderPlugin getPluginForType(File doc)
    {
        for (FileReaderPlugin plugin : registeredPlugins) {
            if (plugin.isFileSupported(doc)) {
                return plugin;
            }
        }
        return null;
    }
}
