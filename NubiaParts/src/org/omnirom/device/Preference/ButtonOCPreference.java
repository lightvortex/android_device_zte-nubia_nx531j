/*
 * Copyright (C) 2019 The OmniROM Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.omnirom.device.Preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;

import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

import org.omnirom.device.utils.FileUtils;

public class DeviceSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String ENABLE_OC_KEY = "buttons_oc";
    private static final String OC_SYSTEM_PROPERTY = "persist.eas.mode";
    private static final boolean ENABLE_OC_DEFAULT_VALUE = false;
    private SwitchPreference mEnableOC;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main, rootKey);
        mEnableOC = (SwitchPreference) findPreference(ENABLE_OC_KEY);
        mEnableOC.setChecked(SystemProperties.getBoolean(OC_SYSTEM_PROPERTY, false));
        mEnableOC.setOnPreferenceChangeListener(this);
    }

   private void setEnableOC(boolean value) {
        if(value) {
            SystemProperties.set(OC_SYSTEM_PROPERTY, "1");
        } else {
            SystemProperties.set(OC_SYSTEM_PROPERTY, "0");
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        boolean value;
        String strvalue;
        if (ENABLE_OC_KEY.equals(key)) {
            value = (Boolean) newValue;
            mEnableOC.setChecked(value);
            setEnableOC(value);
            return true;
        }
        return true;
    }

}

