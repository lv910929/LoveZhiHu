package com.lv.studio.lovezhihu.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lv.studio.lovezhihu.R;
import com.lv.studio.lovezhihu.utils.PreferenceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SettingsFragment mSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mSettingsFragment = new SettingsFragment();
        getFragmentManager().beginTransaction().replace(R.id.settings_container, mSettingsFragment).commit();
        initToolBar();
    }

    protected void initToolBar() {
        setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener,Preference.OnPreferenceClickListener{

        Preference fontSizePre;
        Resources resources;
        View view;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                           Bundle savedInstanceState) {
            view = super.onCreateView(inflater, container, savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
            resources=getResources();
            fontSizePre=findPreference(resources.getString(R.string.pre_fontsize_key));
            String font_size= PreferenceUtils.getPrefString(getActivity(), getString(R.string.pre_fontsize_key), "16");
            fontSizePre.setSummary(showFontSize(font_size));
            fontSizePre.setOnPreferenceChangeListener(this);
            return view;
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
        String showFontSize(String value) {
            switch (value) {
                case "16" :value="小号字体";break;
                case "18" :value="中号字体";break;
                case "20" :value="大号字体";break;
                default:break;
            }
            return value;
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if(preference.equals(fontSizePre)) {
                String prefsValue = newValue.toString();
                fontSizePre.setSummary(showFontSize(prefsValue));
            }
            return true;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {

            if(preference.equals(fontSizePre)) {
                fontSizePre.setDefaultValue(PreferenceUtils.getPrefString(getActivity(), "font_size", "16"));
            }
            return false;
        }
    }


}
