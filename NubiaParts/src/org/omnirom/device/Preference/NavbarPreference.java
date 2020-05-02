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

public final class NavbarPreference extends SwitchPreference implements
        Preference.OnPreferenceChangeListener {

    public static final String ENABLE_NAVBAR_KEY = "navbar";
    private static final String ENABLE_NAVBAR_PATH = "/data/tp/keypad_enable";
    private static final boolean ENABLE_NAVBAR_DEFAULT_VALUE = true;

    public static final KernelFeature<Boolean> FEATURE = new KernelFeature<Boolean>() {

        @Override
        public boolean isSupported() {
            return FileUtils.isFileWritable(ENABLE_NAVBAR_PATH);
        }

        @Override
        public Boolean getCurrentValue() {
            return FileUtils.getFileValueAsBoolean(ENABLE_NAVBAR_PATH, false);
        }

        @Override
        public boolean applyValue(Boolean newValue) {
            return FileUtils.writeValue(ENABLE_NAVBAR_PATH, newValue ? "1" : "0");
        }

        @Override
        public void applySharedPreferences(Boolean newValue, SharedPreferences sp) {
            sp.edit().putBoolean(ENABLE_NAVBAR_KEY, newValue).apply();
        }

        @Override
        public boolean restore(SharedPreferences sp) {
            if(!isSupported()) return false;

            boolean value = sp.getBoolean(ENABLE_NAVBAR_KEY, ENABLE_NAVBAR_DEFAULT_VALUE);
            return applyValue(value);
        }
    };

    public NavbarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value = (Boolean) newValue;
        if (FEATURE.applyValue(value))
            FEATURE.applySharedPreferences(value, getSharedPreferences());

        return true;
    }
}
