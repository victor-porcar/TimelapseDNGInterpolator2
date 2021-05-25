# DNG Images Settings Interpolator

This is a Java tool to interpolate settings from a list of images in format [DNG](https://www.adobe.com/content/dam/acom/en/products/photoshop/pdfs/dng_spec_1.4.0.0.pdf), which is an open RAW specification for images created by Adobe.

I created for my own use, just for fun. However, I believe it could be useful for somebody else, so I make it publicly available under license Apache 

Please note that Adobe offers a [free tool](https://helpx.adobe.com/es/photoshop/using/adobe-dng-converter.html) to convert any RAW custom format to DNG

 
## How it works

Let's suppose there is a list of consecutives images in dng format from a timelapse in a directory

`Image01.dng` `Image02.dng` `Image03.dng` `Image04.dng` `Image05.dng` ... `Image20.dng` ... `Image99.dng`

At this point all images has the "exposure" parameter set to 0 (just as it is taken from the camera)

Using a program like Lightroom or similar, we set the exposure to `Image20.dn` to value 1.

At this point, it would be very nice to have a gradual increase of exposure parameter in a range `Image01.dng` to in `Image20.dng` to have:

* `Image01.dng` exposure = 0
* `Image02.dng` exposure = 0.05
* `Image03.dng` exposure = 0.1
..
* `Image20.dng` exposure = 1

This could be using this tool as follows:

`java -jar dng-settings-interpolator-X.X.jar D:\IMAGES D:\BACKUP Image01.dng Image20.dng crs:Exposure2012`

Where:

* `dng-settings-interpolator-X.X.jar` is the latest version of the `dng-settings-interpolator-X.X.jar` file (located in `/bin`directory) 
* `D:\IMAGES` is the folder containing all the **.dng files
* `D:\BACKUP` is a folder where the tool will make a copy before changing any file
* `crs:Exposure2012` is the name of Exposure setting in XMP definitions of the DNG file (see settings chapter below) 



## Parameters


#### Interpolate a set of settings 

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] [file1] [file2] [setting1 Name] [setting2 name] ... [settingN name]`

 
#### Interpolate all settings at one

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] [file1] [file2] *`
 
#### View all available settings

`java -jar dng-settings-interpolator-X.X.jar --settings`

## Settings

The list of all available settings is [here](src/main/resources/allProperties.txt)

In order to know the name of the setting in the DNG file, the following trick can be used:
Edit the setting you want to know the name with Lightroom (or similar) and set it to a particular value.
Once the dng file is properly saved, open the dng file with any text editor and you'll see the XMP section as plain text (JSON). Look for the value you set before and you'll see the corresponding name of the setting.


## Building a new release

* develop new code and increase manually version in pom.xml
* `mvn clean package`
* `git add .`
* `git commit -m "comment"`
* `git push origin master`
* the new version of the jar will be in /bin folder
