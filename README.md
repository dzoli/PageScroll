# PageScroll

# How to install
To get a Git project into your build:
- Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
   ![alt text](PageScroll/screenshots/project build.gradle.JPG "project build.gradle")
  
- Step 2. Add the dependency
  	![alt text](PageScroll/screenshots/app build.gradle.JPG "app build.gradle")
	  
## Custom attributes:

custom:textColor="RED"						-To change text color<br />
custom:heightOfElementsAndTextSize="60"		-To scale elements and text size (scope will apply from 30 to 120)<br />
custom:textChange="new text"				-To change text <br />


## API Methods:

@param maxPage  -Max items to view<br />
setMaxCount(Integer maxPage)<br />

@param currPage -Item which is currently showen<br />
setCurrPage(Integer currPage)<br />
	  
