# Create the Perficient Digital Header
    
## Overview
* Creating a static header
* Making the editor authorable

### Part I - Create a static version of the Header

Inside the "AEMTraining/assets" folder, within the "Create-Perficient-Digital-Header" branch, you will notice a file named "header-static.html".  The first step to adding a header component into AEM is to get the HTML markup, and massage it into our existing AEM project.  Copy the contents of the header, and paste it into the partial we created for the page header, "AEMTraining/ui.apps/src/main/content/jcr_root/apps/digital/components/structure/page-default/partials/header.html"

After pasting the contents, it will look similar to the following:

```html
<template data-sly-template.header="">
    <header role="banner">
        <div class="search-icon" id="search-trigger-overlay">
            <span></span>
        </div>
        <div class="container-fluid">
        ...
    </header>
</template>
```

If you have not yet created a content page, take the time to do so in siteadmin or sites.html using the "Create Page" button.  Since we're using the "digital" project, a natural homepage would be /content/digital/en or /content/digital/en-us.  

Once your page exists, lets open it and see our new header.  You'll notice that the logo doesn't display correctly, and one of the main navigation items also looks incorrect.  Don't worry!  This is intentional as HTML will not be handed to you in AEM functioning order for client projects.  Many times when we are given markup to add as an AEM component, they will require some small changes in order to work correctly.  In this case, it's just two very simple updates: the path to the logo image, and the link to the "Services" navigation item.  

The logo image is simple enough of a change, currently it exists under /etc/designs/digital/img, go ahead and modify all previous references to the logo to the correct path.  

The Services issue is due to it being flagged as an invalid link.  This is a common issue that can be temporarily resolved by setting the url to "#".

Now to the exciting part!

### Part II - Making the header authorable

Now we have a static header!  Great!  However, this won't be very useful for multiple languages, or if page URL's change or are removed from the navigation.  Let's make all of these authorable to account for any future changes.  There will be a few steps in doing this:
* Move the static code into a header component
* Create a dialog to edit the authorable pieces
* Modify the markup to utilize the authored pieces

#### Moving the static code into a header component
First step here is to create a component under /apps/digital/content/header.  I prersonally complete this step using the IDE and a .content.xml file.  The resulting structure should be similar to the following screenshot:

![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/HeaderComponentLocation.png "Structure is fun!")

Inside the .content.xml, we'll need to define the component.  This can be done by manually creating a .content.xml, or by adding properties to the header node in crx/de and vlt or packaging it back to your IDE.  In this case we're going to use the following properties:
* jcr:primaryType="cq:Component"
* jcr:title="Perficient Digital Header"
* componentGroup="digital"

The result should be similar to the following:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<jcr:root xmlns:jcr="http://www.jcp.org/jcr/1.0"
    jcr:primaryType="cq:Component"
    jcr:title="Perficient Digital Header"
    componentGroup="digital"/>
```    
Note that in this case we do not need to have a sling:resourceSuperType, as this component is not extending any existing component.

Now that we have the base component, we're ready to add back in our static html.  To do this, cut all the content inside of the <template></template> tags from /apps/digital/structure/page-default/partials/header.html, and put them in a new file under /apps/digital/content/header/header.html.

Back in the /apps/digital/structure/page-default/partials/header.html file, we're going to add a static reference to the header component.  Inside (the now empty) <template> tag, add a reference to the new header component using sightly.
```html
<template data-sly-template.header="">
    <section data-sly-resource="${'header' @ resourceType='digital/components/content/header'}" data-sly-unwrap="${wcmmode.disabled}"></section>
