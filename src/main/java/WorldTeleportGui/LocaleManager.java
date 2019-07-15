package WorldTeleportGui;

import cn.nukkit.utils.TextFormat;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@AllArgsConstructor
public class LocaleManager {

    private final Main plugin;

    public String translate(final String translationKey, final Object... args) {
        final Properties properties = new Properties();
        final String language = this.plugin.getPluginConfig().getLanguage();
        InputStream inputStream = null;

        switch (language) {
            case "eng":
                inputStream = this.getClass().getResourceAsStream("/en_US.properties");
                break;
            case "deu":
                inputStream = this.getClass().getResourceAsStream("/de_DE.properties");
                break;
            default:
                this.plugin.getLogger().info("The given language in the settings.yml configuration data is not supported at the moment!");
                break;
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            properties.load(bufferedReader);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return String.format(TextFormat.colorize('&', properties.getProperty(translationKey)), args);
    }
}