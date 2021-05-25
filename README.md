# DNG Settings Interpolator

This is a tool to interpolate settings from a list of images in format [DNG](https://www.adobe.com/content/dam/acom/en/products/photoshop/pdfs/dng_spec_1.4.0.0.pdf), which is an open RAW specification for images created by Adobe.

## Instructions

Download latest version of the `dng-settings-interpolator-X.X.jar` file (located in `/bin`directory) 

#### Interpolate a set of settings 

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] [file1] [file2] [setting1 Name] [setting2 name] ... [settingN name]`

 
#### Interpolate all settings

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] [file1] [file2] [setting1 Name] *`
 
#### View all available settings

`java -jar dng-settings-interpolator-X.X.jar --settings`


## Building a new release

* develop new code and increase manually version in pom.xml
* commit
* push origin master
* mvn clean package
* the new version of the jar is in /bin folder
