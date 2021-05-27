# Lightroom and GOPRO


Let's configure the GoPro 8 to shoot a nighlapse

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
LIST B: images having transition between moon and no moon
LIST C: images without moon (completely dark)

Workflow:

1) Import all images of the nightlapse (GRP format) as DNG files in Lightroom
2) Adjust the settings for all images in List A and the first of List B  as described in point "Settings for images with full moon" 
3) Adjust the settings for all images of List B and the last of List B as described in point "Settings for images without moon"
4) Go to  "Library" Lightroom panel select all images of the timelapse (List A, List B and List c), then Menu Metadata -> "Update DNG previews & Metadata": this operation will update the DNG files with the proper settings
5) Execute this tool as follows:




## Setting for images with full moon



Settings in Develop TAB in Lightroom

![Moon basic](images//basic_full_moon.jpg) ![Moon basic](images//detail_full_moon.jpg)  ![Moon basic](images/tone_curve_full_moon.jpg)


## Setting for images without  moon

Settings in Develop TAB in Lightroom

![Moon basic](images//basic_no_moon.jpg) ![Moon basic](images//detail_no_moon.jpg)  ![Moon basic](images/tone_curve_no_moon.jpg)
