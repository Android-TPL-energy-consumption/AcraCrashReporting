package tpl.crashreporting.acra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.acra.ACRA;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.HttpSenderConfiguration;
import org.acra.config.HttpSenderConfigurationBuilder;
import org.acra.data.StringFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ACRA.init(getApplication(), new CoreConfigurationBuilder()
                //core configuration:
                .withBuildConfigClass(BuildConfig.class)
                .withReportFormat(StringFormat.JSON)
        );

        HttpSenderConfiguration endpoint = new HttpSenderConfigurationBuilder()
                .withUri("https://your.server.com/report")
                .build();
        System.out.println(endpoint.enabled());
    }

    public void onButtonClick(android.view.View view) {
        throw new RuntimeException("Test crash");
    }
}