# Create the Perficient Digital Header

This is an exercise to build out the Perficient Digital Header for our training series.  It is expected that you have completed the page-template exercises prior to starting on this component.

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
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

If you have not yet created a content page, take the time to do so in siteadmin or sites.html using the "Create Page" button.

Once your page exists, lets open it and see our new header.  You'll notice that the logo doesn't display correctly, and one of the main navigation items also looks incorrect.  Don't worry!  This is intentional as HTML will not be handed to you in AEM functioning order for client projects.  Many times when we are given markup to add as an AEM component, they will require some small changes in order to work correctly.  In this case, it's just two very simple updates: the path to the logo image, and the link to the "Services" navigation item.  

The logo image is simple enough of a change, currently it exists under /etc/designs/digital/img, go ahead and modify all previous references to the logo to the correct path.  

The Services issue is due to it being flagged as an invalid link.  This is a common issue that can be temporarily resolved by setting the url to "#".

Now to the exciting part!

### Part II - Making the header authorable
