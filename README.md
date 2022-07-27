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

### get started ###

start with repo mfinfra first

to install the application manually with helm install the complete bundle see repo mfbundle

if Database is fresh: add via gui or api-Docs: currency EUR and USD
then add equities for example DE0005140008 deutsche Bank
US5949181045 Microsoft
add symbols MSFT, DBK
start import prices

#### SNAPSHOTS ####

We only use maven snapshot-versions for the local build not for the CI-Build, because each build generates a docker image and a helm chart.
Both do not support Snapshots but have to distinguish the builds. So each CI-build needs a different number. 
We use the commit_id for that. This brings the additional advantage of a strong binding from the artifact to the code. 
But we loose the main advantage of Snapshots with that: not changing dependency-versions during development. 
For that the local SNAPSHOT build helps if one develeper changes two dependent components. 
If the dependency is developed by another developer, you will most of the time better develop with a stabel version and not a moving target.


#### format ####

the project uses semantic versioning: https://semver.org/

local build with default version 0.0.0-0-SNAPSHOT
via CI: Major, Minor-version and Patch-Version is defined in version.txt. The pre-release info seperated with"-" and contains the CommitId
EACH CI build replaced the maven version number with help of the version plugin.
major.minor.patch-alpha.CommitID.
Release build has the same process but without pre-release version.
e.g. major.minor.patch

#### Branch strategy ####

We allways work on feature branches and merge them to the development-branch at the end, so that you can find at the dev-branch only completed features. 
If you want to make a release you have to merge dev to master. So you can allways see which feature was developed in which release, what is in dev and what in test. 
As soon as the prod rollout was sucessfull a tag should be created in Master.

To keep it simple the CI-Build runs only on the Dev-Branch. That means that you are able to deploy only from this branch.
This is basicly  the git flow workflow but without release branch.
That means for hotfixes in Prod, that you have to deploy all developed features from the last release on.
As long as this software is just for fun, and I'm the only developer and tester this will not be an issue. 
If I test anything I'll do it allready in the featurebranch before I'll merge it to dev.
If this changes we have to edit the filter in the tekton event-lister and parse the revision or 
even better create an additional webhook, eventlister and pipeline to deploy the releasebranch in a different environment.


create dev branch:
git checkout -b dev
create feature branch
git checkout -b a_feature (to switch back: git checkout master)
you are on branch a_feature yet, see git branch
use MYF_[tasknumber]_description for the featurename
git commit -am "a description"
git push origin featurebranchname

merge to dev:
do that via github frontend and create and merge a pullrequest
delete featurebranch: git branch -d  featurename


make a release:
git checkout dev
//change and commit the version in the version.txt to major_minor_micro - do it in the dev branch to avoid mergeconflicts
git push origin dev
git checkout master
git merge dev
git tag -a Release_major_minor_micro -m "this is the latest commit"
git push origin master --follow-tags
//after sucessful rollout

prepare for next release:
git checkout dev
//change and commit the version in the jenkins-file to major_minor_micro-alpha.
commit and push

for the deployment you have to change the app version in the helm-chart of the mfdeployment repository and merge the dev branch to the prod-branch

- on feature branches and on the master will be no ci-builds

git log --oneline --graph --decorate

## Backend access ##

for the development of the frontend with the gitpod ide it is necessary to have a dev backend available. For this the backend will publish via ci after every commit at my server https://babcom.myds.me:30022/dac/rest.
SSL usage is important or other wise no connection is allowed from an gitpod envirmonment.
to create a certificate I've used my synology:
- control_center-external_access-ddns add babcom.myds.me
- control_center-security-certificate add new lets encrypt certifikate
- control_center-security-certificate export certificate
  the is easier but you can use lets encrypt directly or any other service to create a certificate as well

to use the certificate in the backend you have to do the following steps:
- unzip at your win-client and upload them to a linix server with java installed (currently my devenv2 server see MYF-527)
- rename privkey.pem to privkey.key
- openssl pkcs12 -export -out eneCert.pkcs12 -inkey privkey.pem -in cert.pem
- keytool -genkey -keyalg RSA -alias selfsigned -keystore devkeystore.jks  //use your personal infos but mind to use the same password as configured in in dac.res org.ops4j.pax.web.ssl.password
- keytool -delete -alias selfsigned -keystore devkeystore.jks //delete default certifikate
- keytool -v -importkeystore -srckeystore eneCert.pkcs12 -srcstoretype PKCS12 -destkeystore devkeystore.jks -deststoretype JKS
- copy the certificate to /mnt/data/mf/dev_config and restart the container.
- add a portforwarding to the backend ssl port 30022

It is the same for Prod but with another port(for me 30042).
I use a reversproxy for frontend. In this case the user do not have to use a special port and you don't have to add a ssl certificate to your frontend - just handle this in your reverse proxy.
I would like to do it the same way for the backend but unfortunately my integrated Reversproxy in the firewall is only working for the root domain.
You have to publish the backend with https as well because an https frontend is not allowed to communicate with an unsecure backend.