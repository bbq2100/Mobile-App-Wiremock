package novatec.cg.ema.wiremock.ui;

import android.app.Activity;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import novatec.cg.ema.wiremock.R;

public final class CroutonUtil {

    private CroutonUtil() {
    }

    public static void showError(Activity who) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(who, who.getString(R.string.networt_error), Style.ALERT).show();
    }

    public static void showInfo(Activity who, String what) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(who, what, Style.INFO).show();
    }

    public static void showSuccess(Activity who, String what) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(who, what, Style.CONFIRM).show();
    }
}
