# mfframework

to use this in other project add the mavenrepository with this artifacts to your ~/.m2/settings.xml. 
in my case it ist my local nexus where I deploy all my artifacts.

```xml
<mirror>
  <id>my-local-repo</id>
  <mirrorOf>external:http:*</mirrorOf>
  <url>http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo/</url>
  <blocked>false</blocked>
</mirror>
```