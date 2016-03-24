package elec291group2.com.project2;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Kevin on 2016-03-23.
 */
public class Settings extends Fragment
{
    EditText ipField, portField, pinField;
    View view;
    SharedPreferences sharedPreferences;
    boolean notifications;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        sharedPreferences = this.getActivity().getSharedPreferences("serverData", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.settings, container, false);

        ipField = (EditText) view.findViewById(R.id.ip_field);
        portField = (EditText) view.findViewById(R.id.port_field);
        Button saveData = (Button) view.findViewById(R.id.data_button);

        pinField = (EditText) view.findViewById(R.id.pin_field);
        pinField.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button pinUpdate = (Button) view.findViewById(R.id.pin_button);

        ipField.setText(sharedPreferences.getString("IP", ""));
        portField.setText(sharedPreferences.getString("Port", ""));

        notifications = sharedPreferences.getBoolean("Notifications", false);
        ToggleButton notifToggle = (ToggleButton) view.findViewById(R.id.notif_toggle);
        notifToggle.setChecked(notifications);

        saveData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("IP", ipField.getText().toString());
                editor.putString("Port", portField.getText().toString());
                editor.commit();

                Menu menu = ((NavigationView) getActivity().findViewById(R.id.nav_view)).getMenu();
                menu.findItem(R.id.ip_address).setTitle("IP Address: " + sharedPreferences.getString("IP", "not entered"));
                menu.findItem(R.id.port).setTitle("Port: " + sharedPreferences.getString("Port", "not entered"));

                Toast.makeText(getActivity(), "IP address updated to " + ipField.getText().toString() + "\n" +
                        "Port updated to " + portField.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        pinUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newPin = pinField.getText().toString();
                if(newPin.length() < 1)
                    Toast.makeText(getActivity(), "PIN too short, no changes made", Toast.LENGTH_SHORT).show();
                else if(newPin.length() > 6)
                    Toast.makeText(getActivity(), "PIN too long, no changes made", Toast.LENGTH_SHORT).show();
                else
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("PIN", newPin);
                    editor.commit();
                    Toast.makeText(getActivity(), "PIN updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        notifToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                notifications = isChecked;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Notifications", notifications);
                editor.commit();
            }
        });

        return view;
    }
}