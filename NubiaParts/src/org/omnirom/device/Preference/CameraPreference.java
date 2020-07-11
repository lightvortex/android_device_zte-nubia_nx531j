/*
 * Copyright (c) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.omnirom.device.Preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.preference.ListPreference;
import androidx.preference.Preference;

public final class CameraPreference extends ListPreference implements
        Preference.OnPreferenceChangeListener {
        
    public static final String RESTART_CAMERA_KEY = "key_camera_restart";
    private static final String CAMERA_DEFAULT_PROFILE = "0";
    private static final String CAMERA_RESTART_PROPERTY = "persist.camera.nx531j.restart";

    public static final KernelFeature<String> FEATURE = new KernelFeature<String>() {

        @Override
        public boolean isSupported() {
            return !TextUtils.isEmpty(SystemProperties.get(CAMERA_RESTART_PROPERTY, null));
        }

        @Override
        public String getCurrentValue() {
            return SystemProperties.get(CAMERA_RESTART_PROPERTY, CAMERA_DEFAULT_PROFILE);
        }

        @Override
        public boolean applyValue(String newValue) {
            SystemProperties.set(CAMERA_RESTART_PROPERTY, newValue);
            return newValue.equals(getCurrentValue());
        }

        @Override
        public void applySharedPreferences(String newValue, SharedPreferences sp) {
            sp.edit().putString(RESTART_CAMERA_KEY, newValue).apply();
        }

        @Override
        public boolean restore(SharedPreferences sp) {
            if(!isSupported()) return false;

            String value = sp.getString(RESTART_CAMERA_KEY, CAMERA_DEFAULT_PROFILE);
            return applyValue(value);
        }
    };

    public CameraPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String value = newValue.toString();
        if (FEATURE.applyValue(value))
            FEATURE.applySharedPreferences(value, getSharedPreferences());

        return true;
        }
}
