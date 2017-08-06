package me.nullex.installer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public final class Installer {
	
	public static String client_name = "Name";
	public static String client_version = "Version (can be somthing like 4.0 or b1 or somthing like that)";
	public static String jar_url = "https://use somthing like pomf.cat or mixtape.moe";
	public static String json_url = "https://use somthing like pomf.cat or mixtape.moe";
	
	
    public static void main(String[] a1) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Prompt prompt = new Prompt();
        prompt.setVisible(true);
    }
	
    public static void install() {
        File dir = Installer.getMinecraftDirectory();
        try {
            Installer.extractClient(dir);
        }
        catch (URISyntaxException v3) {
            v3.printStackTrace();
            Prompt.report("ERROR", "FFailed to download .jar file! Are you connected to the internet?", 0);
            throw new RuntimeException("QUIET");
        }
        catch (IOException e) {
            e.printStackTrace();
            Prompt.report("ERROR", "Failed to download .json file! Are you connected to the internet?", 0);
            throw new RuntimeException("QUIET");
        }
        try {
        	if (Prompt.profile) {
        		Installer.patchLauncher(dir);
        	}
        }
        catch (IOException v2) {
            v2.printStackTrace();
            Prompt.report("ERROR", "Failed to add new profile to launcher!", 0);
        }
        Prompt.report("Success", client_name + " has beed installed!", 1);
    }

    private static void patchLauncher(File dir) throws IOException {
        File profiles = new File(dir, "launcher_profiles.json");
        JsonObject json = Json.parse((Reader)new FileReader(profiles)).asObject();
        json.get("profiles").asObject().add(client_name, (JsonValue)new JsonObject().add("name", client_name).add("lastVersionId", client_name));
        json.set("selectedProfile", client_name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(profiles));
        json.writeTo((Writer) writer);
        writer.close();
    }

    private static void extractClient(File dir) throws URISyntaxException, IOException {
        File version = new File(dir, "versions/" + client_name);
        if (!version.exists()) {
            version.mkdirs();
        }
        Installer.downloadJar(new File(version, client_name + ".jar"));
        Installer.downloadJson(new File(version, client_name + ".json"));
    }

    private static void downloadJson(File dest) throws IOException {
        URL website = new URL(json_url);
        InputStream in = website.openStream();
        Throwable throwable = null;
        try {
            Files.copy(in, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            if (in != null) {
                if (throwable != null) {
                    try {
                        in.close();
                    }
                    catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                } else {
                    in.close();
                }
            }
        }
    }
    
    private static void downloadJar(File dest) throws IOException {
        URL website = new URL(jar_url);
        InputStream in = website.openStream();
        Throwable throwable = null;
        try {
            Files.copy(in, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            if (in != null) {
                if (throwable != null) {
                    try {
                        in.close();
                    }
                    catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                } else {
                    in.close();
                }
            }
        }
    }
    
    private static File getMinecraftDirectory() {
        String home = System.getProperty("user.home", ".");
        String os = System.getProperty("os.name").toLowerCase();
        File dir = null;
        if (os.contains("win")) {
            String appdata = System.getenv("APPDATA");
            dir = appdata == null ? new File(home, ".minecraft/") : new File(appdata, ".minecraft/");
        } else if (os.contains("mac")) {
            dir = new File(home, "Library/Application Support/minecraft");
        } else if (!os.contains("linux") && !os.contains("unix")) {
            dir = os.contains("sunos") || os.contains("solaris") ? new File(home, ".minecraft/") : new File(home, "minecraft/");
        }
        if (!dir.exists() && !dir.mkdirs()) {
            Prompt.report("ERROR", "Couldn't find the Minecraft directory! Bummer! Please install Minecraft first!", 0);
            throw new RuntimeException("Minecraft directory could not be found or created.");
        }
        return dir;
    }
}

