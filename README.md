# DNG Settings Interpolator

This is a tool to interpolate settings from a 

## Instructions


#### Interpolate a set of settings

java -jar dng-settings-interpolator-X.X.jar [working_directory] [backup_directory] [file1] [file2] [setting1 Name] [setting2 name] ... [settingN name]

Example:

java -jar dng-settings-interpolator-X.X.jarr d:\dng_directory d:\backup file15.dng file55.dng crs:ParametricHighlights crs:Saturation crs:Exposure2012


#### Interpolate all settings

com.github.victormpcmun.dngsettingsinterpolator.DNGSettingsInterpolator d:\dng_directory d:\backup file15.dng file55.dng  *

It will interpolate the values of all settings for files in range [file16.dng, file54.dng]


#### View all available settings

com.github.victormpcmun.dngsettingsinterpolator.DNGSettingsInterpolator --help


## Building a new release

* increase manually version in pom.xml
* commit
* mvn clean package
* the new version of the jar is in /bin folder
