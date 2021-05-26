# DNG Images Settings Interpolator

This is a Java tool to interpolate settings values from a list of images in format [DNG](https://www.adobe.com/content/dam/acom/en/products/photoshop/pdfs/dng_spec_1.4.0.0.pdf), which is an open RAW specification for images created by Adobe.

Many image/video processing application do support this format, specially the ones from Adobe  (Lightroom, Photoshop, Premiere..).

Please note that Adobe offers a [free tool](https://helpx.adobe.com/es/photoshop/using/adobe-dng-converter.html) to convert any propietary custom RAW format to DNG

I created this tool for my own use to have steady transitions in timelapses, specially with light changing conditions. 

The project is what I call a *one afternoon* project (written in a few hours), so there is a plenty of room to improve. Said that, I believe it is fully working and it could be useful for somebody else, so I decided to make it publicly available under license Apache 2.0



 
## How it works

Let's suppose there is a list of consecutives images in dng format from a timelapse in the directory `D:\IMAGES`:

`Image01.dng` `Image02.dng` `Image03.dng` `Image04.dng` `Image05.dng` ... `Image20.dng` ... `Image99.dng`

At this point the setting exposure is set to 0 for all images (just as it is taken from the camera)

Using a program like Lightroom or similar, the exposure for `Image01.dng` is set to `1`  and for `Image20.dng` is set to `2`

Now, it would be very nice to have a gradual increase of exposure parameter in a range of images `Image01.dng` to `Image20.dng` as follows:

* `Image01.dng` exposure = 1
* `Image02.dng` exposure = 1.05
* `Image03.dng` exposure = 1.1
*  ... 
* `Image18.dng` exposure = 1.90
* `Image19.dng` exposure = 1.95
* `Image20.dng` exposure = 2

This could be achieved automatically using this tool as follows:

`java -jar dng-settings-interpolator-X.X.jar D:\IMAGES D:\BACKUP Image01.dng Image20.dng crs:Exposure2012`

Where:

* `dng-settings-interpolator-X.X.jar` is the latest version of the `dng-settings-interpolator-X.X.jar` file (located in `/bin`directory) 
* `D:\IMAGES` is the folder containing all the **.dng files
* `D:\BACKUP` is a folder where the tool will make a copy before changing any file
* `crs:Exposure2012` is the name of Exposure setting in XMP definitions of the DNG file (see settings chapter below) 

Once the execution is over, the exposure value for range of images from `Image02.dng`  to `Image19.dng` will have been overwritten by the tool as per a linear interpolation function between values 1 (from `Image01.dng` ) and 2 (`Image20.dng`)


## Parameters


#### Interpolate a set of settings 

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] [file1] [file2] [setting1 Name] [setting2 name] ... [settingN name]`

 
#### Interpolate all settings at once

Use the wildcard * 

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] [file1] [file2] *`
 
#### View all available settings

`java -jar dng-settings-interpolator-X.X.jar --settings`

## Settings

The list of all available settings is [here](src/main/resources/allProperties.txt)

In order to know the name of the setting in the DNG file, the following trick can be used:
Edit the setting you want to know the name with Lightroom (or similar) and set it to a particular value.
Once the dng file is properly saved, open the dng file with any text editor and you'll see the XMP section as plain text (JSON). Look for the value you set before and you'll see the corresponding name of the setting.

## My Workflow

[Lightroom and GoPro](LIGHTROOM_and_GOPRO.md)

[Lightroom and Nikon](LIGHTROOM_and_NIKON.md)

## Building a new release

* develop new code and increase manually version in pom.xml
* `mvn clean package`
* `git add .`
* `git commit -m "comment"`
* `git push origin master`
* the new version of the jar will be in /bin folder