</template>
```
Here, I chose to use a ```<section>``` tag to include the header component, then unwrap it if we're in publish (wcmmode=disabled).  However, an equally valid approach would be to set it as a header tag, with the "banner" class, and not unwrap.  The reason we cannot use a "sly" tag, or self-unwrapping, is due to the component highlighting.  In a component that is dropped into a parsys, we wouldn't have this issue, as it will automatically wrap the component in an extra div for sizing purposes.  You will notice that I am using the "header" node to configure properties, and point to the "digital/components/content/header" resourceType that we just created.  If you refresh the page, you should see the same result as having the static html inside the template tags.

You will also hopefully recognize that there is a problem with storing the configurations in the "header" node.  If so, good job!  This is a common issue and we'll touch on the solution a bit later in the exercise.  For now, lets bring in some minor authoring css tweaks for the header.  In the AEMTraining/assets folder, you will find an "author-clientlibs" folder.  Copy this folder into your header component.  This file contains a few tweaks to allow proper editing on the header.  The reason this is required is due to the Perficient Digital header being in a fixed position.  Because it's in a fixed position, AEM has difficulties figuring out where to put the editing boxes.  To reduce headaches in the future, we will remove the "fixed" position for the header in author mode, and instead have it only at the top of the page.  In the publisher, it will return to normal behavior, as these tweaks are only loaded on author.

#### Creating the Classic Dialog

At time of writing, there is an issue with AEM where if no dialog.xml exists, it will not allow the component to be edited.  Due to this, we will need to first create an empty dialog.xml node.  As this tutorial is focused on TouchUI components, we will not go through the process of adding an ExtJS based dialog for classic UI.  Create a "dialog.xml" under  your header component with the following content (an empty dialog): 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<jcr:root xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0"
          jcr:primaryType="cq:Dialog"
          height="400"
          stateful="false"
          xtype="dialog">
    <items jcr:primaryType="cq:WidgetCollection">
        <tabs jcr:primaryType="cq:TabPanel">
            <items jcr:primaryType="cq:WidgetCollection">
            </items>
        </tabs>
    </items>
</jcr:root>
```
#### Creating the TouchUI Dialog

For beginning a touchui dialog, I always personally will start from a previously created one (as similar as possible) from a previous project.  Given this is a training exercise, I will share an outline to utilize in this step for speedier results.  Copy the ```_cq_dialog.xml``` and the ```_cq_editConfig.xml``` from ```AEMTraining/assets``` folder to ```AEMTraining/ui.apps/src/main/content/jcr_root/apps/digital/components/content/header```.  This will serve as your starting point.  Before going much further, lets gather a few things from the look and feel of the header.  Currently, looks like there are 5 main navigation items, each with a list of associated links, as well as a landing page itself.  Inside of a given navigation item, each sub-link is split into four columns.  Now looking at the right hand side, we see four utility links and a Search button.  For the purpose of this exercise, we are not going to hook up the search button.  The dialog you just copied has the following entries:
* Tab 1: General 
  * Logo path - A pathfield pointing to the DAM to select a logo image (no upload is supported)
  * Alt Text - Alt text for visually impared - need to keep everything accessible! (textfield)
  * Logo URL - Where do we take the user when the logo is clicked? (pathfield)
* Tab 2: Navigation Menu 1
  * Menu title - What do we put as the tab/navigation item title? (textfield)
  * Menu URL - Where does that take you when clicked on directly? (pathfield)
  * Menu items - Now list all the links to appear within that navigation item (This is done using an ACS Commons composite multifield, pathbrowser, as well as a textfield) - I've made the decision to split this up into 4 groups in the back-end. 
* Tab 3,4,5,6: Navigation Menu 2,3,4 respectively
  * These tabs were left blank to complete during this exercise
* Tab 7: Right rail links
  * Navigation Items: Now list all the links to appear within that navigation item (This is done using an ACS Commons composite multifield, pathbrowser, and textfield)
  * Link 1 Text: Text to display for first link
  * Rest of this tab left blank for exercise

First, let's build and see what we have.
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/TouchUIDialog.png "That dialog is sleek!")

Great!  Now using the provided dialog, fill in the values for Navigation Menu 2,3,4,5.  These should be essentially the sameas Navigation Menu 1, save for the different names.  In each tab, replace the "1" within the "name" of the dialog property with the corresponding tab number.  For example: ./nav2Items (for the 3rd tab, Navigation 2).

Now, redeploy your code.  You should see your new tabs behave the same to the "Navigation 1" tab.  Fill in values for each field and save.  Ensure that upon re-opening all of the values match what was entered.  If values do not match, take a closer look at the names used in the failing fields tabs.  

