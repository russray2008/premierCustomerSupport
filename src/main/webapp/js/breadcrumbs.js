/* This script and many more are available free online at
The JavaScript Source :: http://www.javascriptsource.com
Created by: Copperfield Publishing :: http://www.copperfieldpub.com/ */

function Crumb(Path, Name, Url) {
	this.Path = Path;
	this.Name = Name;
	this.Url = Url;
}

BagOCrumbs = new Array();

// add new directories here. the format:
// Path: the name of the directory
// Name: the text you want to display onscreen
// Url: the URL to the page for this group page or book

BagOCrumbs[0] = new Crumb("navigation", "navigation scripts", "/navigation/");
BagOCrumbs[1] = new Crumb("breadcrumb_trails", "breadcrumb trails",
		"/breadcrumb_trails/about_breadcrumb_trails.htm");
BagOCrumbs[2] = new Crumb("breadcrumb_demo", "breadcrumb demo",
		"/demos/demos.htm");

// ... we build the path and display it

var i, x;
// the character below displays between the directories; you can change it if
// you like.
strConcat = " | ";
strUrl = document.location.href;
strList = "<a href='/'>home</a>";
strDebug = "";
aryDirs = strUrl.split("/");
for (x = 0; x < aryDirs.length; x++) {
	for (i = 0; i < BagOCrumbs.length; i++) {
		if (BagOCrumbs[i].Path.toLowerCase() == aryDirs[x].toLowerCase()) {
			strList += strConcat + "<a href='" + BagOCrumbs[i].Url + "'>"
					+ BagOCrumbs[i].Name + "</a>";
			i = BagOCrumbs.length;
		}
	}
}

// comment out the next line to eliminate the display of the document's title
// the character below displays between the directory and the document's title;
// you can change it if you like.

strList += " | " + document.title;
document.write(strList);


