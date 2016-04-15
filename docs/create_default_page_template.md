# Create Default Page Template

1. From within the ui.apps project within digital, navigate to the templates directory:
    ```
    cd ./src/main/content/jcr_root/apps/digital/templates
    ```
2. Create a new folder named "page-default".
3. Create a file within "page-default" named ".content.xml"
4. Set the contents of the file to the following:

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <jcr:root xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0"
      jcr:description="Default Template for Perficient Digital."
      jcr:primaryType="cq:Template"
      jcr:title="Perficient Digital Default Template"
      allowedPaths="[/content(/.*)?]"
      ranking="{Long}1">
      <jcr:content
          jcr:primaryType="cq:PageContent"
          sling:resourceType="digital/components/structure/page-default">
      </jcr:content>
  </jcr:root>
  ```

> **The Anatomy of a template**
> - **Description**: The description that is assigned to the template.
> - **Primary Type**: Sets the Node to be of type "cq:Template".
> - **Title**: The title that is assigned to the template.
> - **Allowed Paths**: /content(/.\*)? means that we can access our template, anywhere placed inside content folder
> - **Ranking**: The order (ascending) in which this template will appear in relation to other templates. Setting this value to 1 ensures that the template appears first in the list.
