package keyone.keytwo.lesson07;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

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

    // вынесли всю работу с кнопками
    private void initView(View view) {
        initSwitchBackStack(view);
        initSwitchBackAsRemove(view);
        initSwitchBackDeleteBeforeAdd(view);
        initRadioAdd(view);
        initRadioReplace(view);
    }

    // инициализируем радио кнопку, отвечающую за замену фрагментов (замещает)
    private void initRadioReplace(View view) {
        RadioButton radioButtonReplace = view.findViewById(R.id.radioButtonReplace);
        radioButtonReplace.setChecked(Settings.isAddFragment); // нужно добавить еще одну переменную?
        radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isAddFragment = isChecked;
                saveSettings();
            }
        });
    }

    // инициализируем радио кнопку, отвечающую за добавление фрагментов (наслаивает)
    private void initRadioAdd(View view) {
        RadioButton radioButtonAdd = view.findViewById(R.id.radioButtonAdd);
        radioButtonAdd.setChecked(Settings.isAddFragment);
        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isAddFragment = isChecked;
                saveSettings();
            }
        });

    }

    // инициализируем переключатель, отвечающий за удаление фрагмента из стека перед добавлением нового
    private void initSwitchBackDeleteBeforeAdd(View view) {
        SwitchCompat switchCompatBackDeleteBeforeAdd = view.findViewById(R.id.switchBackDeleteBeforeAdd);
        switchCompatBackDeleteBeforeAdd.setChecked(Settings.isDeleteBeforeAdd);
        switchCompatBackDeleteBeforeAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isDeleteBeforeAdd = isChecked;
                saveSettings();
            }
        });
    }

    // инициализируем переключатель, отвечающий за работу кнопки Back, как очистителя/удалителя фрагментов
    private void initSwitchBackAsRemove(View view) {
        SwitchCompat switchCompatBackBackAsRemove = view.findViewById(R.id.switchBackAsRemove);
        switchCompatBackBackAsRemove.setChecked(Settings.isBackAsRemove);
        switchCompatBackBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.isBackAsRemove = isChecked;
                saveSettings();
            }
        });

    }

    // инициализируем переключатель, отвечающий за стек (включен - стек работает, выключен - стек не работает)
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

    // сохраняем настройки, чтобы они были доступны после перезагрузки приложения
    private void saveSettings() {
        SharedPreferences shared = requireActivity().getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(Settings.IS_BACK_STACK_USED, Settings.isBackStack);
        editor.putBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, Settings.isDeleteBeforeAdd);
        editor.putBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT, Settings.isBackAsRemove);
        editor.putBoolean(Settings.IS_ADD_FRAGMENT_USED, Settings.isAddFragment);
        editor.apply();
    }
}