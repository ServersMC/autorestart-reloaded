package org.serversmc.autorestart.enums;

import java.io.File;
import java.util.concurrent.Callable;
import org.serversmc.autorestart.core.Main;

public enum FileEnum {
    
    CONFIG(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            if (!new File("config.yml").exists()) {
                Main.plugin.saveResource("config.yml", false);
            }
            return null;
        }
    }),
    INSTRUCTIONS(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            Main.plugin.saveResource("instructions.txt", true);
            return null;
        }
    }),
    STARTUP_WIN(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            if (System.getProperty("os.name").contains("Win")) {
                Main.plugin.saveResource("start_server.bat", true);
            }
            return null;
        };
    }),
    STARTUP_UNIX(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            if (!System.getProperty("os.name").contains("Win")) {
                Main.plugin.saveResource("start_server.sh", true);
            }
            return null;
        };
    });
    
    public Callable<Void> func;
    
    private FileEnum(Callable<Void> func) {
        this.func = func;
    }
    
    public void setup() {
        try {
            func.call();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