#### Hooking up the back-end
Let's take a quick look at the expanded Header markup (before connecting the properties), so we have a better idea of how we'll turn this into a dynamic component.
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/ExpandedHeader.png "There's a good design!")

First things first, we'll need to create a WCMUsePojo to be used with the component.  This is required based on some of the more complicated logic that we will need to deal with for the composite multifield items.  For more basic components, we would be able to access the properties directly, making this step unnecessary.

Create a Java class under ```AEMTraining/core/src/main/java/com/perficient/adobe/digital/core/sightly/components/PDHeader.java```.  As we'll be connecting this class to a Sightly component, it'll naturally extend the WCMUse class, as seen here: ```public class PDHeader extends WCMUse{```.  Initially, this will prompt an error, as you aren't overriding the ```activate()``` class.  Go ahead and create it ```java
@Override
public void activate() throws Exception {

}```

Given we already have the dialog created, lets begin hooking up the authored values in the same order they appear in the dialog.  Lets start with the 'Logo' on the general tab.  We will do all three of it's properties at once, as they're all related.  In the java class, add class-level variables for each of your logo properties.  In this example, these will be named: logoUrl, logoPath, logoAltText.  Go ahead an generate default getter/setter methods for each of these variables.  The end result should be similar to the following:
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/LogoGetterSetter.png "Most excellent getter/setters!")

Now since these properties not require any complex logic, we can populate them from the page properties in the activate method.  From extending the WCMUse class, we inherit the method ```getProperties()```, which will return a nice valuemap for us to manipulate.  Those with any previous AEM experience will find this fairly familiar.  To allow us to not require repeat calls to the ```getProperties()``` method, lets start out the first line of the activate method as ```ValueMap properties = getProperties();```.  From there, we can retrieve properties from the component object.  Assuming no names were changed, the properties we need to access are: 
* logoImage
* logoAltText
* logoImageURL

The ValueMap class has a method to retrieve properties, with the ability to specify the fallback class, or property value, if no such property exists.  For the time being, we'll just use a class for the fallback.  On the second line of the activate method (after defining the properties ValueMap), lets set the logo properties: ```setLogoPath(properties.get("logoImage",String.class))```  Repeat the same process for logoImageURL and logoAltText. 

_Bonus:_ Create static variables for the property keys.  This is a best practice as it allows future authors to modify the key values in a single location, while also allowing other components access to those property names, in case their logic may depend on it.

So, now we have the logo getters and setters ready.  How do we actually utilize it in the component?  Great question!  This is where we can utilize the ```data-sly-use``` attribute.  Using this tag we can tell a given tag element to utilize a Java class which implements WCMUse.  In our header component, we will need to use it throughout the markup, so it makes the most sense to add this tag to the header element.  When invoking data-sly-use, we can add a variable to store the resulting java object.  This is essential for our component to utilize the various methods that we'll be creating, including the getter/setter methods for the Logo.  The value of the data-sly-use attribute is supposed to point to a javascript or WCMUse implemented Java class.  As we've created the latter, the result should be similar to the following:
```html
<header role="banner" data-sly-use.header="com.perficient.adobe.digital.core.sightly.components.PDHeader">
```
The above implementation stores the component into a variable named "header".  This object can be accessed using standard sightly tactics ```${header}```.  To access a method, you can strip off the "get" portion of the method name, for example ```${header.logoPath}``` would return the result of ```getLogoPath()``` in the PDHeader class.  Using this information, we can modify the image references to '/etc/designs/digital/img/perficient-logo.png' to instead reference ```${header.logoPath}``` resulting in the following:
```<img src="/etc/designs/digital/img/perficient-logo.png">``` --> ```<img src="${header.logoPath}">```.  Now you may be thinking "Hey!  There is no altText on these images!", well, as developers we have to see these types of mistakes and at least bring it to the attention of your technical lead.  In this situation, I am your technical lead, and I say, add it in!  Accessible sites are the new norm!  Add an altText attribute to the image, and have it set to be ```${header.logoAltText}```.  Lastly, we have configured the URL for when someone clicks the logo image.  As each img element is within an anchor container, we just need to now modify the anchor to point to our authored url.  Replace the ```href='#'``` with ```href="${header.logoUrl}"```.

Lets do another build to the server, to see where we're at.  Once you refresh the page, you'll notice some problems right off the bat!  The logo image is blank if the dialog is not authored!  This is a classic case of a missed default.  In this case, we want to make sure that if the image is not selected, we fall back to the original /etc/designs value.  Let's jump back to the PDHeader class and modify that behavior.

You'll remember me mentioning a few steps back that the ValueMap class' get function has the ability to return default values, or a default null-set class in leu of a default.  Lets create some static variables to utilize as property defaults.  For the logo image path, lets use the original value, /etc/designs/digital/img/perficient-logo.png.  For example, for the Image Path, I modified the following two lines:
```java
    public static String DEFAULT_LOGO_IMAGE = "/etc/designs/digital/img/perficient-logo.png";
    ...
    public void activate() throws Exception {
        ...
        setLogoPath(properties.get(LOGO_IMAGE_PROP,DEFAULT_LOGO_IMAGE));
        ...
    }
