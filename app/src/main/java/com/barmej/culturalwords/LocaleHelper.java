package com.barmej.culturalwords;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class LocaleHelper {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Context setLocale(Context context, String language) {
        return updateResourcesLegacy(context, language);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale localeNew = new Locale(language);
        Locale.setDefault(localeNew);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLayoutDirection(localeNew);
        configuration.locale = localeNew;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }



}
