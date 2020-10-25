#
# Copyright (C) 2018 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


# Bluetooth
PRODUCT_PROPERTY_OVERRIDES += \
    bt.max.hfpclient.connections=1 \
    qcom.bluetooth.soc=rome \
    ro.bluetooth.dun=true \
    ro.bluetooth.hfp.ver=1.7 \
    ro.bluetooth.sap=true \
    ro.btconfig.if=uart \
    ro.btconfig.vendor=qcom \
    ro.btconfig.chip=QCA6164 \
    ro.btconfig.dev=/dev/ttyHS0

# Camera
PRODUCT_PROPERTY_OVERRIDES += \
    vendor.camera.aux.packagelist=com.android.camera,org.lineageos.snap,com.google.android.GoogleCameraTele \
    persist.ts.postmakeup=false \
    persist.ts.rtmakeup=false \
    persist.camera.stats.test=5 \
    camera.disable_zsl_mode=1 \
    persist.vendor.camera.HAL3.enabled=1 \
    persist.camera.nx531j.restart=0 \
    persist.camera.gyro.disable=0

# Hal1 
PRODUCT_PROPERTY_OVERRIDES += \
    camera.hal1.packagelist= com.android.camera,com.android.camera2 \
    vendor.camera.hal1.packagelist= com.android.camera,com.android.camera2

# Display
PRODUCT_PROPERTY_OVERRIDES += \
    ro.vendor.display.cabl=0

# NFC
PRODUCT_PROPERTY_OVERRIDES += \
    ro.nfc.port=I2C \
    persist.nfc.smartcard.config=SIM1,SIM2,eSE1

#mbn ota config
PRODUCT_PROPERTY_OVERRIDES += \
    persist.vendor.radio.hw_mbn_update=0 \
    persist.vendor.radio.sw_mbn_update=0 \
    persist.vendor.radio.start_ota_daemon=1

