# DNG Images Settings Interpolator

Command line tool to interpolate settings values from a list of images in format [DNG](https://www.adobe.com/content/dam/acom/en/products/photoshop/pdfs/dng_spec_1.4.0.0.pdf)

DNG is supported by many image/video application, specially the ones from Adobe  (Lightroom, Photoshop, Premiere..).

Please note that Adobe offers a [free tool](https://helpx.adobe.com/es/photoshop/using/adobe-dng-converter.html) to convert any propietary custom RAW format to DNG

## Motivation

I created this tool for my own use to process smooth transitions in timelapses when using Adobe Lightroom, but it can be used with any other tool supporting DNG.

Note that this project is what I call a *one afternoon project* (written in a few hours) just for fun, so it is basic and there is plenty of room to improve. Said that, it is fully working and I believe it could be useful for somebody else, so I decided to make it publicly available under license Apache 2.0, which means you can do practically whatever you want with the code. If you feel like to improve it, please do it!.

 
## How it works

Let's suppose there is a list of consecutives images in dng format from a timelapse in a directory `D:\IMAGES`:

`Image01.dng` `Image02.dng` `Image03.dng` `Image04.dng` `Image05.dng` ... `Image20.dng` ... `Image99.dng`

At this point all settings are set to 0 for all images (as it is taken from the camera)

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
* `D:\IMAGES` is the folder containing all the **.dng files
* `D:\BACKUP` is a folder where the tool will make a copy before changing any file
* --files : it means that subsequent parameters will be the names of files to interpolate (Image01.dng Image20.dng)
* --settings: it means that subsequent parameters will be the settings to interpolate
* `crs:Exposure2012` is the name of Exposure setting in XMP definitions of the DNG file (see settings chapter below) 

Once the execution is over, the exposure value for range of images from `Image02.dng`  to `Image19.dng` will have been overwritten by the tool as per a linear interpolation function between values 1 (from `Image01.dng` ) and 2 (`Image20.dng`)


## Parameters


#### Interpolate a set of settings 

`java -jar dng-settings-interpolator-X.X.jar [image_directory] [backup_directory] --file [file1] [file2] ... [filen] --settings [setting1 Name] [setting2 name] ... [settingN name]`

It interpolates the following *range* of images

[file1, file2]
[file2, file3]
...
[filen-1, filen]


Note: If no --settings argument is provider, the tool will interpolate ALL settings
 
#### View all available settings

`java -jar dng-settings-interpolator-X.X.jar --settings`

## Settings

The list of all available settings is [here](src/main/resources/allProperties.txt)

In order to know the name of the setting in the DNG file, the following trick can be used:
Edit the setting you want to know the name with Lightroom (or similar) and set it to a particular value.
Once the dng file is properly saved, open the dng file with any text editor and you'll see the XMP section as plain text (JSON). Look for the value you set before and you'll see the corresponding name of the setting.

### GoPro and Lightroom

Here is the workflow I use to process smooth transitions in GOPRO nightlapses and Lightroom


 <br />

# Building a new release

* develop new code and increase manually version in pom.xml
* `mvn clean verify package`
* `git add .`
* `git commit -m "comment"`
* `git push origin master`
* the new version of the jar will be in /bin folder

