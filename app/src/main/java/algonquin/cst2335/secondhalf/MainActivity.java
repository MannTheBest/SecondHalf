package algonquin.cst2335.secondhalf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * This is the main page for the app. It ask the user to enter a password to login and checks the password complexity at the same time.
 *
 * @author Man Patel
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This is the textview from main page. */
    private TextView txt;
    /** This is the button from main page. */
    private Button btn;
    /** This is the editText from main page. */
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);

        btn.setOnClickListener( v -> {
            String password = editText.getText().toString();

            checkPasswordComplexity(password, this);
        });
    }

    /**
     * This function checks if the password is strong and correctly written.
     *
     * @param password: it is the password written by user.
     * @param context: the context of the application that is running the function
     * @return Returns true if password is good, else returns false and creates a toast
     */
    public static boolean checkPasswordComplexity(String password, Context context) {
        // Define regular expressions for each requirement
        String uppercaseRegex = ".*[A-Z].*";
        String lowercaseRegex = ".*[a-z].*";
        String digitRegex = ".*\\d.*";
        String specialSymbolRegex = ".*[#$%^&*!@?].*";

        // Check each requirement
        boolean hasUppercase = Pattern.matches(uppercaseRegex, password);
        boolean hasLowercase = Pattern.matches(lowercaseRegex, password);
        boolean hasDigit = Pattern.matches(digitRegex, password);
        boolean hasSpecialSymbol = Pattern.matches(specialSymbolRegex, password);

        // Display Toast messages based on missing requirements
        if (!hasUppercase) {
            showToast(context, "Your password does not have an upper case letter");
            return false;
        }
        if (!hasLowercase) {
            showToast(context, "Your password does not have a lower case letter");
            return false;
        }
        if (!hasDigit) {
            showToast(context, "Your password does not have a number");
            return false;
        }
        if (!hasSpecialSymbol) {
            showToast(context, "Your password does not have a special symbol");
            return false;
        }
        return true;
    }

    private static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}