```
Please go ahead and do the same for the default alt-text and the default image URL.  Set them to be "Perficient Digital" and "http://www.perficient.com/services/perficient-digital" respectively.  Upon doing another build, you should see your default values set properly in the header.  Excellent work!  Now do a little bit of testing, by setting the logo image, url, and alt-text to new values.  Ensure that they're updated accordingly.

Huzzah!  We've got some authoring in place!  Now lets work on the navigation section items.  There are 5 navigation sections, each following the exact same functionality.  From my experience, this points to a good use case of a sightly template, with passed in values for the linked list, as well as the section title and URL.  Lets take a look at the relevant markup:
```html
<li class="nav-item">
    <a href="#" class="nav-link dropdown-toggle" id="servicesMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Services
    </a>
    <div class="dropdown-menu mega-menu" aria-labelledby="servicesMenu">
        <div class="menu-title">
            <a href="#">Services</a>
        </div>
        <div class="menu-block">
            <ul class="list-unstyled">
                <li class="mega-menu-item"><a href="">Adobe</a></li>
                <li class="mega-menu-item"><a href="">Cloudera</a></li>
                ...
                <li class="mega-menu-item"><a href="">TIBCO</a></li>
            </ul>
        </div>
    </div>
</li>
```
Well, that's a mouthful, but if we break it down, each of the five navigation sections are added in as a ```<li class='nav-item'>``` element, with their sub-links as a secondary list.  This behavior is consistent across all five sections - excellent news!  We will be able to reuse the same code for each of the five sections.  Lets start by stripping out (cutting) the very first menu item and putting it into it's own file (pasting).  Create a new file named "navSection.html" with a copy of the services ```<li class='nav-item'>``` contents.  It should look like the following:
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/navSection.png "Templating at its finest!")
Great.  Now let's add the AEM template tags around the whole file contents, similarly to the page component.  Add the following as the first line of the navSection.html file:
```<template data-sly-template.navSection="${@ sectionTitle, sectionURL, sectionLinks}">```
And the corresponding line as the last line of the file:```</template>```.  As always, ensure you follow proper indenting best practices.

You'll notice I added the ```${@ sectionTitle, sectionURL, sectionLinks}``` in the opening tag.  This is telling AEM that we will be passing in variables for each of the sectionTitle, sectionURL and sectionLinks items.  For now, let's just ensure we can include the same markup without a visual difference.  As we cut/paste the "Services" section, from the place you cut out that content (Approx. line 28 of header.html, add the include of this template: ```<li data-sly-use.templ="navSection.html" data-sly-call="${templ.navSection}" data-sly-unwrap></li>```

Upon building and refreshing the page, again you should not see any difference.  Let's now add the "Navigation 1" tab to our WCMUse class.  Open PDHeader.java and add variables for each of the navigation 1 items:
* section1Label
* section1URL
* nav1Items

Like the logo elements, let's include static variables for the property names and default values in addition to the property variables.  In this case we're not going to set a default for nav1Items, but the section1Label and url match the existing source of "Services" and "#".  The end result should be as follows:
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/navigation1Java.png "More setters and getters!")

You may notice that for the variable holding nav1Items, we're using a Map object.  This is due to the way that ACS Commons Composite Multifield works.  It stores the property information in JSON form (based on our implementation), and then includes a utility method to convert said JSON into a standard list (of HashMaps).  Now since both a list and map are readable in sightly, we can use that object directly!  First, add the setters for the section1Label and section1URL using the static defaults and property names above.  For the nav1Items object, however, use the ACSCommons ```MultiFieldPanelFunctions.getMultiFieldPanelValues``` method, as mentioned above.  This will result in the following:
```java
setSection1Label(properties.get(NAV_1_LABEL_PROP,DEFAULT_NAV1_LABEL));
setSection1URL(properties.get(NAV_1_URL_PROP,DEFAULT_NAV1_URL));
setNav1Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_1_ITEMS_PROP));
```
Great!  Now the back-end is set for the Navigation 1 tab, lets now see if we can put those variables into the template we created!  On line 28 (approx.) of header.html, modify it to be the following: 
```<li data-sly-use.templ="navSection.html" data-sly-call="${templ.navSection @ sectionLabel=header.section1Label, sectionURL=header.section1URL, sectionLinks=header.nav1Items}" data-sly-unwrap></li>```

The above will pass in our variables into the template we created!  However, we still haven't hooked up that template to utilize it's own variables!  Lets go an take a look at that now.  Open up the "navSection.html" and do a search for the term "Services".  Since we copied this template using the services navigation section as a default, we can utilize this search to replace instances of "Services" with the passed in variable, section1Label.  For me, the search had two results, lines 4 and 8.  I replaced each instance of "Services" with "${sectionLabel}", and noticed that each of these references were within anchor tags.  Go ahead and replace the href of those anchor tags with the sectionURL variable.  The end result should look as follows:
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/templateHeaders.png "Getting close!")

Let's do a build and check our progress.  If we fill out the "Navigation 1" tab (or had it filled out), the page refresh should update with your proper label being displayed.  Yay!
![alt text](https://github.com/PRFTAdobe/AEMTraining/blob/Create-Perficient-Digital-Header/assets/SuperServices.png "Super Services!")

Now to the fun part!  Let's get our navigation links for the "Navigation 1" tab included.  At this point, we're already passing in the list of items to create links out of, we just need to display them as ```<li class="mega-menu-item">``` items.  First, let's delete all but one of the static links.  We will use the one remaining link to act as a template for all link items.  Next, we need to loop over the sectionLinks variable to get the URL and label of each link.  In sightly, this can be done by using the data-sly-list attribute.  Since we're looping over the ```<li>``` items, we should put the loop initializer inside of the "ul" tag.  This tells AEM that we want to copy all data within the ```<ul class="list-unstyled">``` element will be repeated for each list item.  This is what we should have so far:
```html
<div class="menu-block">
    <ul class="list-unstyled" data-sly-list.link="${sectionLinks}">
        <li class="mega-menu-item"><a href="">Adobe</a></li>
    </ul>
</div>
```
You'll notice taht we used ```data-sly-list.link``` as opposed to just ```data-sly-list```.  Technically, both are valid implementations, but I prefer the more explicit variable defining approach as it's much easier to read.  In this case, we're storing each looped item inside a variable named link.  The next step is to put the link details into the anchor tag:
```html
    <ul class="list-unstyled" data-sly-list.link="${sectionLinks}">
        <li class="mega-menu-item"><a href="${link.url}">${link.label}</a></li>
    </ul>
```

_Exercise:_ Use what you've learned above to hook up the Navigation 2,3,4,5 tabs to utilize the same template.
_Bonus:_ We also did not set up the right-most links (Utility links).  Use your knowledge of a data-sly-list, and the ACS Multifield Utility class to configure this.
