# Lightroom and GoPro

The following is an example that shows a timelapse where first there is a full moon and progressively the moon is hidden in the mountain, so the light condition changes dramatically.

It is assumed that the images GoPro 8 have been taken in a configuration similar to:

* Time Lapse => Night lapse
* Lens: Wide
* Interval: Auto
* Format: Photo
* Output: RAW
* Shutter: 30s
* White Balance: Native
* ISO Min: 100
* ISO Max: 800
* Sharpness: Medium
* Color: Flat


Let's divide the images in three lists

LIST A: images having FULL moon
LIST B: images having TRANSITION between moon and no moon
LIST C: images with NO moon (completely dark)

## Workflow

* Import all images of the nightlapse (GRP format) as DNG files in Lightroom
 
* Adjust the settings for all images in List A and the first of List B  as described in point "Settings for images with full moon" 

* Adjust the settings for all images of List B and the last of List B as described in point "Settings for images without moon"

* Go to  "Library" Lightroom panel select all images of the timelapse (List A, List B and List c), then Menu Metadata -> "Update DNG previews & Metadata": this operation will update the DNG files with the proper settings

* Execute this tool as follows:

`java -jar dng-settings-interpolator-X.X.jar D:\IMAGES D:\BACKUP -- files <first file of list B> <last file of list b>`

* Once the tool has finished, in the Library panel of Lightroom select the directory having the images and select 
 ![syncronize folder](images/syncronizeFolder.jpg)
 
* The images in List B shows a smooth transition!!

* Create the timelapse using your favourite tool from the list o images !!






# Setting for images with full moon



Settings in Develop TAB in Lightroom

![Moon basic](images//basic_full_moon.jpg) ![Moon basic](images//detail_full_moon.jpg)  ![Moon basic](images/tone_curve_full_moon.jpg)



![sample](images//sample_moon.jpg) 




# Setting for images without  moon

Settings in Develop TAB in Lightroom

![Moon basic](images//basic_no_moon.jpg) ![Moon basic](images//detail_no_moon.jpg)  ![Moon basic](images/tone_curve_no_moon.jpg)

![sample](images//sample_no_moon.jpg) 
