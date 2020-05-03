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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import org.omnirom.device.Preference.OCPreference;
import org.omnirom.device.Preference.BacklightPreference;
import org.omnirom.device.Preference.ButtonSwapPreference;
import org.omnirom.device.Preference.FastChargePreference;

public final class Startup extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // Execute boot jobs
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

            BacklightPreference.FEATURE.restore(sp);
            OCPreference.FEATURE.restore(sp);
            ButtonSwapPreference.FEATURE.restore(sp);
            FastChargePreference.FEATURE.restore(sp);
        }
    }
}
