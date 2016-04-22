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
Here, I chose to use a <section> tag to include the header component, then unwrap it if we're in publish (wcmmode=disabled).  However, an equally valid approach would be to set it as a header tag, with the "banner" class, and not unwrap.  The reason we cannot use a "sly" tag, or self-unwrapping, is due to the component highlighting.  In a component that is dropped into a parsys, we wouldn't have this issue, as it will automatically wrap the component in an extra div for sizing purposes.  You will notice that I am using the "header" node to configure properties, and point to the "digital/components/content/header" resourceType that we just created.  If you refresh the page, you should see the same result as having the static html inside the template tags.

You will also hopefully recognize that there is a problem with storing the configurations in the "header" node.  If so, good job!  This is a common issue and we'll touch on the solution a bit later in the exercise.  For now, lets bring in some minor authoring css tweaks for the header.  In the AEMTraining/assets folder, you will find an "author-clientlibs" folder.  Copy this folder into your header component.  This file contains a few tweaks to allow proper editing on the header.  The reason this is required is due to the Perficient Digital header being in a fixed position.  Because it's in a fixed position, AEM has difficulties figuring out where to put the editing boxes.  To reduce headaches in the future, we will remove the "fixed" position for the header in author mode, and instead have it only at the top of the page.  In the publisher, it will return to normal behavior, as these tweaks are only loaded on author.

#### Creating the TouchUI Dialog

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
