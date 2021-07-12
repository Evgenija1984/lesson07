package keyone.keytwo.lesson07;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initSwitchBackStack(view);
    }

    private void initSwitchBackStack(View view) {
        SwitchCompat switchCompatBackStack = view.findViewById(R.id.switchBackStack);
        switchCompatBackStack.setChecked(Settings.isBackStack);
        switchCompatBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackStack = isChecked;
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        SharedPreferences shared = requireActivity().getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(Settings.IS_BACK_STACK_USED, Settings.isBackStack);
        editor.apply();
    }
}