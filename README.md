# Ghidra-ESP8266

This is an ugly modification of the original hank's extension https://github.com/hank/ghidra-esp8266 to accept new ROM header at 0x1000 address as described here: https://richard.burtons.org/2015/05/17/decompiling-the-esp8266-boot-loader-v1-3b3/

It is not fully opperating yet. Now ghidra doesn't fail parsing ROM header (magic 0xe9) but it launch some warnings parsing segments after ROM header. Any help is wellcome!

Requires:

- You need ghidra-xtensa extension: https://github.com/yath/ghidra-xtensa 

Installing instructions:

 - git clone the project into a new folder
 - move into GhidraESP8266_2
 - execute gradle -PGHIDRA_INSTALL_DIR=/path_to.../ghidra_10.0.2_PUBLIC/
 - copy the new created GhidraESP8266_2/dist/ghidra_10.0.2_PUBLIC_20210909_GhidraESP8266_2.zip file into ghidra_10.0.2_PUBLIC/Extensions/Ghidra
 - Install the extension with the Ghidra 'Install Extensions' manager.
 - Restart Ghidra
