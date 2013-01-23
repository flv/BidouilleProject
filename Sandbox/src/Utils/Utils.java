package Utils;

import android.content.Context;
import android.widget.Toast;

public abstract class Utils {

	public static void popDebug(Context context, String message) {
		Toast.makeText(context, message, 10000).show();
	}

}
