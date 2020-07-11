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

package org.omnirom.device;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceScreen;

import org.omnirom.device.Preference.OCPreference;
import org.omnirom.device.Preference.CameraPreference;
import org.omnirom.device.Preference.ButtonSwapPreference;
import org.omnirom.device.Preference.FastChargePreference;

import static org.omnirom.device.Preference.OCPreference.ENABLE_OC_KEY;
import static org.omnirom.device.Preference.CameraPreference.RESTART_CAMERA_KEY;
import static org.omnirom.device.Preference.ButtonSwapPreference.BUTTONS_SWAP_KEY;
import static org.omnirom.device.Preference.FastChargePreference.USB_FAST_CHARGE_KEY;

public final class DeviceSettings extends PreferenceFragment {

    private static final String KEY_CATEGORY_OC = "overclock";
    private static final String KEY_CATEGORY_CAMERA = "Camera";
    private static final String KEY_CATEGORY_HW_BUTTONS = "hw_buttons";
    private static final String KEY_CATEGORY_USB_FASTCHARGE = "usb_fastcharge";

    private final String KEY_DEVICE_DOZE = "device_doze";
    private final String KEY_DEVICE_DOZE_PACKAGE_NAME = "org.lineageos.settings.doze";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main, rootKey);

        PreferenceScreen prefSet = getPreferenceScreen();
        
        OCPreference mOC = (OCPreference) prefSet.findPreference(ENABLE_OC_KEY);
        CameraPreference mCamera = (CameraPreference) prefSet.findPreference(RESTART_CAMERA_KEY);
        ButtonSwapPreference mButtonSwap = (ButtonSwapPreference) prefSet.findPreference(BUTTONS_SWAP_KEY);
        FastChargePreference mFastCharge = (FastChargePreference) findPreference(USB_FAST_CHARGE_KEY);


        mOC.setEnabled(OCPreference.FEATURE.isSupported());
        mCamera.setEnabled(CameraPreference.FEATURE.isSupported());
        mButtonSwap.setEnabled(ButtonSwapPreference.FEATURE.isSupported());
        mFastCharge.setEnabled(FastChargePreference.FEATURE.isSupported());
    }

    private boolean isAppInstalled(String uri) {
        PackageManager pm = getContext().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
}
