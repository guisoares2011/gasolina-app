package drum.com.gasolinaapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import drum.com.gasolinaapp.R;

public class DialogError extends Dialog {

    public DialogError(Context context, String title, String message, String button_text){
        super(context);
        this.setContentView(R.layout.fragment_custom_error_dialog);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ((TextView) this.findViewById(R.id.error_title)).setText(title);
        ((TextView) this.findViewById(R.id.error_message)).setText(message);

        //Set Click event to modal button
        Button button = (Button) this.findViewById(R.id.button_dialog_error);
        button.setText(button_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventButtonClickModal();
            }
        });
    }

    public void eventButtonClickModal(){
        dismiss();
    }
}
