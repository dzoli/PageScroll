# PageScroll

# How to install
To get a Git project into your build:
- Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
    allprojects {<br />
      repositories {<br />
        ...<br />
        maven { url 'https://jitpack.io' }<br />
      }<br />
    }<br />
  
- Step 2. Add the dependency
  	dependencies {<br />
	        compile 'com.github.dzoli:PageScroll:0.1.0'<br />
	  }<br />
	  
## Custom attributes:

custom:textColor="RED"						-To change text color<br />
custom:heightOfElementsAndTextSize="60"		-To scale elements and text size (scope will apply from 30 to 120)<br />
custom:textChange="new text"				-To change text <br />


## API Methods:

@param maxPage  -Max items to view<br />
setMaxCount(Integer maxPage)<br />

@param currPage -Item which is currently showen<br />
setCurrPage(Integer currPage)<br />
	  
