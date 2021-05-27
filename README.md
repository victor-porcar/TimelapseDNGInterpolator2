# DNG Images Settings Interpolator for Timelapses

Command line tool to interpolate settings values from a list of images in image format [DNG](https://www.adobe.com/content/dam/acom/en/products/photoshop/pdfs/dng_spec_1.4.0.0.pdf) to process smooth transitions in timelapses.

DNG is supported by many image/video application, specially the Adobe ecosystem  (Lightroom, Photoshop, Premiere..).

Please note that Adobe offers a [free tool](https://helpx.adobe.com/es/photoshop/using/adobe-dng-converter.html) to convert any propietary custom RAW format to DNG

## Motivation

I created this tool for my own use to process timelapses when using Adobe Lightroom, but it can be used with any other application supporting DNG.

Note that I created this tool just for fun, in a few hours, so it is basic and there is plenty of room to improve. Said that, it is fully working and I believe it could be useful for somebody else, so I decided to make it publicly available under license Apache 2.0. If you feel like to extend it, please do it!.

 
## How it works

Let's suppose there is a list of consecutives images in DNG format from a timelapse in a directory `D:\IMAGES`:

`Image01.dng` `Image02.dng` `Image03.dng` `Image04.dng` `Image05.dng` ... `Image20.dng` ... `Image99.dng`

At this point all images settings are set to 0 for all images (as it is taken from the camera)

Using a program like Lightroom or similar,  we set some settings in `Image01.dng` for example exposure is set to 1 and `Image20.dng` is set to 2

Now, it would be very nice to have a gradual increase of exposure from `Image01.dng` to `Image20.dng` as follows:

* `Image01.dng` exposure = 1
* `Image02.dng` exposure = 1.05
* `Image03.dng` exposure = 1.1
*  ... 
* `Image18.dng` exposure = 1.90
* `Image19.dng` exposure = 1.95
* `Image20.dng` exposure = 2

This is what this tool performs, it interpolates values of settings *between* two images:

`java -jar dng-settings-interpolator-X.X.jar D:\IMAGES D:\BACKUP -- files Image01.dng Image20.dng --settings crs:Exposure2012`

Where:

* `dng-settings-interpolator-X.X.jar` is the latest version of the tool (located in `/bin`directory of this repository)
* `D:\IMAGES` is the folder containing all the **.DNG files
* `D:\BACKUP` is a folder where the tool will make a copy before changing anything.
* --files : it means that subsequent parameters will be the names of files that defines the range to interpolate settings [Image01.dng, Image20.dng]
* --settings: it means that subsequent parameters will have the name the settings to interpolate.
* `crs:Exposure2012` is the name of the setting "Exposure" in a DNG file (see how to know the name of the settings below) 

Once the execution is over, the exposure value for range of images from `Image02.dng`  to `Image19.dng` will have been overwritten by the tool as per a linear interpolation function between values 1 (from `Image01.dng` ) and 2 (`Image20.dng`)


## Arguments

Broadly speaking, the tool suppors the following arguments:

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] --file [file1] [file2] ... [filen] --settings [setting1 Name] [setting2 name] ... [settingN name]`

Please note that `--settings` is optional, if not provided, the tool will interpolate ALL settings
 
#### View all available settings

`java -jar dng-settings-interpolator-X.X.jar --settings`

## Settings

The list of all available settings is [here](src/main/resources/allProperties.txt)

In order to know the name of the setting in a DNG image, the following trick can be used:
Open the file in Lightroom, change setting you want to know and set it to a particular value.
Once the DNG file is properly saved, open the file with any text editor and you'll see the XMP section as plain text (JSON). Look for the value you set before and you'll see the corresponding name of the setting.

### GoPro and Lightroom

[Here](workflow/Lightroom_and_GoPro.md) is an example of the workflow I use to process smooth transitions in Lightroom for GOPRO nightlapses.


 <br />

# Building a new release

* develop new code and increase manually version in pom.xml
* `mvn clean verify package`
* `git add .`
* `git commit -m "comment"`
* `git push origin master`
* the new version of the jar will be in /bin folder

